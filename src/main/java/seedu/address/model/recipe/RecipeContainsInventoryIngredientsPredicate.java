package seedu.address.model.recipe;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.core.fraction.MixedFraction;
import seedu.address.commons.util.StringUtil;
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
        if (recipe.getIngredientNamesString().length() == 0) {
            System.out.println(recipe.getIngredientNamesString());
            return false;
        }

        return inventory.getIngredientList().stream().anyMatch(ingredient ->
            StringUtil.containsWordIgnoreCase(recipe.getIngredientNamesString(), ingredient.getName().ingredientName));
    }

    /**
     * This method takes in a recipe, and returns a MixedFractionObject based on
     * the matching number of ingredients that recipe has with the inventory
     * @param recipe
     * @return MixedFraction
     */
    public MixedFraction calculateSimilarity(Recipe recipe){
        // This will filter the available ingredients that particular recipe has that is in the inventory
        List<Ingredient> availableIngredients = recipe.getIngredients()
                .asUnmodifiableObservableList().stream()
                .filter(recipeIngredient -> inventory.getIngredientList().stream()
                        .anyMatch(inventoryIngredient -> inventoryIngredient.equals(recipeIngredient)))
                        .collect(Collectors.toList());
        return new MixedFraction(availableIngredients.size(),
                recipe.getIngredients().asUnmodifiableObservableList().size());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof RecipeContainsInventoryIngredientsPredicate // instanceof handles nulls
            && inventory.equals(((RecipeContainsInventoryIngredientsPredicate) other).inventory)); // state check
    }
}
