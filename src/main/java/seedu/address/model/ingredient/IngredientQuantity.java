package seedu.address.model.ingredient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.fraction.MixedFraction;

/**
 * Represents the quantity of an ingredient.
 * Guarantees: immutable; is always valid
 */
public class IngredientQuantity {

    public static final String MESSAGE_CONSTRAINTS =
            "Ingredient quantities should only contain a value and a unit, where the value can be "
            + "whole numbers, decimals, or fractions, and the unit should only contain alphabets";

    public static final String WHOLE_NUMBER_REGEX = "[\\p{Digit}]+ *";
    public static final String DECIMAL_REGEX = "[\\p{Digit}]*\\.[\\p{Digit}]+ *";
    public static final String FRACTION_REGEX = "[\\p{Digit}]+( +[\\p{Digit}]+)?/[\\p{Digit}]+ *";
    public static final String UNIT_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";

    /*
     * The ingredient quantity must consist of a whole number, decimal, or fraction, and an optional unit.
     */
    public static final String VALIDATION_REGEX = String.format("((%s)|(%s)|(%s))(%s)?",
            WHOLE_NUMBER_REGEX, DECIMAL_REGEX, FRACTION_REGEX, UNIT_REGEX);

    public static final Pattern WHOLE_NUMBER_PATTERN = Pattern.compile(WHOLE_NUMBER_REGEX);
    public static final Pattern DECIMAL_PATTERN = Pattern.compile(DECIMAL_REGEX);
    public static final Pattern FRACTION_PATTERN = Pattern.compile(FRACTION_REGEX);
    public static final Pattern UNIT_PATTERN = Pattern.compile(UNIT_REGEX);

    private static final int LARGEST_DENOMINATOR = 4;

    public final Number value;
    public final String unit;

    /**
     * Constructs an {@code IngredientQuantity}.
     *
     * @param ingredientQuantity A valid ingredient quantity.
     */
    public IngredientQuantity(String ingredientQuantity) {
        requireNonNull(ingredientQuantity);
        checkArgument(isValidIngredientQuantity(ingredientQuantity), MESSAGE_CONSTRAINTS);
        this.value = parseValue(ingredientQuantity);
        this.unit = parseUnit(ingredientQuantity);
    }

    private IngredientQuantity(Number value, String unit) {
        requireAllNonNull(value, unit);
        this.value = value;
        this.unit = unit;
    }

    /**
     * Returns true if a given string is a valid ingredient quantity.
     */
    public static boolean isValidIngredientQuantity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isCompatible(IngredientQuantity other) {
        return this.unit.equals(other.unit);
    }

    /**
     * Adds the specified ingredient quantity to the ingredient quantity.
     *
     * @param other the ingredient quantity to be added.
     * @return a new ingredient quantity with the specified ingredient quantity added.
     */
    public IngredientQuantity add(IngredientQuantity other) {
        checkArgument(isCompatible(other));

        Number newValue = null;
        if (this.value instanceof Integer && other.value instanceof Integer) {
            newValue = Integer.sum((Integer) this.value, (Integer) other.value);
        } else if (this.value instanceof Double && other.value instanceof Double) {
            newValue = Double.sum((Double) this.value, (Double) other.value);
        } else if (this.value instanceof MixedFraction && other.value instanceof MixedFraction) {
            newValue = MixedFraction.sum((MixedFraction) this.value, (MixedFraction) other.value);
        } else if (this.value instanceof Integer && other.value instanceof Double) {
            newValue = Double.sum(this.value.doubleValue(), (Double) other.value);
        } else if (this.value instanceof Double && other.value instanceof Integer) {
            newValue = Double.sum((Double) this.value, other.value.doubleValue());
        } else if (this.value instanceof Integer && other.value instanceof MixedFraction) {
            newValue = MixedFraction.sum(new MixedFraction((Integer) this.value), (MixedFraction) other.value);
        } else if (this.value instanceof MixedFraction && other.value instanceof Integer) {
            newValue = MixedFraction.sum((MixedFraction) this.value, new MixedFraction((Integer) other.value));
        } else if (this.value instanceof Double && other.value instanceof MixedFraction) {
            newValue = MixedFraction.sum(new MixedFraction((Double) this.value), (MixedFraction) other.value);
        } else if (this.value instanceof MixedFraction && other.value instanceof Double) {
            newValue = MixedFraction.sum((MixedFraction) this.value, new MixedFraction((Double) other.value));
        }

        if (newValue instanceof MixedFraction && ((MixedFraction) newValue).getDenominator() > LARGEST_DENOMINATOR) {
            newValue = ((MixedFraction) newValue).doubleValue();
        }

        assert newValue != null;
        return new IngredientQuantity(newValue, unit);
    }

