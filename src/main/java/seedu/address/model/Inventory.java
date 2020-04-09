package seedu.address.model;

import static java.util.Objects.requireNonNull;

import seedu.address.model.recipe.Recipe;

/**
 * Wraps all data at the inventory level
 */
public class Inventory extends IngredientList implements ReadOnlyInventory {

    public Inventory() {}

    public Inventory(ReadOnlyInventory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code Inventory} with {@code newData}.
     * Called in constructor.
     */
    public void resetData(ReadOnlyInventory newData) {
        requireNonNull(newData);

        setIngredients(newData.getIngredientList());
    }

    public double calculateSimilarity(Recipe recipe) {
        if (recipe.getIngredients().size() == 0) {
            return 0;
        }

        return recipe.getIngredients().asUnmodifiableObservableList().stream()
                .map(recipeIngredient ->
                    getIngredientList().stream()
                        .filter(inventoryIngredient -> inventoryIngredient.isCompatibleWith(recipeIngredient))
                        .findFirst()
                        .map(inventoryIngredient -> inventoryIngredient.asProportionOf(recipeIngredient))
                        .orElseGet(() -> (getIngredientList().stream().filter(inventoryIngredient
                            -> inventoryIngredient.isSameIngredient(recipeIngredient)).count() > 0) ? 0.5 : 0))
                .reduce(0.0, (x, y) -> x + y, (x, y) -> x + y) / recipe.getIngredients().size();
    }

    @Override
    public String toString() {
        return getIngredientList().size() + " ingredients";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Inventory // instanceof handles nulls
                && this.getCompatibleIngredientList().equals(((Inventory) other).getCompatibleIngredientList()));
    }
}
