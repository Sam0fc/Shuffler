package me.xemor.shuffler.game.crafting;

import me.xemor.shuffler.game.ConstantItemGenerator;
import me.xemor.shuffler.game.ProbabilityBag;

import java.util.List;
import java.util.Locale;

/**
 * Turns a bag of directly gatherable materials into a bag over every obtainable material,
 * where a higher weighting means the material is easier to obtain.
 */
public interface CraftingDifficultyModel {

    ProbabilityBag propagate(ProbabilityBag rawMaterials);

    enum Type {
        LEONTIEF, GHOSH, LOOPY_BELIEF, MAX_ENTROPY;

        public static Type fromConfig(String name) {
            if (name == null || name.isBlank()) return MAX_ENTROPY;
            return valueOf(name.trim().toUpperCase(Locale.ROOT).replace('-', '_'));
        }
    }

    static CraftingDifficultyModel create(Type type, List<ConstantItemGenerator.RecipeNode> recipes) {
        return switch (type) {
            case LEONTIEF -> new LeontiefCraftingModel(recipes);
            case GHOSH -> new GhoshCraftingModel(recipes);
            case LOOPY_BELIEF -> new LoopyBeliefCraftingModel(recipes, 10);
            case MAX_ENTROPY -> new MaxEntropyCraftingModel(recipes);
        };
    }
}
