package seedu.address.model.recipe;

import java.util.Comparator;

import seedu.address.model.Inventory;

/**
 * A comparator for {@code Recipe}, which compares recipes by their similarity to an inventory's ingredients.
 */
public class RecipeInventorySimilarityComparator implements Comparator<Recipe> {
    private final Inventory inventory;

    public RecipeInventorySimilarityComparator(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Compares recipes by their similarity to an inventory's ingredients.
     */
    @Override
    public int compare(Recipe o1, Recipe o2) {
        return Double.compare(inventory.calculateSimilarity(o1), inventory.calculateSimilarity(o2));
    }
}
