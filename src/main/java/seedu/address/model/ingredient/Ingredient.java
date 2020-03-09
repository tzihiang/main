package seedu.address.model.ingredient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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

    @Override
    public String toString() {
        return String.format("%s (%s)", ingredientName, ingredientQuantity);
    }

}
