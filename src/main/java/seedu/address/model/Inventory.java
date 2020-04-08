package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

/**
 * Wraps all data at the inventory level
 */
public class Inventory extends IngredientList implements ReadOnlyInventory {

    public Inventory() {}

    public Inventory(ReadOnlyInventory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    @Override
    public List<String> getIngredientNamesString() {
        return getCompatibleIngredientList().getIngredientNamesString();
    }

    /**
     * Resets the existing data of this {@code Inventory} with {@code newData}.
     * Called in constructor.
     */
    public void resetData(ReadOnlyInventory newData) {
        requireNonNull(newData);

        setIngredients(newData.getIngredientList());
    }

    @Override
    public String toString() {
        return getIngredientList().size() + " ingredients";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Inventory // instanceof handles nulls
                && this.getCompatibleIngredientList().equals(((Inventory) other).getCompatibleIngredientList()));
    }
}
