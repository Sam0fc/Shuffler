package me.xemor.shuffler.game.crafting;

import me.xemor.shuffler.game.ProbabilityBag;

import me.xemor.shuffler.game.ConstantItemGenerator.RecipeNode;
import org.bukkit.Material;

import java.util.*;

/**
 * Maximum-entropy model of crafting difficulty. It separates how easy an item is to make from how much weight it gets.
 * <p>
 * <b>1. Obtainability.</b> The raw materials bag gives each primitive (a raw material) a rate at which it is gathered. How fast an
 * item can be obtained is worked out without any weight being split between the things it can become, so an
 * ingredient that is useful for fifty recipes does not make each of them harder. Crafting happens in whole crafts:
 * <ul>
 *     <li>the item itself, as the target, needs one craft of a recipe, which uses the recipe's full ingredient counts
 *     (block shuffle only needs one of it);</li>
 *     <li>as an ingredient needing n of it, it costs ceil(n / yield) whole crafts. A torch's one stick is a full craft
 *     of two planks, while stone brick stairs need 6 bricks, which is two crafts of 4 stone, so 8 stone.</li>
 * </ul>
 * Rates are worked out like this:
 * <ul>
 *     <li>A recipe needs all of its ingredients, one after another. The ingredients can each come from several
 *     primitives (a stick is partly a leaf drop and partly planks), so the recipe is worked out for every combination
 *     of origins, weighted by the chance of that combination, and the results are added. Within a combination, ingredients
 *     made from the same primitive draw on one supply, so planks and sticks from oak are not counted as two chores.
 *     With A_p the units needed from primitive p and R_p the rate p supplies, a combination makes
 *     M = 1 / sum_p(A_p / R_p).</li>
 *     <li>Alternative recipes, and gathering, are added origin by origin: a stick made from oak planks and a stick made
 *     from birch planks are different supplies and add, but two recipes drawing on the same wood are one supply, so the
 *     better of them counts.</li>
 * </ul>
 * Smelting is a serial chore on top of gathering. A round lasts {@link #ROUND_SECONDS} and each furnace load takes
 * {@link #SMELT_SECONDS}, so an item that needs s rounds of smelting (see {@link #smeltSlots}) can only be gathered in the
 * remaining 1 - p of the round, p = s * SMELT_SECONDS / ROUND_SECONDS. With gathering memoryless at rate M, finishing by
 * the end of the round is as likely as for an item gathered at rate M * (1 - p) with no smelting, so its target
 * obtainability is scaled by 1 - p, and by 0 once p reaches 1. This is applied once to the whole recipe tree, to the
 * target only: the rates of ingredients are not scaled, or a smelt would be charged once for every recipe above it.
 * <p>
 * <b>2. Weight.</b> Every primitive has a budget: its weight in the raw bag. All weight made from a primitive, summed over
 * everything that came from it, must equal that budget exactly, so a new recipe can only take weight from the things
 * that already use that primitive. Of all the ways to do that, the model picks the one that stays closest, in
 * relative entropy, to the target obtainabilities from step 1: w_c = prior_c * prod_p x_p^l_cp, where l_cp is the fraction of
 * carrier c that came from primitive p. The x_p are congestion prices: a primitive wanted by many easy things gets a
 * small x_p, which scales all of them down together, and a primitive wanted by nothing keeps its weight for itself.
 * They are found by fixing one primitive's price at a time so its budget is met exactly, until all are.
 * <p>
 * The carriers of weight are each raw material (gathering it) and each craftable item (its recipes), so an item that
 * can be both gathered and crafted gets the weight of both.
 * <p>
 * Everything here works on plain arrays indexed by Material ordinal, because it runs on the server thread every time a
 * level starts. A recipe is only worked out again when one of its ingredients has changed.
 */
public class MaxEntropyCraftingModel implements CraftingDifficultyModel {

