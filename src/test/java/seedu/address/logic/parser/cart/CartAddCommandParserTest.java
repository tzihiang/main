package seedu.address.logic.parser.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.cart.CartAddIngredientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;

public class CartAddCommandParserTest {
    private static final Ingredient VALID_INGREDIENT = new Ingredient(new IngredientName("Ingredient"),
            new IngredientQuantity("5"));
    private static final String VALID_INGREDIENT_ARGUMENT = " i/Ingredient q/5";
    private static final String INVALID_INGREDIENT_ARGUMENT_NO_NAME = " q/5";
    private static final String INVALID_INGREDIENT_ARGUMENT_NO_QUANTITY = " i/Ingredient";
    private static final String VALID_RECIPE_ARGUMENT = " recipe 1";
    private static final String INVALID_RECIPE_ARGUMENT_NO_RECIPE = " 1";
    private static final String INVALID_ARGUMENT = " Invalid argument";

    @Test
    public void parse_validInput() throws ParseException {
        CartAddCommandParser c = new CartAddCommandParser();
        assertEquals(c.parse(VALID_INGREDIENT_ARGUMENT), new CartAddIngredientCommand(VALID_INGREDIENT));
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
