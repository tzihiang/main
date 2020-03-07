package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ingredient(null));
    }

    @Test
    public void constructor_invalidIngredient_throwsIllegalArgumentException() {
        String invalidIngredient = "";
        assertThrows(IllegalArgumentException.class, () -> new Ingredient(invalidIngredient));
    }

    @Test
    public void isValidIngredient() {
        // null ingredient
        assertThrows(NullPointerException.class, () -> Ingredient.isValidIngredient(null));

        // invalid ingredient
        assertFalse(Ingredient.isValidIngredient("")); // empty string
        assertFalse(Ingredient.isValidIngredient(" ")); // spaces only
        assertFalse(Ingredient.isValidIngredient("^")); // only non-alphanumeric characters
        assertFalse(Ingredient.isValidIngredient("peter*")); // contains non-alphanumeric characters

        // valid ingredient
        assertTrue(Ingredient.isValidIngredient("peter jack")); // alphabets only
        assertTrue(Ingredient.isValidIngredient("12345")); // numbers only
        assertTrue(Ingredient.isValidIngredient("peter the 2nd")); // alphanumeric characters
        assertTrue(Ingredient.isValidIngredient("Capital Tan")); // with capital letters
        assertTrue(Ingredient.isValidIngredient("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
