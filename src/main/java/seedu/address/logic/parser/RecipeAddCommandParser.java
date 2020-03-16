package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STEP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STEP_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.recipe.RecipeAddCommand;
import seedu.address.logic.commands.recipe.RecipeAddIngredientCommand;
import seedu.address.logic.commands.recipe.RecipeAddStepCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;

/**
 * Parses input arguments and creates a new RecipeAddCommand object
 */
public class RecipeAddCommandParser implements Parser<RecipeAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RecipeAddCommand
     * and returns a RecipeAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RecipeAddCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (containsIngredient(args)) {
            return parseAddIngredient(args);
        } else if (containsStep(args)) {
            return parseAddStep(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecipeAddCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the RecipeAddIngredientCommand
     * and returns a RecipeAddIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    private RecipeAddIngredientCommand parseAddIngredient(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RecipeAddCommand.MESSAGE_USAGE), pe);
        }

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

        return new RecipeAddIngredientCommand(index, ingredient);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the RecipeAddStepCommand
     * and returns a RecipeAddStepCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RecipeAddStepCommand parseAddStep(String args) throws ParseException {
        // TODO: implement
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RecipeAddCommand.MESSAGE_USAGE));
    }

    private boolean containsIngredient(String args) {
        return args.contains(PREFIX_INGREDIENT_NAME.toString())
                && args.contains(PREFIX_INGREDIENT_QUANTITY.toString());
    }

    private boolean containsStep(String args) {
        return args.contains(PREFIX_STEP_INDEX.toString())
                && args.contains(PREFIX_STEP_DESCRIPTION.toString());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