    private static final Material[] MATERIALS = Material.values();
    private static final int MATERIAL_COUNT = MATERIALS.length;
    private static final int MAX_ORIGINS = Lineage.MAX_ORIGINS;
    /** Most combinations of origins tried per recipe; recipes with many ingredients try fewer origins of each. */
    private static final int MAX_COMBINATIONS = 512;
    /** Origins supplying less than this fraction of an ingredient's rate are ignored. */
    private static final double MIN_ORIGIN_FRACTION = 1e-4;
    /** Each further unit of a gathered ingredient takes this fraction of the effort of the one before it. */
    private static final double BULK_GATHER_DECAY = 0.85;
    /** An item has changed enough for the recipes using it to be worked out again. */
    private static final double CHANGED = 1e-12;
    /** Seconds one load of a furnace takes to smelt. */
    public static final double SMELT_SECONDS = 10;
    /** Seconds a game of block shuffle lasts. */
    public static final double ROUND_SECONDS = 300;
    /** Furnaces a player is assumed to run in parallel. */
    public static final int FURNACES = 4;

    /**
     * Everything that depends only on which raw materials exist: the craftable recipes, grouped by output.
     */
    private record Plan(boolean[] rawKeys, int[] outputs, FlowRecipe[][] recipesByOutput, int maxInputs) {}

    /**
     * Reused arrays for working out recipes.
     */
    private static final class Scratch {
        final double[] contribution = new double[MATERIAL_COUNT];
        final int[] touched = new int[MATERIAL_COUNT];
        int touchedCount = 0;
        final double[] unitSum = new double[MATERIAL_COUNT];
        final double[] factorSum = new double[MATERIAL_COUNT];
        final double[] jobSum = new double[MATERIAL_COUNT];
        final double[] depthSum = new double[MATERIAL_COUNT];
        final double[] previous = new double[MATERIAL_COUNT];
        final double[] rate;
        final int[] originCount, choice, distinctPrim;
        final double[] distinctUnits, distinctTime;
        final Lineage[] inputs;

        Scratch(int maxInputs) {
            rate = new double[maxInputs];
            originCount = new int[maxInputs];
            choice = new int[maxInputs];
            distinctPrim = new int[maxInputs];
            distinctUnits = new double[maxInputs];
            distinctTime = new double[maxInputs];
            inputs = new Lineage[maxInputs];
        }

        void add(int primitive, double weight, double units, double factor, double jobs, double depth) {
            if (contribution[primitive] == 0) touched[touchedCount++] = primitive;
            contribution[primitive] += weight;
            unitSum[primitive] += weight * units;
            factorSum[primitive] += weight * factor;
            jobSum[primitive] += weight * jobs;
            depthSum[primitive] += weight * depth;
        }

        void largest(int primitive, double weight, double units, double factor, double jobs, double depth) {
            if (contribution[primitive] == 0) touched[touchedCount++] = primitive;
            if (weight > contribution[primitive]) {
                contribution[primitive] = weight;
                unitSum[primitive] = weight * units;
                factorSum[primitive] = weight * factor;
                jobSum[primitive] = weight * jobs;
                depthSum[primitive] = weight * depth;
            }
        }

        Lineage drain() {
            if (touchedCount == 0) return null;
            int[] prims = Arrays.copyOf(touched, touchedCount);
            double[] weights = new double[prims.length];
            double[] units = new double[prims.length];
            double[] factors = new double[prims.length];
            double[] jobs = new double[prims.length];
            double[] depths = new double[prims.length];
            for (int i = 0; i < prims.length; i++) {
                weights[i] = contribution[prims[i]];
                units[i] = unitSum[prims[i]] / weights[i];
                factors[i] = factorSum[prims[i]] / weights[i];
                jobs[i] = jobSum[prims[i]] / weights[i];
                depths[i] = depthSum[prims[i]] / weights[i];
                contribution[prims[i]] = 0;
                unitSum[prims[i]] = 0;
                factorSum[prims[i]] = 0;
                jobSum[prims[i]] = 0;
                depthSum[prims[i]] = 0;
            }
            touchedCount = 0;
            return new Lineage(prims, weights, units, factors, jobs, depths);
        }
    }

