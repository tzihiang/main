package seedu.address.model;

/**
 * Wraps all data at the cart level
 * Duplicates are not allowed
 */
public class Cart extends IngredientList implements ReadOnlyCart {

    public Cart() {}

    public Cart(ReadOnlyCart toBeCopied) {
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
                || (other instanceof Cart // instanceof handles nulls
                && this.getCompatibleIngredientList().equals(((Cart) other).getCompatibleIngredientList()));
    }

}
