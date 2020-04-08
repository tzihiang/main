package seedu.address.model.recipe;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalRecipes.getValidRecipe;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;

public class RecipeContainsInventoryIngredientsPredicateTest {
    private static final Ingredient VALID_INGREDIENT = new Ingredient(new IngredientName("Ingredient"),
            new IngredientQuantity("5"));
    private static final Recipe VALID_RECIPE = getValidRecipe();

    @Test
    public void test_validInput() {
        Model model = new ModelManager();
        model.addCookbookRecipe(VALID_RECIPE);
        model.addInventoryIngredient(VALID_INGREDIENT);
        RecipeContainsInventoryIngredientsPredicate c =
            new RecipeContainsInventoryIngredientsPredicate(model.getInventory());
        assertFalse(c.test(VALID_RECIPE));
    }

    @Test
    public void equals() {
        Model model = new ModelManager();
        model.addCookbookRecipe(VALID_RECIPE);
        model.addInventoryIngredient(VALID_INGREDIENT);
        RecipeContainsInventoryIngredientsPredicate c =
                new RecipeContainsInventoryIngredientsPredicate(model.getInventory());
        assertEquals(c, new RecipeContainsInventoryIngredientsPredicate(model.getInventory()));
        assertNotEquals(c, null);
    }
}
