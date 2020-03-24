package seedu.address.logic.parser.cart;

import seedu.address.logic.commands.cart.CartClearCommand;
import seedu.address.logic.commands.cart.CartCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class CartClearCommandParser implements Parser<CartCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CartCommand
     * and returns a CartRemoveIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public CartClearCommand parse(String userInput) throws ParseException {
        if (userInput.isEmpty()) {
            return new CartClearCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CartClearCommand.MESSAGE_USAGE));
        }
    }
}
