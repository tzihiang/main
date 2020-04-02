package seedu.address.model;

import java.util.List;

/**
 * Unmodifiable view of an inventory
 */
public interface ReadOnlyInventory extends ReadOnlyIngredientList {
    List<String> getIngredientNamesString();
}
