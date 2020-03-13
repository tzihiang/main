package seedu.address.model.recipe.exceptions;

public class RecipeNotFoundException extends  RuntimeException {
    public RecipeNotFoundException() {
        super("Recipe is not found in the list.");
    }
}
