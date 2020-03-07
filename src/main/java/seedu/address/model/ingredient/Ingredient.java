package seedu.address.model.ingredient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an ingredient.
 * Guarantees: immutable; is valid as declared in {@link #isValidIngredient(String)}
 */
public class Ingredient {

    public static final String MESSAGE_CONSTRAINTS =
            "Ingredients should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the ingredient must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String ingredientName;

    /**
     * Constructs an {@code Ingredient}.
     *
     * @param ingredient A valid ingredient.
     */
    public Ingredient(String ingredient) {
        requireNonNull(ingredient);
        checkArgument(isValidIngredient(ingredient), MESSAGE_CONSTRAINTS);
        ingredientName = ingredient;
    }

    /**
     * Returns true if a given string is a valid ingredient.
     */
    public static boolean isValidIngredient(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return ingredientName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ingredient // instanceof handles nulls
                && ingredientName.equals(((Ingredient) other).ingredientName)); // state check
    }

    @Override
    public int hashCode() {
        return ingredientName.hashCode();
    }

}
