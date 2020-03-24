package seedu.address.logic.parser.cart;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.cart.CartAddCommand;
import seedu.address.logic.commands.cart.CartAddRecipeIngredientCommand;
import seedu.address.logic.commands.cart.CartCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CartAddRecipeIngredientCommand object
 */
public class CartAddRecipeIngredientCommandParser implements Parser<CartCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CartCommand
     * and returns a CartAddRecipeIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CartAddRecipeIngredientCommand parse(String args) throws ParseException {

        requireNonNull(args);

        int recipeNumber;

        try {
            recipeNumber = Integer.parseInt(args);
        } catch (NumberFormatException ne) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CartAddCommand.MESSAGE_USAGE));
        }

        return new CartAddRecipeIngredientCommand(recipeNumber);
    }

}
