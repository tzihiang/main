package seedu.address.logic.parser.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STEP_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.recipe.RecipeRemoveCommand;
import seedu.address.logic.commands.recipe.RecipeRemoveIngredientCommand;
import seedu.address.logic.commands.recipe.RecipeRemoveStepCommand;
import seedu.address.logic.commands.recipe.RecipeRemoveTagCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new RecipeRemoveCommand object
 */
public class RecipeRemoveCommandParser implements Parser<RecipeRemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RecipeRemoveCommand
     * and returns a RecipeRemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RecipeRemoveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (containsIngredient(args)) {
            return parseRemoveIngredient(args);
        } else if (containsStep(args)) {
            return parseRemoveStep(args);
        } else if (containsTag(args)) {
            return parseRemoveTag(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecipeRemoveCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the RecipeRemoveIngredientCommand
     * and returns a RecipeRemoveIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    private RecipeRemoveIngredientCommand parseRemoveIngredient(String args) throws ParseException {
        //TODO
        throw new ParseException("method not fixed");
    }

    /**
     * Parses the given {@code String} of arguments in the context of the RecipeRemoveStepCommand
     * and returns a RecipeRemoveStepCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RecipeRemoveStepCommand parseRemoveStep(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STEP_INDEX);

        Index recipeIndex;

        try {
            recipeIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RecipeRemoveCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_STEP_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RecipeRemoveCommand.MESSAGE_USAGE));
        }

        Index stepIndex = ParserUtil.parseIndex(argMultimap
                .getValue(PREFIX_STEP_INDEX).get());

        return new RecipeRemoveStepCommand(recipeIndex, stepIndex);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the RecipeRemoveTagCommand
     * and returns a RecipeRemoveTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RecipeRemoveTagCommand parseRemoveTag(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index recipeIndex;

        try {
            recipeIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RecipeRemoveCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RecipeRemoveCommand.MESSAGE_USAGE));
        }

        Tag toRemove = ParserUtil.parseTag(argMultimap
                .getValue(PREFIX_TAG).get());

        return new RecipeRemoveTagCommand(recipeIndex, toRemove);
    }

    private boolean containsIngredient(String args) {
        return args.contains(PREFIX_INGREDIENT_NAME.toString());
    }

    private boolean containsStep(String args) {
        return args.contains(PREFIX_STEP_INDEX.toString());
    }

    private boolean containsTag(String args) {
        return args.contains(PREFIX_TAG.toString());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
