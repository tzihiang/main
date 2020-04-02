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

        if (!hasOnlyRecipePrefixAndIndex(args)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CartAddCommand.MESSAGE_USAGE));
        }

        try {
            args = removeRecipePrefix(args);
            recipeNumber = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CartAddCommand.MESSAGE_USAGE));
        }

        return new CartAddRecipeIngredientCommand(recipeNumber);
    }

    private static boolean hasOnlyRecipePrefixAndIndex(String args) {
        String[] split = args.trim().split(" ");
        return (split.length == 2) && (split[0].toLowerCase().equals("recipe"));
    }

    private String removeRecipePrefix(String args) {
        return args.trim().split(" ", 2)[1];
    }
}
