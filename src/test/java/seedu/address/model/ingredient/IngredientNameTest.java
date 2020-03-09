package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IngredientNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IngredientName(null));
    }

    @Test
    public void constructor_invalidIngredientName_throwsIllegalArgumentException() {
        String invalidIngredientName = "";
        assertThrows(IllegalArgumentException.class, () -> new IngredientName(invalidIngredientName));
    }

    @Test
    public void isValidIngredientName() {
        // null ingredient name
        assertThrows(NullPointerException.class, () -> IngredientName.isValidIngredientName(null));

        // invalid ingredient name
        assertFalse(IngredientName.isValidIngredientName("")); // empty string
        assertFalse(IngredientName.isValidIngredientName(" ")); // spaces only

        // valid ingredient name
        assertTrue(IngredientName.isValidIngredientName("egg")); // alphabets only
        assertTrue(IngredientName.isValidIngredientName("12345")); // numbers only
        assertTrue(IngredientName.isValidIngredientName("100 calorie yogurt")); // alphanumeric characters
        assertTrue(IngredientName.isValidIngredientName("Full Cream Milk")); // with capital letters
        assertTrue(IngredientName.isValidIngredientName("Hershey's Kisses")); // with apostrophe
        assertTrue(IngredientName.isValidIngredientName("Self-raising flour")); // with dash
        assertTrue(IngredientName.isValidIngredientName("70% dark chocolate")); // with numbers and percent sign
        assertTrue(IngredientName.isValidIngredientName("Organic whole milk Greek yogurt")); // long ingredient names
    }
}
