package seedu.address.model.recipe;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ingredient.Ingredient;

/**
 * Tests that a {@code Recipe}'s {@code Ingredient} matches any of the ingredients available in the inventory.
 */
public class RecipeContainsInventoryIngredientsPredicate implements Predicate<Recipe> {
    private ReadOnlyInventory inventory;
    public RecipeContainsInventoryIngredientsPredicate(ReadOnlyInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean test(Recipe recipe) {
        return inventory.calculateSimilarity(recipe) > 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof RecipeContainsInventoryIngredientsPredicate // instanceof handles nulls
            && inventory.equals(((RecipeContainsInventoryIngredientsPredicate) other).inventory)); // state check
    }
}
