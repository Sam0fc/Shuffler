package me.xemor.shuffler.game.crafting;

import me.xemor.shuffler.game.ConstantItemGenerator;
import me.xemor.shuffler.game.ConstantItemGenerator.RecipeNode;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A recipe split into the ingredients it uses up (count per craft) and the workstations it merely needs, by Material ordinal.
 * The workstation a recipe type needs (the furnace for cooking, the stonecutter for stonecutting) lasts forever once
 * obtained, so it must be obtainable for the recipe to run but none of its weight is consumed. Recipes that use one
 * up, like the blast furnace, consume it as a normal ingredient. A cooking recipe is one smelt in a furnace.
 */
record FlowRecipe(int output, int[] inputs, int[] counts, int[] workstations, int amount, boolean smelts) {

    /**
     * @return the recipes that use something up
     */
    static List<FlowRecipe> from(List<RecipeNode> recipes) {
        return recipes.stream().map(FlowRecipe::from).filter(recipe -> recipe.inputs().length > 0).toList();
    }

    private static FlowRecipe from(RecipeNode recipe) {
        List<Integer> inputs = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        List<Integer> workstations = new ArrayList<>();
        for (Map.Entry<Material, Integer> ingredient : recipe.ingredients().entrySet()) {
            Material material = ingredient.getKey();
            int count = ingredient.getValue();
            if (material == recipe.recipeType().workstation()) {
                workstations.add(material.ordinal());
                count--;
            }
            if (count > 0) {
                inputs.add(material.ordinal());
                counts.add(count);
            }
        }
        return new FlowRecipe(recipe.material().ordinal(),
                inputs.stream().mapToInt(Integer::intValue).toArray(),
                counts.stream().mapToInt(Integer::intValue).toArray(),
                workstations.stream().mapToInt(Integer::intValue).toArray(),
                Math.max(1, recipe.amount()),
                recipe.recipeType() == ConstantItemGenerator.RecipeType.COOKING);
    }

    /**
     * @return the recipes whose ingredients and workstations can all be obtained from the raw materials
     */
    static List<FlowRecipe> craftable(List<FlowRecipe> recipes, boolean[] rawKeys) {
        boolean[] obtainable = rawKeys.clone();
        boolean[] done = new boolean[recipes.size()];
        List<FlowRecipe> craftable = new ArrayList<>();
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int r = 0; r < done.length; r++) {
                if (done[r]) continue;
                FlowRecipe recipe = recipes.get(r);
                if (all(obtainable, recipe.inputs()) && all(obtainable, recipe.workstations())) {
                    done[r] = true;
                    craftable.add(recipe);
                    if (!obtainable[recipe.output()]) {
                        obtainable[recipe.output()] = true;
                        changed = true;
                    }
                }
            }
        }
        return craftable;
    }

    private static boolean all(boolean[] flags, int[] indices) {
        for (int index : indices) {
            if (!flags[index]) return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return this == other;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(inputs) * 31 + output;
    }
}
