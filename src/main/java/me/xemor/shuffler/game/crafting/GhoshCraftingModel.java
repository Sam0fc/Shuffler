package me.xemor.shuffler.game.crafting;

import me.xemor.shuffler.game.ProbabilityBag;

import me.xemor.shuffler.game.ConstantItemGenerator.RecipeNode;
import org.bukkit.Material;

import java.util.*;

/**
 * Ghosh (supply-side) input-output model of crafting difficulty, which conserves weight.
 * <p>
 * The raw materials bag is a fixed budget of weight that flows forward through recipes and is never created. Every
 * unit of weight remembers the raw material (the primitive) it came from, so the weight of oak logs plus everything
 * made from them always equals the weight oak logs started with, and a new recipe can only move weight around.
 * <p>
 * A material with n distinct outputs splits all the weight that reaches it evenly into n + 1 shares: one it keeps and
 * one offered to each material that recipes make from it (shared evenly between the recipes for that material). Planks
 * that make chests and sticks keep a third and offer a third to each, so a crafting step costs nothing by itself;
 * weight is only divided by how many things a material can become. A material nothing is made from keeps everything.
 * <p>
 * A recipe needs all of its ingredients, so it is limited by how long it takes to collect them one after another.
 * But an ingredient can come from several places: a stick is partly a leaf drop and partly planks, and the planks are
 * partly oak and partly birch. So the recipe is worked out once for every combination of origins its ingredients could
 * have, and the results are added together, since any one combination is enough. A combination is weighted by q, the
 * chance of drawing that combination (the product of each ingredient's fraction from its origin), and sees ingredient
 * j offering o_j * q. Within a combination, ingredients with the same origin draw on one supply: planks and sticks
 * both made from oak logs are combined rather than counted as two separate chores. For each distinct origin p in the
 * combination, A_p is the units of ingredient needed from p and R_p the weight p offers, and the combination makes
 * M = 1 / sum_p(A_p / R_p). That is never more than the supply of any origin it needs, falls with every extra origin,
 * and with one ingredient is just o / n. Origin p pays (A_p / R_p) * M^2, shared between the ingredients drawing on it
 * in proportion to how much they offered, so the payments sum to exactly the weight made. Combinations use disjoint
 * slices of each ingredient, and whatever an ingredient offered but did not pay stays with it. Only the largest few
 * origins of each ingredient are tried, to bound the work; the rest of its weight is simply left unused.
 * <p>
 * With T_i(p) the weight of primitive p that ever reached material i, T = raw + inflow(T). This is solved by
 * Gauss-Seidel sweeps from T = raw, which converge because each material keeps a share, so weight going around a
 * cycle (block to diamonds to block) leaks away each lap. A material's final weight is what reached it minus what its
 * recipes consumed.
 * <p>
 * Everything in the solve loop works on plain arrays indexed by Material ordinal, because this runs on the server thread
 * every time a level starts. Each item's largest origins are sorted once whenever the item changes, and a recipe is only
 * worked out again when one of its ingredients has changed.
 * <p>
 * The workstation a recipe type needs (the furnace for cooking, the stonecutter for stonecutting) lasts forever once
 * obtained, so it must be obtainable for the recipe to run but none of its weight is consumed. Recipes that use one
 * up, like the blast furnace, consume it as a normal ingredient.
 */
public class GhoshCraftingModel implements CraftingDifficultyModel {

    private static final Material[] MATERIALS = Material.values();
    private static final int MATERIAL_COUNT = MATERIALS.length;
    /** Most origins of one ingredient tried per recipe. */
    private static final int MAX_ORIGINS = Lineage.MAX_ORIGINS;
    /** Most combinations of origins tried per recipe; recipes with many ingredients try fewer origins of each. */
    private static final int MAX_COMBINATIONS = 512;
    /** Origins supplying less than this fraction of an ingredient's weight are ignored. */
    private static final double MIN_ORIGIN_FRACTION = 1e-4;
    /** An item has changed enough for the recipes using it to be worked out again. */
    private static final double CHANGED = 1e-12;

