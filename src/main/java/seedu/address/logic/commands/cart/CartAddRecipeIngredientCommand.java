package seedu.address.logic.commands.cart;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds all the ingredients from the indexed recipe to cart
 */
public class CartAddRecipeIngredientCommand extends CartCommand {

    public static final String COMMAND_WORD = "recipe";
    public static final String MESSAGE_SUCCESS = "All ingredients from recipe %1$s added.";
    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": This commands allows you to add all the ingredients from a recipe to your cart.\n"
            + "Parameters for adding all ingredients in the recipe into your cart is as follows: \n"
            + COMMAND_CATEGORY + " " + COMMAND_WORD + "INDEX\n"
            + "Example: " + COMMAND_CATEGORY + " "
            + COMMAND_WORD + "1\n";

    final int recipeToAdd;

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
        return null;
    }
}
