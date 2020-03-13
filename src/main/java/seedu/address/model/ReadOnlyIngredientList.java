package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.ingredient.Ingredient;

/**
 * Unmodifiable view of inventory
 */
public interface ReadOnlyIngredientList {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Ingredient> getIngredientList();
}
