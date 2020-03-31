package seedu.address.logic.parser.cart;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class CartAddCommandParserTest {
 static final String VALID_INGREDIENT_ARGUMENT = "i/Ingredient q/5";
    private static final String INVALID_INGREDIENT_ARGUMENT_NO_NAME = "q/5";
    private static final String INVALID_INGREDIENT_ARGUMENT_NO_QUANTITY = "i/Ingredient";
    private static final String VALID_RECIPE_ARGUMENT = "recipe 1";
    private static final String INVALID_RECIPE_ARGUMENT_NO_RECIPE = "1";
    private static final String INVALID_ARGUMENT = "Invalid argument";

    @Test
    public void parse_validInput() {
        // TODO
    }

    @Test
    public void parse_null_throwsNullPointerException() {
        CartAddCommandParser c = new CartAddCommandParser();
        assertThrows(NullPointerException.class, () -> c.parse(null));
    }

    @Test
    public void parse_invalidInput() {
        assertThrows(ParseException.class, () -> new CartAddCommandParser().parse(INVALID_ARGUMENT));
    }

    @Test
    public void containsRecipe_validInput() {
        assertTrue(new CartAddCommandParser().containsRecipe(VALID_RECIPE_ARGUMENT));
        assertFalse(new CartAddCommandParser().containsRecipe(INVALID_ARGUMENT));
        assertFalse(new CartAddCommandParser().containsRecipe(INVALID_RECIPE_ARGUMENT_NO_RECIPE));
    }

    @Test
    public void containsRecipe_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CartAddCommandParser().containsRecipe(null));
    }

    @Test
    public void containsIngredient_validInput() {
        assertTrue(new CartAddCommandParser().containsIngredient(VALID_INGREDIENT_ARGUMENT));
        assertFalse(new CartAddCommandParser().containsIngredient(INVALID_ARGUMENT));
        assertFalse(new CartAddCommandParser().containsIngredient(INVALID_INGREDIENT_ARGUMENT_NO_NAME));
        assertFalse(new CartAddCommandParser().containsIngredient(INVALID_INGREDIENT_ARGUMENT_NO_QUANTITY));
    }

    @Test
    public void containsIngredient_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CartAddCommandParser().containsIngredient(null));
    }
}
