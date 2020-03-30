package seedu.address.logic.commands.cart;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds all the ingredients from the indexed recipe to cart
 */
public class CartAddRecipeIngredientCommand extends CartAddCommand {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_SUCCESS = "Ingredients from recipe %1$ added.";
    public static final String MESSAGE_USAGE = "\n" + COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": This commands allows you to add all the ingredients from a recipe to your cart.\n"
            + "Parameters for adding an ingredient into your cart is as follows: \n"
            + PREFIX_INGREDIENT_NAME + "INGREDIENT "
            + PREFIX_INGREDIENT_QUANTITY + "QUANTITY\n"
            + "Example: " + COMMAND_CATEGORY + " " + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Eggs "
            + PREFIX_INGREDIENT_QUANTITY + "10\n";

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
