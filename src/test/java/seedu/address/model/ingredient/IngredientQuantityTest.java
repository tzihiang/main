package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IngredientQuantityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IngredientQuantity(null));
    }

    @Test
    public void constructor_invalidIngredientQuantity_throwsIllegalArgumentException() {
        String invalidIngredientQuantity = "";
        assertThrows(IllegalArgumentException.class, () -> new IngredientQuantity(invalidIngredientQuantity));
    }

    @Test
    public void isValidIngredientQuantity() {
        // null ingredient quantity
        assertThrows(NullPointerException.class, () -> IngredientQuantity.isValidIngredientQuantity(null));

        // invalid ingredient quantity
        assertFalse(IngredientQuantity.isValidIngredientQuantity("")); // empty string
        assertFalse(IngredientQuantity.isValidIngredientQuantity(" ")); // spaces only
        assertFalse(IngredientQuantity.isValidIngredientQuantity("dozen")); // starts with alphabets
        assertFalse(IngredientQuantity.isValidIngredientQuantity("1.")); // whole number with decimal point
        assertFalse(IngredientQuantity.isValidIngredientQuantity("1 / 2 cups")); // spaces in fraction
        assertFalse(IngredientQuantity.isValidIngredientQuantity("1 piece1")); // number in unit

        // valid ingredient quantity
        assertTrue(IngredientQuantity.isValidIngredientQuantity("12345")); // whole number
        assertTrue(IngredientQuantity.isValidIngredientQuantity("0.5")); // decimal number
        assertTrue(IngredientQuantity.isValidIngredientQuantity("1/2")); // pure fraction
        assertTrue(IngredientQuantity.isValidIngredientQuantity("1 1/2")); // mixed fraction
        assertTrue(IngredientQuantity.isValidIngredientQuantity("100 ml")); // whole number and unit
        assertTrue(IngredientQuantity.isValidIngredientQuantity("100ml")); // no space between value and unit
        assertTrue(IngredientQuantity.isValidIngredientQuantity("2.5 cups")); // decimal number and unit
        assertTrue(IngredientQuantity.isValidIngredientQuantity(".5 cups")); // starts with decimal point
        assertTrue(IngredientQuantity.isValidIngredientQuantity("1/2 cups")); // pure fractions and unit
        assertTrue(IngredientQuantity.isValidIngredientQuantity("2 1/2 cups")); // mixed fractions and unit
        assertTrue(IngredientQuantity.isValidIngredientQuantity("1 Tablespoon")); // with capital letters
        assertTrue(IngredientQuantity.isValidIngredientQuantity("1 rounded tsp")); // unit with spaces
    }
}
