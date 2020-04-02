package seedu.address.logic.parser.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.cart.CartAddIngredientCommand;
import seedu.address.logic.commands.cart.CartAddRecipeIngredientCommand;
import seedu.address.logic.commands.cart.CartClearCommand;
import seedu.address.logic.commands.cart.CartRemoveIngredientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;

public class CartCommandParserTest {
    private static final String VALID_ADD_INGREDIENT_ARGUMENT = " add i/Ingredient q/5";
    private static final Ingredient VALID_INGREDIENT = new Ingredient(new IngredientName("Ingredient"),
            new IngredientQuantity("5"));
    private static final Index VALID_RECIPE_INDEX = new Index(1);
    private static final String VALID_ADD_RECIPE_ARGUMENT = "add recipe 1";
    private static final String VALID_CLEAR_ARGUMENT = "clear";
    private static final String VALID_REMOVE_INGREDIENT_ARGUMENT = "remove i/Ingredient q/5";
    private static final String INVALID_ARGUMENT = "Invalid argument";

    @Test
    public void parse_validInput() throws ParseException {
        CartCommandParser c = new CartCommandParser();
        assertEquals(c.parse(VALID_ADD_INGREDIENT_ARGUMENT), new CartAddIngredientCommand(VALID_INGREDIENT));
        assertEquals(c.parse(VALID_ADD_RECIPE_ARGUMENT), new CartAddRecipeIngredientCommand(VALID_RECIPE_INDEX));
        assertEquals(c.parse(VALID_CLEAR_ARGUMENT), new CartClearCommand());
        assertEquals(c.parse(VALID_REMOVE_INGREDIENT_ARGUMENT), new CartRemoveIngredientCommand(VALID_INGREDIENT));
    }

    @Test
    public void parse_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CartCommandParser().parse(null));
    }

    @Test
    public void parse_invalidInput() {
        assertThrows(ParseException.class, () -> new CartCommandParser().parse(INVALID_ARGUMENT));
    }
}
