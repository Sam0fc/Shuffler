package me.xemor.shuffler.game.crafting;

import me.xemor.shuffler.game.ProbabilityBag;

import me.xemor.shuffler.game.ConstantItemGenerator.RecipeNode;
import me.xemor.shuffler.game.ConstantItemGenerator.RecipeType;
import org.bukkit.Material;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Leontief input-output model of crafting difficulty.
 * <p>
 * Each material i has a difficulty (shadow price) p_i. Gathering it directly costs c_i = 1 / P(i), where P(i) is
 * its probability in the raw materials bag. A recipe r yielding Y_r of i, consuming n_rj of each j, defines a row
 * of the technology matrix A_rj = n_rj / Y_r, so the price of i via r is (1 + markup_r) * sum_j A_rj p_j.
 * <p>
 * The literal system p = c + A p is singular for reversible cycles (1 block = 9 diamonds gives p_d = c_d + p_d), so
 * like Leontief's non-substitution theorem each material is made the cheapest way available, gathering it directly being one option:
 * <pre>p_i = min(c_i, min_r (1 + markup_r) * sum_j A_rj p_j)</pre>
 * Once each material's cheapest way is fixed, this is exactly (I - A) p = c. It is solved by Gauss-Seidel sweeps starting from p = c,
 * which decrease monotonically to the fixed point (the Neumann series sum A^k c), so lossy or break-even cycles
 * converge and unreachable materials stay absent.
 * <p>
 * The workstation a recipe type needs (the furnace for cooking, the stonecutter for stonecutting) is capital rather
 * than an input: such a recipe only consumes 1 / toolUses of one. Recipes that use one up, like the blast furnace,
 * consume it in full.
 */
public class LeontiefCraftingModel implements CraftingDifficultyModel {

    /**
     * A recipe scaled to produce exactly 1 output: units of each input consumed per output, and the effort multiplier.
     * This is one row of the matrix A.
     */
    private record ScaledRecipe(Map<Material, Double> inputs, double markup) {
        double cost(Map<Material, Double> prices, double cheapestSoFar) {
            double cost = 0;
            for (Map.Entry<Material, Double> input : inputs.entrySet()) {
                Double price = prices.get(input.getKey());
                if (price == null) return Double.POSITIVE_INFINITY; // input cannot be obtained
                cost += input.getValue() * price;
                if (cost * markup >= cheapestSoFar) return Double.POSITIVE_INFINITY; // already worse, stop early
            }
            return cost * markup;
        }
    }

    private final Map<Material, List<ScaledRecipe>> recipesByOutput;
    private final int maxSweeps;
    private final double tolerance;

    public LeontiefCraftingModel(List<RecipeNode> recipes) {
        this(recipes, 64, 0.01, 1 / 0.97 - 1, 1000, 1e-9);
    }

    /**
     * @param toolUses how many crafts a workstation lasts, i.e. its amortisation
     * @param craftingMarkup relative effort added on top of the inputs of a crafting/stonecutting recipe
     * @param cookingMarkup relative effort added on top of the inputs of a cooking recipe (fuel, waiting)
     */
    public LeontiefCraftingModel(List<RecipeNode> recipes, double toolUses, double craftingMarkup, double cookingMarkup, int maxSweeps, double tolerance) {
        this.maxSweeps = maxSweeps;
        this.tolerance = tolerance;
        this.recipesByOutput = recipes.stream().collect(
                Collectors.groupingBy(
                    RecipeNode::material,
                    () -> new EnumMap<>(Material.class),
                    Collectors.mapping(recipe -> scale(recipe, toolUses, craftingMarkup, cookingMarkup), Collectors.toList())
                )
        );
    }

    private static ScaledRecipe scale(RecipeNode recipe, double toolUses, double craftingMarkup, double cookingMarkup) {
        Map<Material, Double> inputs = new EnumMap<>(Material.class);
        for (Map.Entry<Material, Integer> ingredient : recipe.ingredients().entrySet()) {
            Material material = ingredient.getKey();
            double consumed = material == recipe.recipeType().workstation()
                    ? ingredient.getValue() - 1 + 1 / toolUses
                    : ingredient.getValue();
            inputs.put(material, consumed / Math.max(1, recipe.amount()));
        }
        double markup = 1 + (recipe.recipeType() == RecipeType.COOKING ? cookingMarkup : craftingMarkup);
        return new ScaledRecipe(inputs, markup);
    }

    @Override
    public ProbabilityBag propagate(ProbabilityBag rawMaterials) {
        ProbabilityBag result = new ProbabilityBag();
        difficulties(rawMaterials).forEach((material, price) -> {
            if (price > 0) result.addWeighting(material, 1 / price);
        });
        return result;
    }

    /**
     * @return the difficulty of every obtainable material; materials that cannot be obtained are absent
     */
    public Map<Material, Double> difficulties(ProbabilityBag rawMaterials) {
        Map<Material, Double> gatheringCost = new EnumMap<>(Material.class);
        for (ProbabilityBag.Entry entry : rawMaterials.entries()) {
            if (entry.probability() > 0) gatheringCost.put(entry.material(), 1 / entry.probability());
        }

        Map<Material, Double> prices = new EnumMap<>(gatheringCost);
        for (int sweep = 0; sweep < maxSweeps; sweep++) {
            double maxRelativeChange = 0;
            for (Map.Entry<Material, List<ScaledRecipe>> entry : recipesByOutput.entrySet()) {
                Material output = entry.getKey();
                double best = gatheringCost.getOrDefault(output, Double.POSITIVE_INFINITY);
                for (ScaledRecipe recipe : entry.getValue()) {
                    best = Math.min(best, recipe.cost(prices, best));
                }
                if (Double.isInfinite(best)) continue;

                Double previous = prices.get(output);
                if (previous == null || best < previous) {
                    prices.put(output, best);
                    maxRelativeChange = Math.max(maxRelativeChange,
                            previous == null ? Double.POSITIVE_INFINITY : (previous - best) / previous);
                }
            }
            if (maxRelativeChange <= tolerance) break;
        }
        return prices;
    }
}
