package seedu.address.commons.core.fraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.apache.commons.math3.exception.NullArgumentException;
import org.junit.jupiter.api.Test;

public class MixedFractionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MixedFraction(null));
    }

    @Test
    public void parseUnsignedMixedFraction() {
        // null input
        assertThrows(NumberFormatException.class, () -> MixedFraction.parseUnsignedMixedFraction(null));

        // invalid input
        assertThrows(NumberFormatException.class, () -> MixedFraction.parseUnsignedMixedFraction(""));
        assertThrows(NumberFormatException.class, () -> MixedFraction.parseUnsignedMixedFraction("null"));
        assertThrows(NumberFormatException.class, () -> MixedFraction.parseUnsignedMixedFraction("1"));
        assertThrows(NumberFormatException.class, () -> MixedFraction.parseUnsignedMixedFraction("1 / 2"));
        assertThrows(NumberFormatException.class, () -> MixedFraction.parseUnsignedMixedFraction("0.5"));

        // valid input
        assertEquals(0, MixedFraction.parseUnsignedMixedFraction("1/2").intValue());
        assertEquals(1, MixedFraction.parseUnsignedMixedFraction("1/2").getNumerator());
        assertEquals(2, MixedFraction.parseUnsignedMixedFraction("1/2").getDenominator());
        assertEquals(1, MixedFraction.parseUnsignedMixedFraction("1 2/3").intValue());
        assertEquals(5, MixedFraction.parseUnsignedMixedFraction("1 2/3").getNumerator());
        assertEquals(3, MixedFraction.parseUnsignedMixedFraction("1 2/3").getDenominator());
    }

    @Test
    public void sum() {
        MixedFraction a = new MixedFraction(3, 2);
        MixedFraction b = new MixedFraction(8, 3);
        MixedFraction c = new MixedFraction(-5, 3);

        // null input
        assertThrows(NullPointerException.class, () -> MixedFraction.sum(null, null));
        assertThrows(NullPointerException.class, () -> MixedFraction.sum(null, a));
        assertThrows(NullArgumentException.class, () -> MixedFraction.sum(a, null));

        // valid input
        assertEquals(25, MixedFraction.sum(a, b).getNumerator());
        assertEquals(6, MixedFraction.sum(a, b).getDenominator());
        assertEquals(-1, MixedFraction.sum(a, c).getNumerator());
        assertEquals(6, MixedFraction.sum(a, c).getDenominator());
        assertEquals(1, MixedFraction.sum(b, c).getNumerator());
        assertEquals(1, MixedFraction.sum(b, c).getDenominator());
    }

    @Test
    public void negate() {
        MixedFraction a = new MixedFraction(0, 1); // zero
        MixedFraction b = new MixedFraction(1, 2); // positive
        MixedFraction c = new MixedFraction(-2, 3); // negative

        assertEquals(0, a.negate().getNumerator());
        assertEquals(1, a.negate().getDenominator());
        assertEquals(-1, b.negate().getNumerator());
        assertEquals(2, b.negate().getDenominator());
        assertEquals(2, c.negate().getNumerator());
        assertEquals(3, c.negate().getDenominator());
    }
}
