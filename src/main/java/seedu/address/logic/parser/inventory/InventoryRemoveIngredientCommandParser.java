package seedu.address.logic.parser.inventory;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;

import java.util.Optional;

import seedu.address.logic.commands.inventory.InventoryCommand;
import seedu.address.logic.commands.inventory.InventoryRemoveIngredientCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;

/**
 * Parses input arguments and creates a new InventoryRemoveIngredientCommand object
 */
public class InventoryRemoveIngredientCommandParser implements Parser<InventoryCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the InventoryCommand
     * and returns a InventoryRemoveIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InventoryRemoveIngredientCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY);

        if (!argMultimap.arePrefixesPresent(PREFIX_INGREDIENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InventoryRemoveIngredientCommand.MESSAGE_USAGE));
        }

        IngredientName ingredientName = ParserUtil.parseIngredientName(argMultimap
                .getValue(PREFIX_INGREDIENT_NAME).get());

        Optional<IngredientQuantity> ingredientQuantity = argMultimap.arePrefixesPresent(PREFIX_INGREDIENT_QUANTITY)
                ? Optional.of(ParserUtil.parseIngredientQuantity(argMultimap.getValue(PREFIX_INGREDIENT_QUANTITY)
                    .get()))
                : Optional.empty();

        return new InventoryRemoveIngredientCommand(ingredientName, ingredientQuantity);
    }
}
