package seedu.address.model;

/**
 * Wraps all data at the inventory level
 * Duplicates are not allowed
 */
public class Inventory extends IngredientList implements ReadOnlyInventory {

    public Inventory() {}

    public Inventory(ReadOnlyInventory toBeCopied) {
        super(toBeCopied);
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
                && this.getUniqueIngredientList().equals(((Inventory) other).getUniqueIngredientList()));
    }
}
