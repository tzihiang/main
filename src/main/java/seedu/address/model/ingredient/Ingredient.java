package seedu.address.model.ingredient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an ingredient.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Ingredient {

    private final IngredientName ingredientName;
    private final IngredientQuantity ingredientQuantity;

    /**
     * Every field must be present and not null.
     */
    public Ingredient(IngredientName ingredientName, IngredientQuantity ingredientQuantity) {
        requireAllNonNull(ingredientName, ingredientQuantity);
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
    }

    public IngredientName getName() {
        return ingredientName;
    }

    public IngredientQuantity getQuantity() {
        return ingredientQuantity;
    }

    /**
     * Returns true if both ingredients have the same name.
     * This defines a weaker notion of equality between two ingredients.
     */
    public boolean isSameIngredient(Ingredient otherIngredient) {
        if (otherIngredient == this) {
            return true;
        }

        return otherIngredient != null
                && otherIngredient.getName().equals(getName());
    }

    /**
     * Returns true if both ingredients have the same name and quantity.
     * This defines a stronger notion of equality between two ingredients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Ingredient)) {
            return false;
        }

        Ingredient otherIngredient = (Ingredient) other;
        return otherIngredient.getName().equals(getName());
        // && otherIngredient.getQuantity().equals(getQuantity());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(ingredientName, ingredientQuantity);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", ingredientName, ingredientQuantity);
    }

}
