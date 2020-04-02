package seedu.address.logic.parser.cart;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;

import seedu.address.logic.commands.cart.CartAddCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CartAddCommand object
 */
public class CartAddCommandParser implements Parser<CartAddCommand> {

    public static final String RECIPE_STRING = "recipe";

    /**
     * Parses the given {@code String} of arguments in the context of the CartAddCommand
     * and returns a CartAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CartAddCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (containsIngredient(args)) {
            return new CartAddIngredientCommandParser().parse(args);
        } else if (containsRecipe(args)) {
            return new CartAddRecipeIngredientCommandParser().parse(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CartAddCommand.MESSAGE_USAGE));
        }
    }

    // TODO improve the parsing method
    boolean containsRecipe(String args) {
        requireNonNull(args);
        return args.contains(RECIPE_STRING);
    }

    boolean containsIngredient(String args) {
        requireNonNull(args);
        return args.contains(PREFIX_INGREDIENT_NAME.toString())
                && args.contains(PREFIX_INGREDIENT_QUANTITY.toString());
    }
}
