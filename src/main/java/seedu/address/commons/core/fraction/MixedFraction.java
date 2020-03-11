package seedu.address.commons.core.fraction;

import org.apache.commons.math3.fraction.Fraction;

/**
 * Represents a mixed fraction.
 */
public class MixedFraction extends Fraction {
    private static final int NUM_OF_PARTS_IN_PURE_FRACTION = 1;
    private static final int WHOLE_NUMBER_PART_OF_PURE_FRACTION = 0;
    private static final int FRACTION_PART_POSITION_IN_PURE_FRACTION = 0;
    private static final int NUM_OF_PARTS_IN_MIXED_FRACTION = 2;
    private static final int WHOLE_NUMBER_PART_POSITION_IN_MIXED_FRACTION = 0;
    private static final int FRACTION_PART_POSITION_IN_MIXED_FRACTION = 1;
    private static final int NUM_OF_PARTS_IN_FRACTION = 2;
    private static final int NUMERATOR_POSITION = 0;
    private static final int DENOMINATOR_POSITION = 1;

    public MixedFraction(int value) {
        super(value);
    }

    public MixedFraction(double value) {
        super(value);
    }

    public MixedFraction(Fraction f) {
        super(f.getNumerator(), f.getDenominator());
    }

    public MixedFraction(int num, int den) {
        super(num, den);
    }

    /**
     * Parses the string argument as an unsigned mixed fraction.
     *
     * @param s a {@code String} containing the {@code MixedFraction} representation to be parsed
     * @return the mixed fraction value represented by the argument.
     * @throws NumberFormatException if the string does not contain a parsable unsigned mixed fraction.
     */
    public static MixedFraction parseUnsignedMixedFraction(String s) {
        if (s == null) {
            throw new NumberFormatException("null");
        }

        int wholeNumberPart;
        int fractionPosition;
        String[] splitInput = s.split("\\s+");
        if (splitInput.length == NUM_OF_PARTS_IN_PURE_FRACTION) {
            wholeNumberPart = WHOLE_NUMBER_PART_OF_PURE_FRACTION;
            fractionPosition = FRACTION_PART_POSITION_IN_PURE_FRACTION;
        } else if (splitInput.length == NUM_OF_PARTS_IN_MIXED_FRACTION) {
            wholeNumberPart = Integer.parseInt(splitInput[WHOLE_NUMBER_PART_POSITION_IN_MIXED_FRACTION]);
            fractionPosition = FRACTION_PART_POSITION_IN_MIXED_FRACTION;
        } else {
            throw new NumberFormatException(s);
        }

        String[] splitFraction = splitInput[fractionPosition].split("/");
        if (splitFraction.length != NUM_OF_PARTS_IN_FRACTION) {
            throw new NumberFormatException(s);
        }

        int numerator = Integer.parseInt(splitFraction[NUMERATOR_POSITION]);
        int denominator = Integer.parseInt(splitFraction[DENOMINATOR_POSITION]);
        numerator += wholeNumberPart * denominator;
        return new MixedFraction(numerator, denominator);
    }

    /**
     * Adds two {@code MixedFraction} values together.
     */
    public static MixedFraction sum(MixedFraction a, MixedFraction b) {
        return new MixedFraction(a.add(b));
    }

    /**
     * Return the additive inverse of this mixed fraction.
     */
    public MixedFraction negate() {
        return new MixedFraction(super.negate());
    }

    @Override
    public String toString() {
        int wholeNumberPart = intValue();
        int denominator = getDenominator();
        int numerator = getNumerator() - (wholeNumberPart * denominator);

        if (wholeNumberPart == 0) {
            return String.format("%d/%d", numerator, denominator);
        }
        return String.format("%d %d/%d", wholeNumberPart, numerator, denominator);
    }
}
