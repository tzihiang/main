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

    /**
     * This method takes in a recipe, and returns a MixedFractionObject based on
     * the matching number of ingredients that recipe has with the inventory
     * @param recipe
     * @return MixedFraction
     */
    public double calculateSimilarity(Recipe recipe) {
        //This is to handle a scenario if the recipe has no ingredients.
        if (recipe.getIngredients().asUnmodifiableObservableList().size() == 0) {
            return 0;
        }
        //This will filter the available ingredients that particular recipe has that is in the inventory
        List<Ingredient> availableIngredients = recipe.getIngredients()
                .asUnmodifiableObservableList().stream()
                .filter(recipeIngredient -> inventory.getIngredientList().stream()
                        .anyMatch(inventoryIngredient -> inventoryIngredient.equals(recipeIngredient)))
                        .collect(Collectors.toList());
        return availableIngredients.size() / recipe.getIngredients().asUnmodifiableObservableList().size();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof RecipeContainsInventoryIngredientsPredicate // instanceof handles nulls
            && inventory.equals(((RecipeContainsInventoryIngredientsPredicate) other).inventory)); // state check
    }
}
