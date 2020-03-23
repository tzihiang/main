package seedu.address.logic.parser.cookbook;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.cookbook.CookbookAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;
import seedu.address.model.ingredient.UniqueIngredientList;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeDescription;
import seedu.address.model.recipe.RecipeName;
import seedu.address.model.step.Step;
import seedu.address.model.step.UniqueStepList;
import seedu.address.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_RECIPE_NAME, PREFIX_RECIPE_DESCRIPTION, PREFIX_INGREDIENT_NAME,
                    PREFIX_INGREDIENT_QUANTITY, PREFIX_STEP_INDEX, PREFIX_STEP_DESCRIPTION, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_RECIPE_NAME, PREFIX_RECIPE_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CookbookAddCommand.MESSAGE_USAGE));
        }

        RecipeName recipeName = ParserUtil.parseRecipeName(argMultimap.getValue(PREFIX_RECIPE_NAME).get());
        RecipeDescription recipeDescription =
                ParserUtil.parseRecipeDescription(argMultimap.getValue(PREFIX_RECIPE_DESCRIPTION).get());

        List<IngredientName> ingredientNames =
                ParserUtil.parseIngredientNames(argMultimap.getAllValues(PREFIX_INGREDIENT_NAME));
        List<IngredientQuantity> ingredientQuantities =
                ParserUtil.parseIngredientQuantities(argMultimap.getAllValues(PREFIX_INGREDIENT_QUANTITY));
        UniqueIngredientList ingredients = parseIngredients(ingredientNames, ingredientQuantities);

        UniqueStepList steps = ParserUtil.parseSteps(argMultimap.getAllValues(PREFIX_STEP_DESCRIPTION));

        Set<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Recipe recipe = new Recipe(recipeName, recipeDescription, ingredients, steps, tags);

        return new CookbookAddCommand(recipe);
    }

    private UniqueIngredientList parseIngredients(List<IngredientName> names, List<IngredientQuantity> quantities) {
        //TODO include exception where the quantities of both lists are different

        UniqueIngredientList ingredients = new UniqueIngredientList();

        for (int i = 0; i < names.size(); i++) {
            ingredients.add(new Ingredient(names.get(i), quantities.get(i)));
        }

        return ingredients;
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix ... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
