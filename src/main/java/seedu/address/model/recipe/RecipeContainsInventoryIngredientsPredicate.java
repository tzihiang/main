package seedu.address.model.recipe;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.ReadOnlyInventory;

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
        return inventory.getIngredientList().stream().anyMatch(ingredient ->
            StringUtil.containsWordIgnoreCase(recipe.getIngredientNameString(), ingredient.getName().ingredientName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof RecipeContainsInventoryIngredientsPredicate // instanceof handles nulls
            && inventory.equals(((RecipeContainsInventoryIngredientsPredicate) other).inventory)); // state check
    }
}
