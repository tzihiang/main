package seedu.address.logic.parser.cookbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STEP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STEP_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.cookbook.CookbookAddCommand;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.TypicalRecipes;

public class CookbookAddCommandParserTest {
    private static final Recipe VALID_RECIPE = TypicalRecipes.getValidRecipe();
    private static final String VALID_ARGUMENT = " n/Recipe d/Description i/Ingredient q/5 x/1 s/Step t/Tag";
    private static final String INVALID_ARGUMENT_NO_RECIPE_NAME = " d/Description i/Ingredient q/5 x/1 "
            + "s/Step t/Tag";
    private static final String INVALID_ARGUMENT_NO_RECIPE_DESCRIPTION = " n/Recipe i/Ingredient q/5 "
            + "x/1 s/Step t/Tag";
    private static final String INVALID_ARGUMENT = "Invalid argument";

    @Test
    public void parse_validInput() throws ParseException {
        CookbookAddCommandParser c = new CookbookAddCommandParser();
        assertEquals(c.parse(VALID_ARGUMENT), new CookbookAddCommand(VALID_RECIPE));
    }

    @Test
    public void parse_null_throwsNullPointerException() {
        CookbookAddCommandParser c = new CookbookAddCommandParser();
        assertThrows(NullPointerException.class, () -> c.parse(null));
    }

    @Test
    public void parse_invalidInput() {
        assertThrows(ParseException.class, () ->
            new CookbookAddCommandParser().parse(INVALID_ARGUMENT_NO_RECIPE_NAME));
        assertThrows(ParseException.class, () ->
                new CookbookAddCommandParser().parse(INVALID_ARGUMENT_NO_RECIPE_DESCRIPTION));
        assertThrows(ParseException.class, () ->
                new CookbookAddCommandParser().parse(INVALID_ARGUMENT));
    }

    @Test
    public void arePrefixesPresent_validInput() {
        assertTrue(CookbookAddCommandParser.arePrefixesPresent(
                ArgumentTokenizer.tokenize(
                VALID_ARGUMENT, PREFIX_RECIPE_NAME,
                PREFIX_RECIPE_DESCRIPTION, PREFIX_INGREDIENT_NAME,
                PREFIX_INGREDIENT_QUANTITY, PREFIX_STEP_INDEX, PREFIX_STEP_DESCRIPTION, PREFIX_TAG),
                PREFIX_RECIPE_NAME, PREFIX_RECIPE_DESCRIPTION));
        assertFalse(CookbookAddCommandParser.arePrefixesPresent(
                ArgumentTokenizer.tokenize(INVALID_ARGUMENT,
                PREFIX_RECIPE_NAME,
                PREFIX_RECIPE_DESCRIPTION, PREFIX_INGREDIENT_NAME,
                PREFIX_INGREDIENT_QUANTITY, PREFIX_STEP_INDEX, PREFIX_STEP_DESCRIPTION, PREFIX_TAG),
                PREFIX_RECIPE_NAME, PREFIX_RECIPE_DESCRIPTION));
        assertFalse(CookbookAddCommandParser.arePrefixesPresent(
                ArgumentTokenizer.tokenize(INVALID_ARGUMENT_NO_RECIPE_NAME,
                PREFIX_RECIPE_NAME,
                PREFIX_RECIPE_DESCRIPTION, PREFIX_INGREDIENT_NAME,
                PREFIX_INGREDIENT_QUANTITY, PREFIX_STEP_INDEX, PREFIX_STEP_DESCRIPTION, PREFIX_TAG),
                PREFIX_RECIPE_NAME, PREFIX_RECIPE_DESCRIPTION));
        assertFalse(CookbookAddCommandParser.arePrefixesPresent(
                ArgumentTokenizer.tokenize(INVALID_ARGUMENT_NO_RECIPE_DESCRIPTION,
                PREFIX_RECIPE_NAME,
                PREFIX_RECIPE_DESCRIPTION, PREFIX_INGREDIENT_NAME,
                PREFIX_INGREDIENT_QUANTITY, PREFIX_STEP_INDEX, PREFIX_STEP_DESCRIPTION, PREFIX_TAG),
                PREFIX_RECIPE_NAME, PREFIX_RECIPE_DESCRIPTION));
    }

    @Test
    public void arePrefixesPresent_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                CookbookAddCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
                        VALID_ARGUMENT, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY),
                        (Prefix[]) null));
        assertThrows(NullPointerException.class, () ->
                CookbookAddCommandParser.arePrefixesPresent(null, PREFIX_INGREDIENT_NAME));

    }
}