    /**
     * The weight of every material, and which primitive each unit of that weight came from.
     */
    public record Solution(Map<Material, Double> weights, Map<Material, Map<Material, Double>> weightsByPrimitive) {}

    /**
     * A recipe that can actually be crafted, with the share of each ingredient's weight it is offered, and the weight
     * it produced last time it was worked out.
     */
    private static final class Allocation {
        final FlowRecipe recipe;
        final double[] shares;
        long seen = -1;
        int[] producedPrims = new int[0];
        double[] producedWeights = new double[0];

        Allocation(FlowRecipe recipe, double[] shares) {
            this.recipe = recipe;
            this.shares = shares;
        }
    }

    /**
     * Everything that depends only on which raw materials exist: the craftable recipes, grouped by output.
     */
    private record Plan(boolean[] rawKeys, int[] outputs, Allocation[][] allocationsByOutput, int maxInputs) {}

    /**
     * Reused arrays for working out recipes.
     */
    private static final class Scratch {
        final double[] contribution = new double[MATERIAL_COUNT];
        final int[] touched = new int[MATERIAL_COUNT];
        int touchedCount = 0;
        final double[] previous = new double[MATERIAL_COUNT];
        final double[] offered;
        final int[] originCount, choice, distinctPrim;
        final double[] distinctNeeded, distinctOffered;

        Scratch(int maxInputs) {
            offered = new double[maxInputs];
            originCount = new int[maxInputs];
            choice = new int[maxInputs];
            distinctPrim = new int[maxInputs];
            distinctNeeded = new double[maxInputs];
            distinctOffered = new double[maxInputs];
        }

        void add(int primitive, double weight) {
            if (contribution[primitive] == 0) touched[touchedCount++] = primitive;
            contribution[primitive] += weight;
        }
    }

    private final List<FlowRecipe> recipes;
    private final int maxSweeps;
    private final double tolerance;
    private Plan cachedPlan = null;

    public GhoshCraftingModel(List<RecipeNode> recipes) {
        this(recipes, 1000, 1e-9);
    }

