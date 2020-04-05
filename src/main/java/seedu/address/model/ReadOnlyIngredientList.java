package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.ingredient.CompatibleIngredientList;
import seedu.address.model.ingredient.Ingredient;

/**
 * Unmodifiable view of an ingredient list
 */
public interface ReadOnlyIngredientList {
    CompatibleIngredientList getCompatibleIngredientList();

    /**
     * Returns an unmodifiable view of the ingredients list.
     * This list will not contain any duplicate ingredients.
     */
    ObservableList<Ingredient> getIngredientList();
}
