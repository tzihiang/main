package seedu.address.model.ingredient.exceptions;

/**
 * Returns that the ingredient already exists within the list.
 */
public class DuplicateIngredientException extends RuntimeException {
    public DuplicateIngredientException() {
        super("The ingredient you are trying to add already exists");
    }

}
