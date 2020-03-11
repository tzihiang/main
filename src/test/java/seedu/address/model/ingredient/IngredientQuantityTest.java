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
    public void isCompatibleWith() {
        IngredientQuantity a = new IngredientQuantity("1 cup");
        IngredientQuantity b = new IngredientQuantity("0.25 cup");
        IngredientQuantity c = new IngredientQuantity("1/2 cup");
        IngredientQuantity d = new IngredientQuantity("100 ml");

        assertTrue(a.isCompatibleWith(a));
        assertTrue(a.isCompatibleWith(b));
        assertTrue(a.isCompatibleWith(c));
        assertTrue(b.isCompatibleWith(a));
        assertTrue(b.isCompatibleWith(b));
        assertTrue(b.isCompatibleWith(c));
        assertTrue(c.isCompatibleWith(a));
        assertTrue(c.isCompatibleWith(b));
        assertTrue(c.isCompatibleWith(c));
        assertTrue(d.isCompatibleWith(d));
        assertFalse(a.isCompatibleWith(d));
        assertFalse(b.isCompatibleWith(d));
        assertFalse(c.isCompatibleWith(d));
        assertFalse(d.isCompatibleWith(a));
        assertFalse(d.isCompatibleWith(b));
        assertFalse(d.isCompatibleWith(c));
    }

    @Test
    public void add() {
        IngredientQuantity a = new IngredientQuantity("1 cup");
        IngredientQuantity b = new IngredientQuantity("0.25 cup");
        IngredientQuantity c = new IngredientQuantity("2 1/2 cup");
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
        assertEquals("3 1/2 cup", a.add(c).toString());
        assertEquals("2.2 cup", a.add(d).toString());
        assertEquals("1.25 cup", b.add(a).toString());
        assertEquals("2 3/4 cup", b.add(c).toString());
        assertEquals("1.45 cup", b.add(d).toString());
        assertEquals("3 1/2 cup", c.add(a).toString());
        assertEquals("2 3/4 cup", c.add(b).toString());
        assertEquals("3.7 cup", c.add(d).toString());
        assertEquals("2.2 cup", d.add(a).toString());
        assertEquals("1.45 cup", d.add(b).toString());
        assertEquals("3.7 cup", d.add(c).toString());
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

    @Test
    public void parseValue() {
        // valid ingredient quantity
        assertEquals("12345", IngredientQuantity.parseValue("12345").toString()); // whole number
        assertEquals("0.5", IngredientQuantity.parseValue("0.5").toString()); // decimal number
        assertEquals("1/2", IngredientQuantity.parseValue("1/2").toString()); // pure fraction
        assertEquals("1 1/2", IngredientQuantity.parseValue("1 1/2").toString()); // mixed fraction
        assertEquals("100", IngredientQuantity.parseValue("100 ml").toString()); // whole number and unit
        assertEquals("100", IngredientQuantity.parseValue("100ml").toString()); // no space between value and unit
        assertEquals("2.5", IngredientQuantity.parseValue("2.5 cups").toString()); // decimal number and unit
        assertEquals("0.5", IngredientQuantity.parseValue(".5 cups").toString()); // starts with decimal point
        assertEquals("1/2", IngredientQuantity.parseValue("1/2 cups").toString()); // pure fractions and unit
        assertEquals("2.3", IngredientQuantity.parseValue("2 3/10 cups").toString()); // denominator larger than 6
        assertEquals("1", IngredientQuantity.parseValue("1 Tablespoon").toString()); // with capital letters
        assertEquals("1", IngredientQuantity.parseValue("1 rounded tsp").toString()); // unit with spaces;
    }

    @Test
    public void parseUnit() {
        // valid ingredient quantity
        assertEquals("", IngredientQuantity.parseUnit("12345")); // whole number
        assertEquals("", IngredientQuantity.parseUnit("0.5")); // decimal number
        assertEquals("", IngredientQuantity.parseUnit("1/2")); // pure fraction
        assertEquals("", IngredientQuantity.parseUnit("1 1/2")); // mixed fraction
        assertEquals("ml", IngredientQuantity.parseUnit("100 ml")); // whole number and unit
        assertEquals("ml", IngredientQuantity.parseUnit("100ml")); // no space between value and unit
        assertEquals("cups", IngredientQuantity.parseUnit("2.5 cups")); // decimal number and unit
        assertEquals("cups", IngredientQuantity.parseUnit(".5 cups")); // starts with decimal point
        assertEquals("cups", IngredientQuantity.parseUnit("1/2 cups")); // pure fractions and unit
        assertEquals("cups", IngredientQuantity.parseUnit("2 3/5 cups")); // mixed fractions and unit
        assertEquals("Tablespoon", IngredientQuantity.parseUnit("1 Tablespoon")); // with capital letters
        assertEquals("rounded tsp", IngredientQuantity.parseUnit("1 rounded tsp ")); // unit with spaces;
    }

    @Test
    public void toString_validIngredientQuantity_returnsStringRepresentation() {
        // valid ingredient quantity
        assertEquals("12345", new IngredientQuantity("12345").toString()); // whole number
        assertEquals("0.5", new IngredientQuantity("0.5").toString()); // decimal number
        assertEquals("1/2", new IngredientQuantity("1/2").toString()); // pure fraction
        assertEquals("1 1/2", new IngredientQuantity("1 1/2").toString()); // mixed fraction
        assertEquals("100 ml", new IngredientQuantity("100 ml").toString()); // whole number and unit
        assertEquals("100 ml", new IngredientQuantity("100ml").toString()); // no space between value and unit
        assertEquals("2.5 cups", new IngredientQuantity("2.5 cups").toString()); // decimal number and unit
        assertEquals("0.5 cups", new IngredientQuantity(".5 cups").toString()); // starts with decimal point
        assertEquals("1/2 cups", new IngredientQuantity("1/2 cups").toString()); // pure fractions and unit
        assertEquals("2.3 cups", new IngredientQuantity("2 3/10 cups").toString()); // mixed fractions and unit
        assertEquals("1 Tablespoon", new IngredientQuantity("1 Tablespoon").toString()); // with capital letters
        assertEquals("1 rounded tsp", new IngredientQuantity("1 rounded tsp ").toString()); // unit with spaces;
    }
}