    private final List<FlowRecipe> recipes;
    private final int maxSweeps;
    private final double sweepTolerance;
    private final int maxIterations;
    private final double budgetTolerance;
    private final double smeltSeconds;
    private Plan cachedPlan = null;
    private double[] lastSmeltSlots = null;

    public MaxEntropyCraftingModel(List<RecipeNode> recipes) {
        this(recipes, SMELT_SECONDS);
    }

    /**
     * @param smeltSeconds seconds one furnace load takes, or 0 to ignore smelting time
     */
    public MaxEntropyCraftingModel(List<RecipeNode> recipes, double smeltSeconds) {
        this(recipes, 500, 1e-9, 200, 1e-9, smeltSeconds);
    }

    /**
     * @param maxSweeps most passes over the recipes to settle how obtainable each item is
     * @param sweepTolerance relative change in an item's obtainability below which it has settled
     * @param maxIterations most passes over the primitives to make each one's weight match its budget
     * @param budgetTolerance largest allowed |ln(weight made / budget)| for any primitive
     */
    public MaxEntropyCraftingModel(List<RecipeNode> recipes, int maxSweeps, double sweepTolerance, int maxIterations, double budgetTolerance) {
        this(recipes, maxSweeps, sweepTolerance, maxIterations, budgetTolerance, SMELT_SECONDS);
    }

    public MaxEntropyCraftingModel(List<RecipeNode> recipes, int maxSweeps, double sweepTolerance, int maxIterations, double budgetTolerance, double smeltSeconds) {
        this.smeltSeconds = smeltSeconds;
        this.recipes = FlowRecipe.from(recipes);
        this.maxSweeps = maxSweeps;
        this.sweepTolerance = sweepTolerance;
        this.maxIterations = maxIterations;
        this.budgetTolerance = budgetTolerance;
    }

    @Override
    public ProbabilityBag propagate(ProbabilityBag rawMaterials) {
        ProbabilityBag result = new ProbabilityBag();
        weights(rawMaterials).forEach((material, weight) -> {
            if (weight > 0) result.addWeighting(material, weight);
        });
        return result;
    }

