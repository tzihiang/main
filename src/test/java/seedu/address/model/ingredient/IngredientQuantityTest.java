package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void isCompatible() {
        IngredientQuantity a = new IngredientQuantity("1 cup");
        IngredientQuantity b = new IngredientQuantity("0.25 cup");
        IngredientQuantity c = new IngredientQuantity("1/2 cup");
        IngredientQuantity d = new IngredientQuantity("100 ml");

        assertTrue(a.isCompatible(a));
        assertTrue(a.isCompatible(b));
        assertTrue(a.isCompatible(c));
        assertTrue(b.isCompatible(a));
        assertTrue(b.isCompatible(b));
        assertTrue(b.isCompatible(c));
        assertTrue(c.isCompatible(a));
        assertTrue(c.isCompatible(b));
        assertTrue(c.isCompatible(c));
        assertTrue(d.isCompatible(d));
        assertFalse(a.isCompatible(d));
        assertFalse(b.isCompatible(d));
        assertFalse(c.isCompatible(d));
        assertFalse(d.isCompatible(a));
        assertFalse(d.isCompatible(b));
        assertFalse(d.isCompatible(c));
    }

    @Test
    public void add() {
        IngredientQuantity a = new IngredientQuantity("1 cup");
        IngredientQuantity b = new IngredientQuantity("0.25 cup");
        IngredientQuantity c = new IngredientQuantity("1/2 cup");
        IngredientQuantity d = new IngredientQuantity("1.2cup");
        IngredientQuantity e = new IngredientQuantity("100 ml");

        assertThrows(IllegalArgumentException.class, () -> a.add(e));
        assertThrows(IllegalArgumentException.class, () -> b.add(e));
        assertThrows(IllegalArgumentException.class, () -> c.add(e));
        assertThrows(IllegalArgumentException.class, () -> d.add(e));
        assertThrows(IllegalArgumentException.class, () -> e.add(a));
        assertThrows(IllegalArgumentException.class, () -> e.add(b));
        assertThrows(IllegalArgumentException.class, () -> e.add(c));
        assertThrows(IllegalArgumentException.class, () -> e.add(d));

        assertEquals("1.25 cup", a.add(b).toString());
        assertEquals("1 1/2 cup", a.add(c).toString());
        assertEquals("2.2 cup", a.add(d).toString());
        assertEquals("1.25 cup", b.add(a).toString());
        assertEquals("3/4 cup", b.add(c).toString());
        assertEquals("1.45 cup", b.add(d).toString());
        assertEquals("1 1/2 cup", c.add(a).toString());
        assertEquals("3/4 cup", c.add(b).toString());
        assertEquals("1.7 cup", c.add(d).toString());
        assertEquals("2.2 cup", d.add(a).toString());
        assertEquals("1.45 cup", d.add(b).toString());
        assertEquals("1.7 cup", d.add(c).toString());
    }

    @Test
    public void subtract() {
        IngredientQuantity a = new IngredientQuantity("1 cup");
        IngredientQuantity b = new IngredientQuantity("0.25 cup");
        IngredientQuantity c = new IngredientQuantity("1/2 cup");
        IngredientQuantity d = new IngredientQuantity("1.2cup");
        IngredientQuantity e = new IngredientQuantity("100 ml");

        assertThrows(IllegalArgumentException.class, () -> a.subtract(e));
        assertThrows(IllegalArgumentException.class, () -> b.subtract(e));
        assertThrows(IllegalArgumentException.class, () -> c.subtract(e));
        assertThrows(IllegalArgumentException.class, () -> d.subtract(e));
        assertThrows(IllegalArgumentException.class, () -> e.subtract(a));
        assertThrows(IllegalArgumentException.class, () -> e.subtract(b));
        assertThrows(IllegalArgumentException.class, () -> e.subtract(c));
        assertThrows(IllegalArgumentException.class, () -> e.subtract(d));

        assertEquals("0.75 cup", a.subtract(b).toString());
        assertEquals("1/2 cup", a.subtract(c).toString());
        assertEquals("0 cup", a.subtract(d).toString());
        assertEquals("0 cup", b.subtract(a).toString());
        assertEquals("0 cup", b.subtract(c).toString());
        assertEquals("0 cup", b.subtract(d).toString());
        assertEquals("0 cup", c.subtract(a).toString());
        assertEquals("1/4 cup", c.subtract(b).toString());
        assertEquals("0 cup", c.subtract(d).toString());
        assertEquals("0.2 cup", d.subtract(a).toString());
        assertEquals("0.95 cup", d.subtract(b).toString());
        assertEquals("0.7 cup", d.subtract(c).toString());
    }
}
