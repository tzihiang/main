package seedu.address.logic.parser.cookbook;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_KEYWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.cookbook.CookbookSearchByInventoryCommand;
import seedu.address.logic.commands.cookbook.CookbookSearchByKeywordCommand;
import seedu.address.logic.commands.cookbook.CookbookSearchByTagCommand;
import seedu.address.logic.commands.cookbook.CookbookSearchCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.RecipeContainsTagsPredicate;
import seedu.address.model.recipe.RecipeNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new CookbookSearchCommand object
 */
public class CookbookSearchCommandParser implements Parser<CookbookSearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CookbookSearchCommand
     * and returns a CookbookSearchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CookbookSearchCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (containsKeyword(args)) {
            return parseSearchByKeyword(args);
        } else if (containsTag(args)) {
            return parseSearchByTag(args);
        } else if (containsInventoryKeyword(args)) {
            return parseSearchByInventory(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CookbookSearchCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the CookbookSearchByKeywordCommand
     * and returns a CookbookSearchByKeywordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CookbookSearchByKeywordCommand parseSearchByKeyword(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SEARCH_KEYWORD);

        if (!argMultimap.arePrefixesPresent(PREFIX_SEARCH_KEYWORD)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CookbookSearchByKeywordCommand.MESSAGE_USAGE));
        }

        String trimmedArgs = argMultimap.getValue(PREFIX_SEARCH_KEYWORD).get();
        String[] recipeKeywords = trimmedArgs.split("\\s+");

        return new CookbookSearchByKeywordCommand(
                new RecipeNameContainsKeywordsPredicate(Arrays.asList(recipeKeywords)));
    }

    /**
     * Parses the given {@code String} of arguments in the context of the CookbookSearchByTagCommand
     * and returns a CookbookSearchByTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CookbookSearchByTagCommand parseSearchByTag(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!argMultimap.arePrefixesPresent(PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CookbookSearchByTagCommand.MESSAGE_USAGE));
        }

        String trimmedArgs = argMultimap.getValue(PREFIX_TAG).get();
        String[] recipeTags = trimmedArgs.split("\\s+");

        return new CookbookSearchByTagCommand(new RecipeContainsTagsPredicate(Arrays.asList(recipeTags)));
    }

    /**
     * Parses the given {@code String} of arguments in the context of the CookbookSearchByInventoryCommand
     * and returns a CookbookSearchByInventoryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CookbookSearchByInventoryCommand parseSearchByInventory(String args) throws ParseException {
        requireNonNull(args);

        return new CookbookSearchByInventoryCommand();
    }

    /**
     * Returns true if {@code String args} contains {@code String PREFIX_SEARCH_KEYWORD}.
     */
    private boolean containsKeyword(String args) {
        return args.contains(PREFIX_SEARCH_KEYWORD.toString());
    }

    /**
     * Returns true if {@code String args} contains {@code String PREFIX_TAG}.
     */
    private boolean containsTag(String args) {
        return args.contains(PREFIX_TAG.toString());
    }

    /**
     * Returns true if {@code String args} contains {@code String SEARCH_INVENTORY_COMMAND}.
     */
    private boolean containsInventoryKeyword(String args) {
        return args.contains(CookbookSearchByInventoryCommand.SEARCH_INVENTORY_COMMAND);
    }
}
