package seedu.address.model.ingredient;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UniqueIngredientListTest {

    private final UniqueIngredientList uniqueIngredientList = new UniqueIngredientList();

    @Test
    public void containsNullIngredientThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.contains(null));
    }

    @Test
    public void addNullIngredientThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.add(null));
    }

}
