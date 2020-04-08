package seedu.address.model;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.ingredient.Ingredient;

/**
 * Unmodifiable view of an inventory
 */
public interface ReadOnlyInventory {
    List<String> getIngredientNamesString();

    /**
     * Returns an unmodifiable view of the inventory.
     */
    ObservableList<Ingredient> getIngredientList();
}
