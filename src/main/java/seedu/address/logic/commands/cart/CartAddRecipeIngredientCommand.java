package seedu.address.logic.commands.cart;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds all the ingredients from the indexed recipe to cart
 */
public class CartAddRecipeIngredientCommand extends CartAddCommand {

    public static final String MESSAGE_SUCCESS = "Ingredients from recipe %1$ added.";

    private final int recipeToAdd;

    /**
     * Creates a CartAddIngredientCommand to add the specified {@code Ingredient} to the cart
     */
    public CartAddRecipeIngredientCommand(int recipeToAdd) {
        requireNonNull(recipeToAdd);
        this.recipeToAdd = recipeToAdd;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO: Implement method.
        return new CommandResult(String.format(MESSAGE_SUCCESS, recipeToAdd));
    }
}
