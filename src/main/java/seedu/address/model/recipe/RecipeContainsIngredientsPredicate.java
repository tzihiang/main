package seedu.address.model.recipe;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Ingredient} matches any of the ingredients available in the inventory.
 */
public class RecipeContainsIngredientsPredicate implements Predicate<Recipe> {
    private List<String> ingredientNames;

    public RecipeContainsIngredientsPredicate(List<String> ingredientNames) {
        this.ingredientNames = ingredientNames;
    }

    @Override
    public boolean test(Recipe recipe) {
        return ingredientNames.stream()
            .anyMatch(ingredientName ->
                StringUtil.containsWordIgnoreCase(recipe.getIngredientNameString(), ingredientName));
    }
}
