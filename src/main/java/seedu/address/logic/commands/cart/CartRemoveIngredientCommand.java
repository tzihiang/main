package seedu.address.logic.commands.cart;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;

/**
 * Removes an ingredient from the cart
 */
public class CartRemoveIngredientCommand extends CartCommand {

    public static final String COMMAND_WORD = "remove ingredient";
    public static final String MESSAGE_SUCCESS = "Ingredient removed: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + "This commands allows you to remove ingredients from your cart.\n"
            + "Parameters for removing an ingredient into your cart is as follows: \n"
            + PREFIX_INGREDIENT_NAME + "INGREDIENT "
            + "Example: " + COMMAND_CATEGORY + " "
            + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Egg " + PREFIX_INGREDIENT_QUANTITY + "10";

    private final Ingredient toRemove;

    /**
     * Creates a CartRemoveIngredientCommand to add the specified {@code Ingredient} to the cart
     */
    public CartRemoveIngredientCommand(Ingredient toRemove) {
        requireNonNull(toRemove);
        this.toRemove = toRemove;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.removeCartIngredient(toRemove);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CartRemoveIngredientCommand // instanceof handles nulls
                && toRemove.equals(((CartRemoveIngredientCommand) other).toRemove));
    }
}
