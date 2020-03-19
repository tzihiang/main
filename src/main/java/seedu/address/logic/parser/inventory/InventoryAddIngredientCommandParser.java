package seedu.address.logic.parser.inventory;

import seedu.address.logic.commands.inventory.InventoryAddIngredientCommand;
import seedu.address.logic.commands.inventory.InventoryCommand;
import seedu.address.logic.commands.recipe.RecipeAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;

/**
 * Parses input arguments and creates a new InventoryAddIngredientCommand object
 */
public class InventoryAddIngredientCommandParser implements Parser<InventoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InventoryCommand
     * and returns a InventoryAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InventoryAddIngredientCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (containsIngredient(args)) {
            return parseAddIngredient(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecipeAddCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the RecipeAddIngredientCommand
     * and returns a RecipeAddIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    private InventoryAddIngredientCommand parseAddIngredient(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_INGREDIENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RecipeAddCommand.MESSAGE_USAGE));
        }

        IngredientName ingredientName = ParserUtil.parseIngredientName(argMultimap
                .getValue(PREFIX_INGREDIENT_NAME).get());
        IngredientQuantity ingredientQuantity = ParserUtil.parseIngredientQuantity(argMultimap
                .getValue(PREFIX_INGREDIENT_QUANTITY).get());

        Ingredient ingredient = new Ingredient(ingredientName, ingredientQuantity);

        return new InventoryAddIngredientCommand(ingredient);
    }

    private boolean containsIngredient(String args) {
        return args.contains(PREFIX_INGREDIENT_NAME.toString())
                && args.contains(PREFIX_INGREDIENT_QUANTITY.toString());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