    /**
     * @return the final weight of every obtainable material, summing to the total raw weight
     */
    public synchronized Map<Material, Double> weights(ProbabilityBag rawMaterials) {
        double[] raw = new double[MATERIAL_COUNT];
        boolean[] rawKeys = new boolean[MATERIAL_COUNT];
        for (ProbabilityBag.Entry entry : rawMaterials.entries()) {
            if (entry.probability() > 0) {
                raw[entry.material().ordinal()] = entry.probability();
                rawKeys[entry.material().ordinal()] = true;
            }
        }

        Plan plan = planFor(rawKeys);
        Scratch scratch = new Scratch(plan.maxInputs());
        Lineage[] supply = new Lineage[MATERIAL_COUNT];
        Lineage[] target = new Lineage[MATERIAL_COUNT];
        long[] versions = new long[MATERIAL_COUNT];
        for (int material = 0; material < MATERIAL_COUNT; material++) {
            if (rawKeys[material]) supply[material] = new Lineage(new int[]{material}, new double[]{raw[material]}, new double[]{1}, new double[]{1}, new double[]{0}, new double[]{0});
        }

        int[] groupOf = new int[MATERIAL_COUNT];
        Arrays.fill(groupOf, -1);
        for (int group = 0; group < plan.outputs().length; group++) groupOf[plan.outputs()[group]] = group;

        // For each recipe, what one craft of it made last time it was worked out.
        Lineage[][] madeAsTarget = new Lineage[plan.outputs().length][];
        long[][] seen = new long[plan.outputs().length][];
        for (int group = 0; group < plan.outputs().length; group++) {
            int size = plan.recipesByOutput()[group].length;
            madeAsTarget[group] = new Lineage[size];
            seen[group] = new long[size];
            Arrays.fill(seen[group], -1);
        }

        for (int sweep = 0; sweep < maxSweeps; sweep++) {
            double maxRelativeChange = 0;
            for (int group = 0; group < plan.outputs().length; group++) {
                int output = plan.outputs()[group];
                FlowRecipe[] groupRecipes = plan.recipesByOutput()[group];
                for (int r = 0; r < groupRecipes.length; r++) {
                    FlowRecipe recipe = groupRecipes[r];
                    long key = 0;
                    for (int input : recipe.inputs()) key += versions[input] + 1;
                    if (seen[group][r] == key) continue;
                    seen[group][r] = key;
                    madeAsTarget[group][r] = makeRecipe(recipe, supply, madeAsTarget, groupOf, plan, raw, rawKeys, scratch);
                }

                // Different recipes, and gathering, add origin by origin. The target is what one craft of the item
                // itself takes, so its smelting time cuts into the round; what is supplied to other recipes is not cut.
                for (Lineage made : madeAsTarget[group]) {
                    if (made == null) continue;
                    for (int i = 0; i < made.prims.length; i++) {
                        double keep = 1 - smeltFraction(smeltSlots(made.jobs[i], made.depths[i]));
                        if (keep > 0) scratch.largest(made.prims[i], made.weights[i] * keep, made.units[i], made.factors[i], made.jobs[i], made.depths[i]);
                    }
                }
                Lineage targetMade = scratch.drain();
                for (Lineage made : madeAsTarget[group]) {
                    if (made != null) for (int i = 0; i < made.prims.length; i++) scratch.largest(made.prims[i], made.weights[i], made.units[i], made.factors[i], made.jobs[i], made.depths[i]);
                }
                if (rawKeys[output]) scratch.largest(output, raw[output], 1, 1, 0, 0);
                Lineage supplyMade = scratch.drain();
                if (supplyMade == null) continue;
                double current = supply[output] == null ? 0 : supply[output].total;
                if (supplyMade.total < current - 1e-18) continue;

                double change = Lineage.difference(supply[output], supplyMade, scratch.previous);
                if (target[output] != null && targetMade != null) {
                    change = Math.max(change, Lineage.difference(target[output], targetMade, scratch.previous));
                }
                maxRelativeChange = Math.max(maxRelativeChange, change);
                if (change > CHANGED) versions[output]++;
                supply[output] = supplyMade;
                target[output] = targetMade;
            }
            if (maxRelativeChange <= sweepTolerance) break;
        }

        lastSmeltSlots = new double[MATERIAL_COUNT];
        for (int material = 0; material < MATERIAL_COUNT; material++) {
            Lineage lineage = target[material];
            if (lineage == null) continue;
            for (int i = 0; i < lineage.prims.length; i++) {
                lastSmeltSlots[material] += lineage.weights[i] / lineage.total * smeltSlots(lineage.jobs[i], lineage.depths[i]);
            }
        }
        return fitBudgets(raw, rawKeys, target);
    }

    /**
     * @return how many rounds of furnaces the item's smelting took in the last {@link #weights} call, averaged over
     * the origins it is made from by their weight
     */
    public synchronized double smeltSlotsOfLast(Material material) {
        return lastSmeltSlots == null ? 0 : lastSmeltSlots[material.ordinal()];
    }

