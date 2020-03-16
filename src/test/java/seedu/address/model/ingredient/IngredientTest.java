package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_QUANTITY_BANANA;
import static seedu.address.testutil.TypicalIngredients.APPLE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.IngredientBuilder;

public class IngredientTest {
    @Test
    public void isSameIngredient() {
        // same object -> returns true
        assertTrue(APPLE.isSameIngredient(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameIngredient(null));

        // different name -> returns false
        Ingredient editedApple = new IngredientBuilder(APPLE).withName(VALID_INGREDIENT_NAME_BANANA).build();
        assertFalse(APPLE.isSameIngredient(editedApple));

        // same name, different quantity -> returns true
        editedApple = new IngredientBuilder(APPLE).withQuantity(VALID_INGREDIENT_QUANTITY_BANANA).build();
        assertTrue(APPLE.isSameIngredient(editedApple));
    }
}
