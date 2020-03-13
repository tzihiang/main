package seedu.address.model.recipe.exceptions;

public class DuplicateRecipeException extends RuntimeException {
    public DuplicateRecipeException() {
        super("The recipe you are trying to add already exists");
    }
}
