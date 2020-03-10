package seedu.address.commons.core.fraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MixedFractionTest {
    @Test
    public void parseMixedFraction() {
        // null input
        assertThrows(NumberFormatException.class, () -> MixedFraction.parseMixedFraction(null));

        // invalid input
        assertThrows(NumberFormatException.class, () -> MixedFraction.parseMixedFraction(""));
        assertThrows(NumberFormatException.class, () -> MixedFraction.parseMixedFraction("null"));
        assertThrows(NumberFormatException.class, () -> MixedFraction.parseMixedFraction("1"));
        assertThrows(NumberFormatException.class, () -> MixedFraction.parseMixedFraction("1 / 2"));
        assertThrows(NumberFormatException.class, () -> MixedFraction.parseMixedFraction("0.5"));

        // valid input
        assertEquals(0, MixedFraction.parseMixedFraction("1/2").intValue());
        assertEquals(1, MixedFraction.parseMixedFraction("1/2").getNumerator());
        assertEquals(2, MixedFraction.parseMixedFraction("1/2").getDenominator());
        assertEquals(1, MixedFraction.parseMixedFraction("1 2/3").intValue());
        assertEquals(5, MixedFraction.parseMixedFraction("1 2/3").getNumerator());
        assertEquals(3, MixedFraction.parseMixedFraction("1 2/3").getDenominator());
    }
}