    /**
     * Works out one craft of a recipe for every combination of origins its ingredients could have. A combination sees
     * each ingredient supplying its whole rate scaled by the chance of that combination, and the combinations are added.
     *
     * @return where the recipe's rate comes from, or null if it cannot be made yet
     */
    private Lineage makeRecipe(FlowRecipe recipe, Lineage[] supply, Lineage[][] madeAsTarget, int[] groupOf, Plan plan,
                               double[] raw, boolean[] rawKeys, Scratch scratch) {
        int inputCount = recipe.inputs().length;
        int originLimit = Math.clamp((int) Math.floor(Math.pow(MAX_COMBINATIONS, 1D / inputCount)), 1, MAX_ORIGINS);
        for (int i = 0; i < inputCount; i++) {
            Lineage lineage = supplyFor(recipe.inputs()[i], recipe.counts()[i], supply, madeAsTarget, groupOf, plan, raw, rawKeys, scratch);
            if (lineage == null || lineage.total <= 0) return null;
            scratch.inputs[i] = lineage;
            scratch.rate[i] = lineage.total;
            int count = 0;
            while (count < Math.min(originLimit, lineage.topCount) && lineage.topFraction[count] >= MIN_ORIGIN_FRACTION) count++;
            if (count == 0) return null;
            scratch.originCount[i] = count;
            scratch.choice[i] = 0;
        }

        scratch.touchedCount = 0;
        boolean more = true;
        while (more) {
            double chance = 1;
            double jobs = recipe.smelts() ? 1 : 0;
            double depth = 0;
            int distinct = 0;
            for (int i = 0; i < inputCount; i++) {
                Lineage lineage = scratch.inputs[i];
                chance *= lineage.topFraction[scratch.choice[i]];
                int primitive = lineage.topPrimitive(scratch.choice[i]);
                jobs += recipe.counts()[i] * lineage.topJobs(scratch.choice[i]);
                depth = Math.max(depth, lineage.topDepth(scratch.choice[i]));
                int slot = 0;
                while (slot < distinct && scratch.distinctPrim[slot] != primitive) slot++;
                if (slot == distinct) {
                    scratch.distinctPrim[slot] = primitive;
                    scratch.distinctTime[slot] = 0;
                    scratch.distinctUnits[slot] = 0;
                    distinct++;
                }
                // The time this ingredient takes without any bulk discount of its own; the discount for everything the
                // recipe needs of this raw material is applied below.
                scratch.distinctTime[slot] += recipe.counts()[i] / (scratch.rate[i] * lineage.topFactor(scratch.choice[i]));
                scratch.distinctUnits[slot] += recipe.counts()[i] * lineage.topUnits(scratch.choice[i]);
            }

            if (recipe.smelts()) depth++;
            double timePerUnit = 0;
            for (int slot = 0; slot < distinct; slot++) {
                scratch.distinctTime[slot] = scratch.distinctTime[slot] / chance * bulkFactor(scratch.distinctUnits[slot]);
                timePerUnit += scratch.distinctTime[slot];
            }
            double made = 1 / timePerUnit;
            if (Double.isFinite(made) && made > 0) {
                for (int slot = 0; slot < distinct; slot++) {
                    scratch.add(scratch.distinctPrim[slot], scratch.distinctTime[slot] * made * made,
                            scratch.distinctUnits[slot], bulkFactor(scratch.distinctUnits[slot]), jobs, depth);
                }
            }

            more = false;
            for (int i = inputCount - 1; i >= 0; i--) {
                if (++scratch.choice[i] < scratch.originCount[i]) {
                    more = true;
                    break;
                }
                scratch.choice[i] = 0;
            }
        }
        return scratch.drain();
    }

