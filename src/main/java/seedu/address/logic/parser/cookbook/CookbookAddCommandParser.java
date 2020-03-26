package seedu.address.logic.parser.cookbook;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.cookbook.CookbookAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeDescription;
import seedu.address.model.recipe.RecipeName;

/**
 * Parses input arguments and creates a new CookbookAddCommand object
 */
public class CookbookAddCommandParser implements Parser<CookbookAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CookbookAddCommand
     * and returns a CookbookAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CookbookAddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RECIPE_NAME, PREFIX_RECIPE_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_RECIPE_NAME, PREFIX_RECIPE_DESCRIPTION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CookbookAddCommand.MESSAGE_USAGE));
        }

        RecipeName recipeName = ParserUtil.parseRecipeName(argMultimap.getValue(PREFIX_RECIPE_NAME).get());
        RecipeDescription recipeDescription =
                ParserUtil.parseRecipeDescription(argMultimap.getValue(PREFIX_RECIPE_DESCRIPTION).get());

        Recipe recipe = new Recipe(recipeName, recipeDescription);

        return new CookbookAddCommand(recipe);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix ... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
