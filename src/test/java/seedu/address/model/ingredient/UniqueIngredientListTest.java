package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_QUANTITY_ALMOND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIngredients.APPLE;
import static seedu.address.testutil.TypicalIngredients.BANANA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.ingredient.exceptions.DuplicateIngredientException;
import seedu.address.model.ingredient.exceptions.IngredientNotFoundException;
import seedu.address.testutil.IngredientBuilder;

public class UniqueIngredientListTest {

    private final UniqueIngredientList uniqueIngredientList = new UniqueIngredientList();

    @Test
    public void contains_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.contains(null));
    }

    @Test
    public void contains_ingredientNotInList_returnsFalse() {
        assertFalse(uniqueIngredientList.contains(APPLE));
    }

    @Test
    public void contains_ingredientInList_returnsTrue() {
        uniqueIngredientList.add(APPLE);
        assertTrue(uniqueIngredientList.contains(APPLE));
    }

    @Test
    public void contains_ingredientWithSameIdentityFieldsInList_returnsTrue() {
        uniqueIngredientList.add(APPLE);
        Ingredient editedApple = new IngredientBuilder(APPLE).withQuantity(VALID_INGREDIENT_QUANTITY_ALMOND)
                .build();
        assertTrue(uniqueIngredientList.contains(editedApple));
    }

    @Test
    public void add_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.add(null));
    }

    @Test
    public void add_duplicateIngredient_throwsDuplicateIngredientException() {
        uniqueIngredientList.add(APPLE);
        assertThrows(DuplicateIngredientException.class, () -> uniqueIngredientList.add(APPLE));
    }

    @Test
    public void setIngredient_nullTargetIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.setIngredient(null, APPLE));
    }

    @Test
    public void setIngredient_nullEditedIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.setIngredient(APPLE, null));
    }

    @Test
    public void setIngredient_targetIngredientNotInList_throwsIngredientNotFoundException() {
        assertThrows(IngredientNotFoundException.class, () -> uniqueIngredientList.setIngredient(APPLE, APPLE));
    }

    @Test
    public void setIngredient_editedIngredientIsSameIngredient_success() {
        uniqueIngredientList.add(APPLE);
        uniqueIngredientList.setIngredient(APPLE, APPLE);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(APPLE);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredient_editedIngredientHasSameIdentity_success() {
        uniqueIngredientList.add(APPLE);
        Ingredient editedApple = new IngredientBuilder(APPLE).withQuantity(VALID_INGREDIENT_QUANTITY_ALMOND).build();
        uniqueIngredientList.setIngredient(APPLE, editedApple);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(editedApple);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredient_editedIngredientHasDifferentIdentity_success() {
        uniqueIngredientList.add(APPLE);
        uniqueIngredientList.setIngredient(APPLE, BANANA);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(BANANA);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredient_editedIngredientHasNonUniqueIdentity_throwsDuplicateIngredientException() {
        uniqueIngredientList.add(APPLE);
        uniqueIngredientList.add(BANANA);
        assertThrows(DuplicateIngredientException.class, () -> uniqueIngredientList.setIngredient(APPLE, BANANA));
    }

    @Test
    public void remove_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.remove(null));
    }

    @Test
    public void remove_ingredientDoesNotExist_throwsIngredientNotFoundException() {
        assertThrows(IngredientNotFoundException.class, () -> uniqueIngredientList.remove(APPLE));
    }

    @Test
    public void remove_existingIngredient_removesIngredient() {
        uniqueIngredientList.add(APPLE);
        uniqueIngredientList.remove(APPLE);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredient_nullUniqueIngredientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueIngredientList.setIngredients((UniqueIngredientList) null));
    }

    @Test
    public void setIngredients_uniqueIngredientList_replacesOwnListWithProvidedUniqueIngredientList() {
        uniqueIngredientList.add(APPLE);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(BANANA);
        uniqueIngredientList.setIngredients(expectedUniqueIngredientList);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.setIngredients((List<Ingredient>) null));
    }

    @Test
    public void setIngredients_list_replacesOwnListWithProvidedList() {
        uniqueIngredientList.add(APPLE);
        List<Ingredient> ingredientList = Collections.singletonList(BANANA);
        uniqueIngredientList.setIngredients(ingredientList);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(BANANA);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredients_listWithDuplicateIngredients_throwsDuplicateIngredientException() {
        List<Ingredient> listWithDuplicateIngredients = Arrays.asList(APPLE, APPLE);
        assertThrows(DuplicateIngredientException.class, () ->
                uniqueIngredientList.setIngredients(listWithDuplicateIngredients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueIngredientList.asUnmodifiableObservableList().remove(0));
    }
}
