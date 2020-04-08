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
     * Returns the proportion of {@code recipe} ingredients that the inventory contains.
     * @param recipe the specified recipe
     * @return a double value between 0 and 1 (inclusive)
     */
    double calculateSimilarity(Recipe recipe);
}
