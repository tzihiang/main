package seedu.address.logic.parser.cart;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.cart.CartAddIngredientCommand;
import seedu.address.logic.commands.cart.CartCommand;
import seedu.address.logic.commands.cart.CartRemoveIngredientCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
* Parses input arguments and into a {@code CartCommand}.
 */
public class CartCommandParser implements Parser<CartCommand> {

    private static final Pattern CART_COMMAND_ARGUMENT_FORMAT = Pattern
            .compile(" *(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of a CartCommand
     * and returns a CartCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CartCommand parse(String args) throws ParseException {
        final Matcher matcher = CART_COMMAND_ARGUMENT_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        // For now, implementation will only be done for the whole ingredient, and not quantity
        // TODO: Implement optional argument for parser.
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case CartAddIngredientCommand.COMMAND_WORD:
            //TODO : return new CartAddIngredientCommandParser().parse(arguments);
        case CartRemoveIngredientCommand.COMMAND_WORD:
            //TODO: return new CartRemoveIngredientCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
