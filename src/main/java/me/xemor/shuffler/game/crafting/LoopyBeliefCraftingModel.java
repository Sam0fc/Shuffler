package me.xemor.shuffler.game.crafting;

import me.xemor.shuffler.game.ProbabilityBag;

import me.xemor.shuffler.game.ConstantItemGenerator.RecipeNode;
import me.xemor.shuffler.game.ConstantItemGenerator.RecipeType;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoopyBeliefCraftingModel implements CraftingDifficultyModel {

    private final Map<Material, List<RecipeNode>> recipesByResult;
    private final int iterations;

    public LoopyBeliefCraftingModel(List<RecipeNode> recipes, int iterations) {
        // Group recipes by their output Material to evaluate them together
        this.recipesByResult = recipes.stream().collect(Collectors.groupingBy(RecipeNode::material));
        this.iterations = iterations;
    }

    @Override
    public ProbabilityBag propagate(ProbabilityBag rootBag) {
        ProbabilityBag startingBag = new ProbabilityBag();
        for (ProbabilityBag.Entry entry : rootBag.entries()) {
            startingBag.addWeighting(entry.material(), entry.probability());
        }
        ProbabilityBag currentState = new ProbabilityBag().addWeighting(startingBag);

        // Iterate to propagate probabilities through loops
        for (int i = 0; i < iterations; i++) {
            ProbabilityBag nextState = new ProbabilityBag().addWeighting(startingBag);

            for (Map.Entry<Material, List<RecipeNode>> entry : recipesByResult.entrySet()) {
                Material resultMaterial = entry.getKey();
                double recipeWeightSum = 0D;

                for (RecipeNode recipe : entry.getValue()) {
                    // Re-using your exact Vibe Math, but pulling from currentState
                    Map<Material, Integer> groupedIngredients = recipe.ingredients();

                    if (!currentState.containsAll(groupedIngredients.keySet())) {
                        continue;
                    }

                    final ProbabilityBag effectivelyFinalCurrentState = currentState;
                    // Maybe I should do a check to see if they're 'crafting independent' or not
                    double independentP = groupedIngredients.entrySet().stream()
                            .map(it -> effectivelyFinalCurrentState.get(it.getKey()) / it.getValue())
                            .reduce(1D, (x, y) -> x * y);

                    if (independentP == 1D) independentP = 0D;

                    double minP = groupedIngredients.keySet().stream()
                            .map(currentState::get)
                            .reduce(Double.MAX_VALUE, Math::min);

                    if (minP == Double.MAX_VALUE) minP = 0D;

                    double p = (minP * 0.9 + 0.1 * independentP);
                    if (recipe.recipeType() == RecipeType.COOKING) {
                        p *= 0.97;
                    }
                    p *= recipe.amount();

                    // Sum to better represent the difficulty of e.g sticks, rather than taking max.
                    recipeWeightSum += p;
                }

                if (recipeWeightSum > 0) {
                    // Update the next state with the best crafted weight
                    nextState.maxWeighting(resultMaterial, recipeWeightSum);
                }
            }
            currentState = nextState; // Move to next time step
        }
        return currentState;
    }
}