    /**
     * Finds the weights closest to the target obtainabilities, in relative entropy, such that everything made from each
     * primitive adds up to that primitive's budget. Each primitive's price is set in turn so that its budget is met
     * exactly with the others held fixed, which is a one-dimensional convex problem, and this is repeated until all are met.
     */
    private Map<Material, Double> fitBudgets(double[] raw, boolean[] rawKeys, Lineage[] target) {
        int[] primitiveIndex = new int[MATERIAL_COUNT];
        List<Integer> primitives = new ArrayList<>();
        for (int material = 0; material < MATERIAL_COUNT; material++) {
            if (!rawKeys[material]) continue;
            primitiveIndex[material] = primitives.size();
            primitives.add(material);
        }
        int primitiveCount = primitives.size();
        double[] budget = new double[primitiveCount];
        for (int p = 0; p < primitiveCount; p++) budget[p] = raw[primitives.get(p)];

        // A carrier of weight: gathering a raw material, or the recipes for an item.
        List<Integer> carrierItems = new ArrayList<>();
        List<Double> logWeights = new ArrayList<>();
        List<int[]> carrierPrimitives = new ArrayList<>();
        List<double[]> carrierFractions = new ArrayList<>();
        for (int p = 0; p < primitiveCount; p++) {
            carrierItems.add(primitives.get(p));
            logWeights.add(Math.log(budget[p]));
            carrierPrimitives.add(new int[]{p});
            carrierFractions.add(new double[]{1});
        }
        for (int material = 0; material < MATERIAL_COUNT; material++) {
            Lineage lineage = target[material];
            if (lineage == null || lineage.total <= 0) continue;
            int[] prims = new int[lineage.prims.length];
            double[] fractions = new double[prims.length];
            for (int i = 0; i < prims.length; i++) {
                prims[i] = primitiveIndex[lineage.prims[i]];
                fractions[i] = lineage.weights[i] / lineage.total;
            }
            carrierItems.add(material);
            logWeights.add(Math.log(lineage.total));
            carrierPrimitives.add(prims);
            carrierFractions.add(fractions);
        }

        int carrierCount = carrierItems.size();
        double[] logWeight = new double[carrierCount];
        int[] usersCount = new int[primitiveCount];
        for (int c = 0; c < carrierCount; c++) {
            logWeight[c] = logWeights.get(c);
            for (int p : carrierPrimitives.get(c)) usersCount[p]++;
        }
        int[][] userCarrier = new int[primitiveCount][];
        double[][] userFraction = new double[primitiveCount][];
        for (int p = 0; p < primitiveCount; p++) {
            userCarrier[p] = new int[usersCount[p]];
            userFraction[p] = new double[usersCount[p]];
            usersCount[p] = 0;
        }
        for (int c = 0; c < carrierCount; c++) {
            int[] prims = carrierPrimitives.get(c);
            double[] fractions = carrierFractions.get(c);
            for (int i = 0; i < prims.length; i++) {
                int p = prims[i];
                userCarrier[p][usersCount[p]] = c;
                userFraction[p][usersCount[p]++] = fractions[i];
            }
        }

        double[] terms = new double[carrierCount];
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            for (int p = 0; p < primitiveCount; p++) {
                int users = userCarrier[p].length;
                double logBudget = Math.log(budget[p]);
                double change = 0;
                for (int newton = 0; newton < 60; newton++) {
                    // F(change) = ln(sum w l e^(l change)) - ln(budget), which is convex and increasing.
                    double largest = Double.NEGATIVE_INFINITY;
                    for (int u = 0; u < users; u++) {
                        terms[u] = logWeight[userCarrier[p][u]] + Math.log(userFraction[p][u]) + userFraction[p][u] * change;
                        largest = Math.max(largest, terms[u]);
                    }
                    double sum = 0, slope = 0;
                    for (int u = 0; u < users; u++) {
                        double e = Math.exp(terms[u] - largest);
                        sum += e;
                        slope += e * userFraction[p][u];
                    }
                    double step = (largest + Math.log(sum) - logBudget) / (slope / sum);
                    change -= step;
                    if (Math.abs(step) < 1e-13) break;
                }
                for (int u = 0; u < users; u++) logWeight[userCarrier[p][u]] += userFraction[p][u] * change;
            }

            double largestError = 0;
            for (int p = 0; p < primitiveCount; p++) {
                double made = 0;
                for (int u = 0; u < userCarrier[p].length; u++) made += Math.exp(logWeight[userCarrier[p][u]]) * userFraction[p][u];
                largestError = Math.max(largestError, Math.abs(Math.log(made / budget[p])));
            }
            if (largestError < budgetTolerance) break;
        }

