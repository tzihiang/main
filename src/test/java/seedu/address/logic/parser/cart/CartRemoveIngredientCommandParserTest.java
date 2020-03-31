package seedu.address.logic.parser.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.cart.CartRemoveIngredientCommand;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;

public class CartRemoveIngredientCommandParserTest {
    private static final Ingredient VALID_INGREDIENT = new Ingredient(new IngredientName("Ingredient"),
            new IngredientQuantity("5"));
    private static final String VALID_INGREDIENT_ARGUMENT = " i/Ingredient q/5";
    private static final String INVALID_INGREDIENT_ARGUMENT_NO_NAME = " q/5";
    private static final String INVALID_ARGUMENT = "Invalid argument";

    @Test
    public void parse_validInput() throws ParseException {
        CartRemoveIngredientCommandParser c = new CartRemoveIngredientCommandParser();
        assertEquals(c.parse(VALID_INGREDIENT_ARGUMENT), new CartRemoveIngredientCommand(VALID_INGREDIENT));
    }

    @Test
    public void parse_null_throwsNullPointerException() {
        CartRemoveIngredientCommandParser c = new CartRemoveIngredientCommandParser();
        assertThrows(NullPointerException.class, () -> c.parse(null));
    }

    @Test
    public void parse_invalidInput() {
        assertThrows(ParseException.class, () -> new CartRemoveIngredientCommandParser().parse(INVALID_ARGUMENT));
        assertThrows(ParseException.class, () ->
                new CartRemoveIngredientCommandParser().parse(INVALID_INGREDIENT_ARGUMENT_NO_NAME));
    }

    @Test
    public void arePrefixesPresent_validInput() {
        assertTrue(CartRemoveIngredientCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
                VALID_INGREDIENT_ARGUMENT, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY),
                PREFIX_INGREDIENT_NAME));
        assertFalse(CartRemoveIngredientCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
                INVALID_INGREDIENT_ARGUMENT_NO_NAME, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY),
                PREFIX_INGREDIENT_NAME));
        assertFalse(CartRemoveIngredientCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
                INVALID_ARGUMENT, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY),
                PREFIX_INGREDIENT_NAME));
    }

    @Test
    public void arePrefixesPresent_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                CartRemoveIngredientCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
                        VALID_INGREDIENT_ARGUMENT, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY),
                        (Prefix[]) null));
        assertThrows(NullPointerException.class, () ->
                CartRemoveIngredientCommandParser.arePrefixesPresent(null, PREFIX_INGREDIENT_NAME));

    }
}
