package seedu.address.commons.core.fraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.math.BigDecimal;

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
    public void getFromBigDecimal() {
        // integer
        assertEquals(1, MixedFraction.getFromBigDecimal(new BigDecimal("1")).intValue());
        assertEquals(1, MixedFraction.getFromBigDecimal(new BigDecimal("1")).getNumerator());
        assertEquals(1, MixedFraction.getFromBigDecimal(new BigDecimal("1")).getDenominator());

        // double
        assertEquals(1, MixedFraction.getFromBigDecimal(new BigDecimal("1.2")).intValue());
        assertEquals(6, MixedFraction.getFromBigDecimal(new BigDecimal("1.2")).getNumerator());
        assertEquals(5, MixedFraction.getFromBigDecimal(new BigDecimal("1.2")).getDenominator());

        // negative double
        assertEquals(-1, MixedFraction.getFromBigDecimal(new BigDecimal("-1.2")).intValue());
        assertEquals(-6, MixedFraction.getFromBigDecimal(new BigDecimal("-1.2")).getNumerator());
        assertEquals(5, MixedFraction.getFromBigDecimal(new BigDecimal("-1.2")).getDenominator());
    }

    @Test
    public void add() {
        MixedFraction a = new MixedFraction(3, 2);
        MixedFraction b = new MixedFraction(8, 3);
        MixedFraction c = new MixedFraction(-5, 3);

        // null input
        assertThrows(NullArgumentException.class, () -> a.add(null));

        // valid input
        assertEquals(25, a.add(b).getNumerator());
        assertEquals(6, a.add(b).getDenominator());
        assertEquals(-1, a.add(c).getNumerator());
        assertEquals(6, a.add(c).getDenominator());
        assertEquals(1, b.add(c).getNumerator());
        assertEquals(1, b.add(c).getDenominator());
    }

    @Test
    public void subtract() {
        MixedFraction a = new MixedFraction(3, 2);
        MixedFraction b = new MixedFraction(8, 3);
        MixedFraction c = new MixedFraction(-5, 3);

        // null input
        assertThrows(NullArgumentException.class, () -> a.subtract(null));

        // valid input
        assertEquals(-7, a.subtract(b).getNumerator());
        assertEquals(6, a.subtract(b).getDenominator());
        assertEquals(19, a.subtract(c).getNumerator());
        assertEquals(6, a.subtract(c).getDenominator());
        assertEquals(13, b.subtract(c).getNumerator());
        assertEquals(3, b.subtract(c).getDenominator());
    }

    @Test
    public void toString_validMixedFraction_returnsStringRepresentation() {
        assertEquals("1/2", new MixedFraction(1, 2).toString());
        assertEquals("1 2/3", new MixedFraction(5, 3).toString());
        assertEquals("-1/2", new MixedFraction(-1, 2).toString());
        assertEquals("-1 2/3", new MixedFraction(-5, 3).toString());
        assertEquals("0", new MixedFraction(0, 1).toString());
    }
}