        Map<Material, Double> weights = new EnumMap<>(Material.class);
        for (int c = 0; c < carrierCount; c++) {
            weights.merge(MATERIALS[carrierItems.get(c)], Math.exp(logWeight[c]), Double::sum);
        }
        return weights;
    }

    /**
     * With {@link #FURNACES} furnaces running at once, smelts that each wait on the one before them (depth) cannot
     * finish faster than the longest chain, and independent ones cannot finish faster than the jobs shared out over
     * the furnaces. For a recipe tree of unit smelts, a schedule meeting the larger of the two is achievable (Hu), and
     * for recipes sharing sub-items it is within a small factor of it.
     *
     * @return how many rounds of furnaces the recipe's smelting takes, one smelt each
     */
    private static double smeltSlots(double jobs, double depth) {
        if (jobs <= 0) return 0;
        return Math.max(depth, Math.ceil(jobs / FURNACES - 1e-9));
    }

    /**
     * @return the share of a game that this many rounds of smelting uses up, at most 1
     */
    private double smeltFraction(double slots) {
        return Math.min(1, slots * smeltSeconds / ROUND_SECONDS);
    }

    /**
     * Gathering gets easier in bulk, since once you are at a vein the next block is close by: the first unit of a raw
     * material takes the full effort and each further unit takes {@link #BULK_GATHER_DECAY} of the one before. This
     * is applied to the total units of each raw material a recipe ends up needing, however many crafts deep it is, so
     * 31 iron ingots for an anvil are cheaper per ingot than one.
     *
     * @return the fraction of the plain, one-unit-at-a-time effort that gathering this many units takes
     */
    private static double bulkFactor(double units) {
        if (units <= 1) return 1;
        return (1 - Math.pow(BULK_GATHER_DECAY, units)) / ((1 - BULK_GATHER_DECAY) * units);
    }

    /**
     * The rate at which an item is supplied when a recipe needs several of it, so that time = needed / rate. Gathering
     * is the same for any amount, since the bulk discount is applied to the raw materials a recipe needs in total, in
     * {@link #bulkFactor}. Crafting only happens in whole crafts: needing 1 stick costs a craft of 2 planks,
     * and needing 6 stone bricks costs two crafts of 4 stone, however many the last craft leaves over. Crafting the
     * item therefore supplies needed / ceil(needed / yield) times as fast as the recipe's single-craft rate.
     */
    private Lineage supplyFor(int item, int needed, Lineage[] supply, Lineage[][] madeAsTarget, int[] groupOf, Plan plan,
                              double[] raw, boolean[] rawKeys, Scratch scratch) {
        if (needed == 1 || supply[item] == null) return supply[item];
        int group = groupOf[item];
        if (rawKeys[item]) scratch.largest(item, raw[item], 1, 1, 0, 0);
        if (group >= 0) {
            FlowRecipe[] groupRecipes = plan.recipesByOutput()[group];
            for (int r = 0; r < groupRecipes.length; r++) {
                Lineage made = madeAsTarget[group][r];
                if (made == null) continue;
                int crafts = (needed + groupRecipes[r].amount() - 1) / groupRecipes[r].amount();
                double factor = (double) needed / crafts;
                for (int i = 0; i < made.prims.length; i++) scratch.largest(made.prims[i], made.weights[i] * factor, made.units[i] / factor, made.factors[i], made.jobs[i] / factor, made.depths[i]);
            }
        }
        return scratch.drain();
    }

    /**
     * Keeps only the recipes that build an item up from things that exist without it. Each item is given a depth: raw
     * materials are 0, and anything else is one more than the shallowest way of making it from items already
     * obtainable. A recipe whose ingredients are not all shallower than its output undoes or sideways-converts
     * something (smelting an iron sword into nuggets, unpacking a block, recolouring wool). Those cannot make an item
     * easier to obtain in practice, but they form loops where a sliver of one origin feeds back on itself and grows
     * without bound, so they are left out.
     */
    private static List<FlowRecipe> forward(List<FlowRecipe> craftable, boolean[] rawKeys) {
        // depth is how deep an item is as an ingredient (0 if it can be gathered); made is how deep its recipes put it
        int[] depth = new int[MATERIAL_COUNT];
        int[] made = new int[MATERIAL_COUNT];
        Arrays.fill(depth, Integer.MAX_VALUE);
        Arrays.fill(made, Integer.MAX_VALUE);
        for (int material = 0; material < MATERIAL_COUNT; material++) if (rawKeys[material]) depth[material] = 0;
        boolean changed = true;
        while (changed) {
            changed = false;
            for (FlowRecipe recipe : craftable) {
                int deepest = deepestIngredient(recipe, depth);
                if (deepest != Integer.MAX_VALUE && deepest + 1 < made[recipe.output()]) {
                    made[recipe.output()] = deepest + 1;
                    depth[recipe.output()] = Math.min(depth[recipe.output()], deepest + 1);
                    changed = true;
                }
            }
        }
        List<FlowRecipe> kept = new ArrayList<>();
        for (FlowRecipe recipe : craftable) {
            if (deepestIngredient(recipe, depth) < made[recipe.output()]) kept.add(recipe);
        }

        // Something that can be gathered can also be crafted (sticks from planks), which is useful, unless it is
        // crafted from something built out of it (raw iron from a block of raw iron), which is a loop.
        BitSet[] builtFrom = new BitSet[MATERIAL_COUNT];
        changed = true;
        while (changed) {
            changed = false;
            for (FlowRecipe recipe : kept) {
                if (rawKeys[recipe.output()]) continue;
                if (builtFrom[recipe.output()] == null) builtFrom[recipe.output()] = new BitSet();
                BitSet set = builtFrom[recipe.output()];
                int before = set.cardinality();
                for (int input : recipe.inputs()) {
                    set.set(input);
                    if (builtFrom[input] != null) set.or(builtFrom[input]);
                }
                for (int station : recipe.workstations()) set.set(station);
                if (set.cardinality() != before) changed = true;
            }
        }
        List<FlowRecipe> forward = new ArrayList<>();
        for (FlowRecipe recipe : kept) {
            boolean loop = false;
            if (rawKeys[recipe.output()]) {
                for (int input : recipe.inputs()) {
                    if (builtFrom[input] != null && builtFrom[input].get(recipe.output())) loop = true;
                }
            }
            if (!loop) forward.add(recipe);
        }
        return forward;
    }

    private static int deepestIngredient(FlowRecipe recipe, int[] depth) {
        int deepest = 0;
        for (int input : recipe.inputs()) deepest = Math.max(deepest, depth[input]);
        for (int station : recipe.workstations()) deepest = Math.max(deepest, depth[station]);
        return deepest;
    }

    /**
     * @return the craftable recipes for these raw materials, grouped by the item they make
     */
    private Plan planFor(boolean[] rawKeys) {
        if (cachedPlan != null && Arrays.equals(cachedPlan.rawKeys(), rawKeys)) return cachedPlan;

        Map<Integer, List<FlowRecipe>> byOutput = new TreeMap<>();
        int maxInputs = 1;
        for (FlowRecipe recipe : forward(FlowRecipe.craftable(recipes, rawKeys), rawKeys)) {
            byOutput.computeIfAbsent(recipe.output(), _ -> new ArrayList<>()).add(recipe);
            maxInputs = Math.max(maxInputs, recipe.inputs().length);
        }
        int[] outputs = byOutput.keySet().stream().mapToInt(Integer::intValue).toArray();
        FlowRecipe[][] recipesByOutput = byOutput.values().stream().map(list -> list.toArray(new FlowRecipe[0])).toArray(FlowRecipe[][]::new);
        cachedPlan = new Plan(rawKeys, outputs, recipesByOutput, maxInputs);
        return cachedPlan;
    }
}
