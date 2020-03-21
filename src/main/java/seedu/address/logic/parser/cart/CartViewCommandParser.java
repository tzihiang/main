package seedu.address.logic.parser.cart;

import seedu.address.logic.commands.cart.CartCommand;
import seedu.address.logic.commands.cart.CartViewCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class CartViewCommandParser implements Parser<CartCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CartCommand
     * and returns a CartAddIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CartViewCommand parse(String args) throws ParseException {
        throw new ParseException("Not implemented yet");
    }
}
