package seedu.address.model.ingredient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the quantity of an ingredient.
 * Guarantees: immutable; is always valid
 */
public class IngredientQuantity {

    public static final String MESSAGE_CONSTRAINTS =
            "Ingredient quantities should only contain alphanumeric characters, spaces, and punctuation, "
            + "and it should start with a decimal digit";

    /*
     * The first character of the ingredient quantity must be a decimal digit.
     */
    public static final String VALIDATION_REGEX = "[\\p{Digit}][\\p{Graph} ]*";

    public final String ingredientQuantity;

    /**
     * Constructs an {@code IngredientQuantity}.
     *
     * @param ingredientQuantity A valid ingredient quantity.
     */
    public IngredientQuantity(String ingredientQuantity) {
        requireNonNull(ingredientQuantity);
        checkArgument(isValidIngredientQuantity(ingredientQuantity), MESSAGE_CONSTRAINTS);
        this.ingredientQuantity = ingredientQuantity;
    }

    /**
     * Returns true if a given string is a valid ingredient quantity.
     */
    public static boolean isValidIngredientQuantity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return ingredientQuantity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientQuantity // instanceof handles nulls
                && ingredientQuantity.toLowerCase()
                        .equals(((IngredientQuantity) other).ingredientQuantity.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return ingredientQuantity.toLowerCase().hashCode();
    }

}