    public GhoshCraftingModel(List<RecipeNode> recipes, int maxSweeps, double tolerance) {
        this.maxSweeps = maxSweeps;
        this.tolerance = tolerance;
        this.recipes = FlowRecipe.from(recipes);
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
     * @return the final weight of every material, summing to the total raw weight
     */
    public Map<Material, Double> weights(ProbabilityBag rawMaterials) {
        return solve(rawMaterials, false).weights();
    }

    public Solution solve(ProbabilityBag rawMaterials) {
        return solve(rawMaterials, true);
    }

    private synchronized Solution solve(ProbabilityBag rawMaterials, boolean byPrimitive) {
        double[] raw = new double[MATERIAL_COUNT];
        boolean[] rawKeys = new boolean[MATERIAL_COUNT];
        for (ProbabilityBag.Entry entry : rawMaterials.entries()) {
            if (entry.probability() > 0) {
                raw[entry.material().ordinal()] = entry.probability();
                rawKeys[entry.material().ordinal()] = true;
            }
        }

        Plan plan = planFor(rawKeys);
        for (Allocation[] allocations : plan.allocationsByOutput()) {
            for (Allocation allocation : allocations) allocation.seen = -1;
        }
        Scratch scratch = new Scratch(plan.maxInputs());

        Lineage[] lineages = new Lineage[MATERIAL_COUNT];
        long[] versions = new long[MATERIAL_COUNT];
        for (int material = 0; material < MATERIAL_COUNT; material++) {
            if (rawKeys[material]) lineages[material] = new Lineage(new int[]{material}, new double[]{raw[material]});
        }

        for (int sweep = 0; sweep < maxSweeps; sweep++) {
            double maxRelativeChange = 0;
            for (int group = 0; group < plan.outputs().length; group++) {
                int output = plan.outputs()[group];
                for (Allocation allocation : plan.allocationsByOutput()[group]) {
                    long seen = 0;
                    for (int input : allocation.recipe.inputs()) seen += versions[input] + 1;
                    if (allocation.seen != seen) {
                        evaluate(allocation, lineages, null, scratch, true);
                        allocation.seen = seen;
                    }
                }
                scratch.touchedCount = 0;
                if (rawKeys[output]) scratch.add(output, raw[output]);
                for (Allocation allocation : plan.allocationsByOutput()[group]) {
                    for (int i = 0; i < allocation.producedPrims.length; i++) {
                        scratch.add(allocation.producedPrims[i], allocation.producedWeights[i]);
                    }
                }
                if (scratch.touchedCount == 0) continue;

                int[] prims = Arrays.copyOf(scratch.touched, scratch.touchedCount);
                double[] weights = new double[prims.length];
                for (int i = 0; i < prims.length; i++) {
                    weights[i] = scratch.contribution[prims[i]];
                    scratch.contribution[prims[i]] = 0;
                }
                scratch.touchedCount = 0;
                Lineage updated = new Lineage(prims, weights);
                if (updated.total <= 0) continue;

                double change = Lineage.difference(lineages[output], updated, scratch.previous);
                maxRelativeChange = Math.max(maxRelativeChange, change);
                if (change > CHANGED) versions[output]++;
                lineages[output] = updated;
            }
            if (maxRelativeChange <= tolerance) break;
        }

        // What each material ends up with is everything that reached it minus what recipes took from it. Each primitive's
        // payment comes out of the ingredients that offered it, so the same amount is subtracted as was added to the output.
        double[][] remaining = new double[MATERIAL_COUNT][];
        for (int material = 0; material < MATERIAL_COUNT; material++) {
            if (lineages[material] != null) remaining[material] = lineages[material].weights.clone();
        }
        for (Allocation[] allocations : plan.allocationsByOutput()) {
            for (Allocation allocation : allocations) evaluate(allocation, lineages, remaining, scratch, false);
        }

        Map<Material, Double> weights = new EnumMap<>(Material.class);
        Map<Material, Map<Material, Double>> weightsByPrimitive = byPrimitive ? new EnumMap<>(Material.class) : null;
        for (int material = 0; material < MATERIAL_COUNT; material++) {
            if (lineages[material] == null) continue;
            double weight = 0;
            for (double value : remaining[material]) weight += value;
            weights.put(MATERIALS[material], weight);
            if (byPrimitive) {
                Map<Material, Double> origins = new EnumMap<>(Material.class);
                for (int i = 0; i < remaining[material].length; i++) origins.put(MATERIALS[lineages[material].prims[i]], remaining[material][i]);
                weightsByPrimitive.put(MATERIALS[material], origins);
            }
        }
        return new Solution(weights, weightsByPrimitive);
    }

    /**
     * Works out a recipe with the current weights. It is tried once for every combination of origins its ingredients
     * could have, with each combination seeing the ingredients' offers scaled by the chance of that combination.
     * <p>
     * If {@code remaining} is null the weight made is stored on the allocation, otherwise what the recipe uses up is
     * taken out of {@code remaining}.
     */
    private void evaluate(Allocation allocation, Lineage[] lineages, double[][] remaining, Scratch scratch, boolean store) {
        FlowRecipe recipe = allocation.recipe;
        int inputCount = recipe.inputs().length;
        int originLimit = Math.max(1, Math.min(MAX_ORIGINS, (int) Math.floor(Math.pow(MAX_COMBINATIONS, 1D / inputCount))));
        for (int i = 0; i < inputCount; i++) {
            Lineage lineage = lineages[recipe.inputs()[i]];
            if (lineage == null || lineage.total <= 0) {
                clear(allocation, store);
                return;
            }
            scratch.offered[i] = allocation.shares[i] * lineage.total;
            int count = 0;
            while (count < Math.min(originLimit, lineage.topCount) && lineage.topFraction[count] >= MIN_ORIGIN_FRACTION) count++;
            if (count == 0) {
                clear(allocation, store);
                return;
            }
            scratch.originCount[i] = count;
            scratch.choice[i] = 0;
        }

        scratch.touchedCount = 0;
        boolean more = true;
        while (more) {
            double chance = 1;
            int distinct = 0;
            for (int i = 0; i < inputCount; i++) {
                Lineage lineage = lineages[recipe.inputs()[i]];
                chance *= lineage.topFraction[scratch.choice[i]];
                int primitive = lineage.topPrimitive(scratch.choice[i]);
                int slot = 0;
                while (slot < distinct && scratch.distinctPrim[slot] != primitive) slot++;
                if (slot == distinct) {
                    scratch.distinctPrim[slot] = primitive;
                    scratch.distinctNeeded[slot] = 0;
                    scratch.distinctOffered[slot] = 0;
                    distinct++;
                }
                scratch.distinctNeeded[slot] += recipe.counts()[i];
                scratch.distinctOffered[slot] += scratch.offered[i];
            }

            double timePerUnit = 0;
            for (int slot = 0; slot < distinct; slot++) {
                timePerUnit += scratch.distinctNeeded[slot] / (chance * scratch.distinctOffered[slot]);
            }
            double mass = 1 / timePerUnit;
            if (Double.isFinite(mass) && mass > 0) {
                for (int slot = 0; slot < distinct; slot++) {
                    int primitive = scratch.distinctPrim[slot];
                    double payment = scratch.distinctNeeded[slot] / (chance * scratch.distinctOffered[slot]) * mass * mass;
                    if (store) {
                        scratch.add(primitive, payment);
                    } else {
                        for (int i = 0; i < inputCount; i++) {
                            Lineage lineage = lineages[recipe.inputs()[i]];
                            if (lineage.topPrimitive(scratch.choice[i]) != primitive) continue;
                            remaining[recipe.inputs()[i]][lineage.topPosition[scratch.choice[i]]] -=
                                    payment * scratch.offered[i] / scratch.distinctOffered[slot];
                        }
                    }
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

        if (store) {
            allocation.producedPrims = Arrays.copyOf(scratch.touched, scratch.touchedCount);
            allocation.producedWeights = new double[scratch.touchedCount];
            for (int i = 0; i < scratch.touchedCount; i++) {
                allocation.producedWeights[i] = scratch.contribution[scratch.touched[i]];
                scratch.contribution[scratch.touched[i]] = 0;
            }
            scratch.touchedCount = 0;
        }
    }

    private static void clear(Allocation allocation, boolean store) {
        if (!store) return;
        allocation.producedPrims = new int[0];
        allocation.producedWeights = new double[0];
    }

    /**
     * @return the recipes that can be crafted from the raw materials, with each ingredient's weight split between them
     */
    private Plan planFor(boolean[] rawKeys) {
        if (cachedPlan != null && Arrays.equals(cachedPlan.rawKeys(), rawKeys)) return cachedPlan;

        List<FlowRecipe> craftable = FlowRecipe.craftable(recipes, rawKeys);

        // Each ingredient's weight is split evenly between itself and the materials it can be crafted into,
        // then evenly between the recipes for each of those materials.
        Map<Long, Integer> recipeCounts = new HashMap<>();
        int[] outputCounts = new int[MATERIAL_COUNT];
        for (FlowRecipe recipe : craftable) {
            for (int input : recipe.inputs()) {
                if (recipeCounts.merge((long) input * MATERIAL_COUNT + recipe.output(), 1, Integer::sum) == 1) outputCounts[input]++;
            }
        }

        Map<Integer, List<Allocation>> byOutput = new TreeMap<>();
        int maxInputs = 1;
        for (FlowRecipe recipe : craftable) {
            double[] shares = new double[recipe.inputs().length];
            for (int i = 0; i < shares.length; i++) {
                shares[i] = 1D / ((outputCounts[recipe.inputs()[i]] + 1) * recipeCounts.get((long) recipe.inputs()[i] * MATERIAL_COUNT + recipe.output()));
            }
            byOutput.computeIfAbsent(recipe.output(), _ -> new ArrayList<>()).add(new Allocation(recipe, shares));
            maxInputs = Math.max(maxInputs, shares.length);
        }
        int[] outputs = byOutput.keySet().stream().mapToInt(Integer::intValue).toArray();
        Allocation[][] allocations = byOutput.values().stream().map(list -> list.toArray(new Allocation[0])).toArray(Allocation[][]::new);
        cachedPlan = new Plan(rawKeys, outputs, allocations, maxInputs);
        return cachedPlan;
    }
}
