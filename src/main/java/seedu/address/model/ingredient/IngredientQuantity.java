package seedu.address.model.ingredient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

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

    /**
     * Returns true if a given string is a valid ingredient quantity.
     */
    public static boolean isValidIngredientQuantity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the value of an ingredient's quantity.
     *
     * @param ingredientQuantity The given string representing an ingredient's quantity.
     * @return The value of the ingredient's IngredientQuantity.
     */
    private static Number parseValue(String ingredientQuantity) {
        Matcher wholeNumberMatcher = WHOLE_NUMBER_PATTERN.matcher(ingredientQuantity);
        Matcher decimalMatcher = DECIMAL_PATTERN.matcher(ingredientQuantity);
        Matcher mixedFractionMatcher = FRACTION_PATTERN.matcher(ingredientQuantity);
        if (decimalMatcher.find()) {
            return Double.parseDouble(decimalMatcher.group().trim());
        } else if (mixedFractionMatcher.find()) {
            return MixedFraction.parseMixedFraction(mixedFractionMatcher.group().trim());
        } else if (wholeNumberMatcher.find()) {
            return Integer.parseInt(wholeNumberMatcher.group().trim());
        }
        assert false;
        return null;
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