    /**
     * Subtracts the specified ingredient quantity from the ingredient quantity.
     * If the specified ingredient quantity is larger, the value of the ingredient quantity returned will be 0.
     *
     * @param other the ingredient quantity to be subtracted.
     * @return a new ingredient quantity with the specified ingredient quantity subtracted.
     */
    public IngredientQuantity subtract(IngredientQuantity other) {
        checkArgument(isCompatible(other));

        Number newValue = null;
        if (this.value instanceof Integer && other.value instanceof Integer) {
            newValue = Integer.sum((Integer) this.value, -Integer.valueOf((Integer) other.value));
        } else if (this.value instanceof Double && other.value instanceof Double) {
            newValue = Double.sum((Double) this.value, -Double.valueOf((Double) other.value));
        } else if (this.value instanceof MixedFraction && other.value instanceof MixedFraction) {
            newValue = MixedFraction.sum((MixedFraction) this.value, ((MixedFraction) other.value).negate());
        } else if (this.value instanceof Integer && other.value instanceof Double) {
            newValue = Double.sum(this.value.doubleValue(), -Double.valueOf((Double) other.value));
        } else if (this.value instanceof Double && other.value instanceof Integer) {
            newValue = Double.sum((Double) this.value, -other.value.doubleValue());
        } else if (this.value instanceof Integer && other.value instanceof MixedFraction) {
            newValue =
                    MixedFraction.sum(new MixedFraction((Integer) this.value), ((MixedFraction) other.value).negate());
        } else if (this.value instanceof MixedFraction && other.value instanceof Integer) {
            newValue =
                    MixedFraction.sum((MixedFraction) this.value, new MixedFraction((Integer) other.value).negate());
        } else if (this.value instanceof Double && other.value instanceof MixedFraction) {
            newValue =
                    MixedFraction.sum(new MixedFraction((Double) this.value), ((MixedFraction) other.value).negate());
        } else if (this.value instanceof MixedFraction && other.value instanceof Double) {
            newValue =
                    MixedFraction.sum((MixedFraction) this.value, new MixedFraction((Double) other.value).negate());
        }

        if (newValue instanceof MixedFraction && ((MixedFraction) newValue).getDenominator() > LARGEST_DENOMINATOR) {
            newValue = ((MixedFraction) newValue).doubleValue();
        }

        assert newValue != null;
        if (newValue.doubleValue() < 0) {
            newValue = 0;
        }

        return new IngredientQuantity(newValue, unit);
    }

    /**
     * Returns the value of an ingredient's quantity.
     *
     * @param ingredientQuantity The given string representing an ingredient's quantity.
     * @return The value of the ingredient's IngredientQuantity.
     */
    private static Number parseValue(String ingredientQuantity) {
        Number parsedValue = null;
        Matcher wholeNumberMatcher = WHOLE_NUMBER_PATTERN.matcher(ingredientQuantity);
        Matcher decimalMatcher = DECIMAL_PATTERN.matcher(ingredientQuantity);
        Matcher mixedFractionMatcher = FRACTION_PATTERN.matcher(ingredientQuantity);
        if (decimalMatcher.find()) {
            parsedValue = Double.parseDouble(decimalMatcher.group().trim());
        } else if (mixedFractionMatcher.find()) {
            parsedValue = MixedFraction.parseUnsignedMixedFraction(mixedFractionMatcher.group().trim());
        } else if (wholeNumberMatcher.find()) {
            parsedValue = Integer.parseUnsignedInt(wholeNumberMatcher.group().trim());
        }

        if (parsedValue instanceof MixedFraction
                && ((MixedFraction) parsedValue).getDenominator() > LARGEST_DENOMINATOR) {
            parsedValue = ((MixedFraction) parsedValue).doubleValue();
        }

        assert parsedValue != null;
        return parsedValue;
    }

    /**
     * Returns the unit of an ingredient's quantity.
     *
     * @param ingredientQuantity The given string representing an ingredient's quantity.
     * @return The unit of the ingredient's IngredientQuantity.
     */
    private static String parseUnit(String ingredientQuantity) {
        Matcher unitMatcher = UNIT_PATTERN.matcher(ingredientQuantity);
        unitMatcher.find();
        return unitMatcher.group().trim();
    }

    @Override
    public String toString() {
        return String.format("%s %s", value, unit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientQuantity // instanceof handles nulls
                && value.equals(((IngredientQuantity) other).value)
                && unit.equals(((IngredientQuantity) other).unit));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

}
