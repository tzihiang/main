package seedu.address.logic.parser.inventory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.inventory.InventoryRemoveIngredientCommand;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;

public class InventoryRemoveIngredientCommandParserTest {
    private static final Ingredient VALID_INGREDIENT = new Ingredient(new IngredientName("Ingredient"),
        new IngredientQuantity("5"));
    private static final String VALID_INGREDIENT_ARGUMENT = " i/Ingredient q/5";
    private static final String INVALID_INGREDIENT_ARGUMENT_NO_NAME = " q/5";
    private static final String INVALID_ARGUMENT = "Invalid argument";

    @Test
    public void parse_validInput() throws ParseException {
        InventoryRemoveIngredientCommandParser c = new InventoryRemoveIngredientCommandParser();
        Assertions.assertEquals(c.parse(VALID_INGREDIENT_ARGUMENT),
            new InventoryRemoveIngredientCommand(VALID_INGREDIENT));
    }

    @Test
    public void parse_null_throwsNullPointerException() {
        InventoryRemoveIngredientCommandParser c = new InventoryRemoveIngredientCommandParser();
        assertThrows(NullPointerException.class, () -> c.parse(null));
    }

    @Test
    public void parse_invalidInput() {
        assertThrows(ParseException.class, () -> new InventoryRemoveIngredientCommandParser().parse(INVALID_ARGUMENT));
        assertThrows(ParseException.class, () ->
            new InventoryRemoveIngredientCommandParser().parse(INVALID_INGREDIENT_ARGUMENT_NO_NAME));
    }

    @Test
    public void arePrefixesPresent_validInput() {
        Assertions.assertTrue(InventoryRemoveIngredientCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
            VALID_INGREDIENT_ARGUMENT, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY),
            PREFIX_INGREDIENT_NAME));
        assertFalse(InventoryRemoveIngredientCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
            INVALID_INGREDIENT_ARGUMENT_NO_NAME, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY),
            PREFIX_INGREDIENT_NAME));
        assertFalse(InventoryRemoveIngredientCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
            INVALID_ARGUMENT, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY),
            PREFIX_INGREDIENT_NAME));
    }

    @Test
    public void arePrefixesPresent_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            InventoryRemoveIngredientCommandParser.arePrefixesPresent(ArgumentTokenizer.tokenize(
                    VALID_INGREDIENT_ARGUMENT, PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY),
                    (Prefix[]) null));
        assertThrows(NullPointerException.class, () ->
            InventoryRemoveIngredientCommandParser.arePrefixesPresent(null, PREFIX_INGREDIENT_NAME));
    }
}
