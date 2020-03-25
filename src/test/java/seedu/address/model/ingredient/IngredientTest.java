package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_QUANTITY_ALMOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_QUANTITY_BANANA;
import static seedu.address.testutil.TypicalIngredients.APPLE;
import static seedu.address.testutil.TypicalIngredients.BANANA;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.IngredientBuilder;

public class IngredientTest {

    @Test
    public void isCompatibleWith() {
        // same object -> returns true
        assertTrue(APPLE.isCompatibleWith(APPLE));

        // null -> returns false
        assertFalse(APPLE.isCompatibleWith(null));

        // different name -> returns false
        Ingredient editedApple = new IngredientBuilder(APPLE).withName(VALID_INGREDIENT_NAME_BANANA).build();
        assertFalse(APPLE.isCompatibleWith(editedApple));

        // same name, different quantity value -> returns true
        editedApple = new IngredientBuilder(APPLE).withQuantity(VALID_INGREDIENT_QUANTITY_BANANA).build();
        assertTrue(APPLE.isCompatibleWith(editedApple));

        // same name, different quantity unit -> returns false
        editedApple = new IngredientBuilder(APPLE).withQuantity(VALID_INGREDIENT_QUANTITY_ALMOND).build();
        assertFalse(APPLE.isCompatibleWith(editedApple));
    }

    @Test
    public void isSameIngredient() {
        // same object -> returns true
        assertTrue(APPLE.isSameIngredient(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameIngredient(null));

        // different name -> returns false
        Ingredient editedApple = new IngredientBuilder(APPLE).withName(VALID_INGREDIENT_NAME_BANANA).build();
        assertFalse(APPLE.isSameIngredient(editedApple));

        // same name, different quantity value -> returns true
        editedApple = new IngredientBuilder(APPLE).withQuantity(VALID_INGREDIENT_QUANTITY_BANANA).build();
        assertTrue(APPLE.isSameIngredient(editedApple));

        // same name, different quantity unit -> returns true
        editedApple = new IngredientBuilder(APPLE).withQuantity(VALID_INGREDIENT_QUANTITY_ALMOND).build();
        assertTrue(APPLE.isSameIngredient(editedApple));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Ingredient appleCopy = new IngredientBuilder(APPLE).build();
        assertTrue(APPLE.equals(appleCopy));

        // same object -> returns true
        assertTrue(APPLE.equals(APPLE));

        // null -> returns false
        assertFalse(APPLE.equals(null));

        // different type -> returns false
        assertFalse(APPLE.equals(5));

        // different ingredient -> returns false
        assertFalse(APPLE.equals(BANANA));

        // different name -> returns false
        Ingredient editedApple = new IngredientBuilder(APPLE).withName(VALID_INGREDIENT_NAME_BANANA).build();
        assertFalse(APPLE.equals(editedApple));

        // same name, different quantity value -> returns false
        editedApple = new IngredientBuilder(APPLE).withQuantity(VALID_INGREDIENT_QUANTITY_BANANA).build();
        assertFalse(APPLE.equals(editedApple));

        // same name, different quantity unit -> returns false
        editedApple = new IngredientBuilder(APPLE).withQuantity(VALID_INGREDIENT_QUANTITY_ALMOND).build();
        assertFalse(APPLE.equals(editedApple));
    }
}
