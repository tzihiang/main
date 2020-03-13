package seedu.address.model.recipe.exceptions;

/**
 * Returns that the recipe already exists within the list.
 */
public class DuplicateRecipeException extends RuntimeException {
    public DuplicateRecipeException() {
        super("The recipe you are trying to add already exists");
    }
}
