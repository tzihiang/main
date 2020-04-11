package seedu.address.model.recipe;

import java.util.Comparator;

import seedu.address.model.ReadOnlyInventory;

/**
 * A comparator for {@code Recipe}, which compares recipes by their similarity to an inventory's ingredients.
 */
public class RecipeInventorySimilarityComparator implements Comparator<Recipe> {
    private final ReadOnlyInventory inventory;

    public RecipeInventorySimilarityComparator(ReadOnlyInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Compares recipes by their similarity to an inventory's ingredients.
     */
    @Override
    public int compare(Recipe o1, Recipe o2) {
        return Double.compare(calculateSimilarity(o2, inventory), calculateSimilarity(o1, inventory));
    }

    /**
     * Returns the proportion of recipe ingredients that the inventory contains.
     * @param recipe the specified recipe
     * @param inventory the specified inventory
     * @return a double value between 0 and 1 (inclusive)
     */
    public static double calculateSimilarity(Recipe recipe, ReadOnlyInventory inventory) {
        if (recipe.getIngredients().size() == 0) {
            return 0;
        }

        return recipe.getIngredients().asUnmodifiableObservableList().stream()
                .map(recipeIngredient -> inventory.getIngredientList().stream()
                    .filter(inventoryIngredient -> inventoryIngredient.isCompatibleWith(recipeIngredient))
                    .findFirst()
                    .map(inventoryIngredient -> inventoryIngredient.asProportionOf(recipeIngredient))
                    .orElseGet(() -> (inventory.getIngredientList().stream().filter(inventoryIngredient
                        -> inventoryIngredient.isSameIngredient(recipeIngredient)).count() > 0) ? 0.5 : 0))
                .reduce(0.0, (x, y) -> x + y, (x, y) -> x + y) / recipe.getIngredients().size();
    }
}
