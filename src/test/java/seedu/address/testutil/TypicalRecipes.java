package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Cookbook;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;
import seedu.address.model.ingredient.UniqueIngredientList;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeDescription;
import seedu.address.model.recipe.RecipeName;
import seedu.address.model.step.Step;
import seedu.address.model.step.UniqueStepList;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class containing a list of {@code Recipe} objects to be used in tests.
 */
public class TypicalRecipes {

    public static final Recipe AGLIO_OLIO = new RecipeBuilder().withRecipeName("Aglio Olio")
            .withRecipeDescription("A traditional Italian pasta dish made with garlic in olive oil,"
                    + " and dried red chili flakes.").build();
    public static final Recipe CARBONARA = new RecipeBuilder().withRecipeName("Carbonara")
            .withRecipeDescription("The best dish sold in NUS.")
            .withTags("Pasta", "Italian", "Simple", "Creamy").build();
    public static final Recipe SCRAMBLED_EGG = new RecipeBuilder().withRecipeName("Scrambled Egg")
            .withRecipeDescription("Gordan Ramsay's famous creamy and fluffly srambled eggs")
            .withTags("Simple", "Celebrity").build();
    public static final Recipe SPAGHETTI_BOLOGNESE = new RecipeBuilder().withRecipeName("Spaghetti bolognese")
            .withRecipeDescription("Jaime Oliver's great version of the classic Italian Bolognese.").build();

    // Manually added
    public static final Recipe CHICKEN_CHOP = new RecipeBuilder().withRecipeName("Chicken Chop")
            .withRecipeDescription("Delicious grilled chicken covered in mushroom sauce.").build();
    public static final Recipe FISH_AND_CHIPS = new RecipeBuilder().withRecipeName("Fish & Chips")
            .withRecipeDescription("Fried fish in batter served with deep-fried chips.").build();

    private TypicalRecipes() {} // prevents instantiation

    /**
     * Returns a {@code Recipe} with sample ingredients, steps, and tags.
     */
    public static Recipe getValidRecipe() {
        UniqueIngredientList ingredients = new UniqueIngredientList();
        ingredients.add(new Ingredient(new IngredientName("Ingredient"), new IngredientQuantity("5")));
        UniqueStepList steps = new UniqueStepList();
        steps.add(new Step("Step"));
        return new Recipe(
                new RecipeName("Recipe"),
                new RecipeDescription("Description"),
                ingredients,
                steps,
                SampleDataUtil.getTagSet("Tag"));
    }

    /**
     * Returns a {@code Cookbook} with all the typical recipes.
     */
    public static Cookbook getTypicalCookbook() {
        Cookbook cookbook = new Cookbook();

        for (Recipe recipe : getTypicalRecipes()) {
            cookbook.addRecipe(recipe);
        }

        return cookbook;
    }

    public static List<Recipe> getTypicalRecipes() {
        return new ArrayList<>(Arrays.asList(CARBONARA, SCRAMBLED_EGG, AGLIO_OLIO, SPAGHETTI_BOLOGNESE));
    }
}
