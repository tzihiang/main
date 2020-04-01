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
}
