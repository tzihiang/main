package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Recipe;

/**
 * Unmodifiable view of an inventory
 */
public interface ReadOnlyInventory {
    /**
     * Returns an unmodifiable view of the inventory.
     */
    ObservableList<Ingredient> getIngredientList();

    /**
     * Returns a double value between 0 and 1, describing the proportion of {@code recipe} ingredients that the
     * inventory contains.
     */
    double calculateSimilarity(Recipe recipe);
}
