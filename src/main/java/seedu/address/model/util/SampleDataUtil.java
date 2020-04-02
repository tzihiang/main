package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Cart;
import seedu.address.model.Cookbook;
import seedu.address.model.Inventory;
import seedu.address.model.ReadOnlyCart;
import seedu.address.model.ReadOnlyCookbook;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;
import seedu.address.model.ingredient.UniqueIngredientList;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeDescription;
import seedu.address.model.recipe.RecipeName;
import seedu.address.model.step.UniqueStepList;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code CookingPapa} with sample data.
 */
public class SampleDataUtil {
    public static ReadOnlyCookbook getSampleCookbook() {
        Cookbook sampleCookbook = new Cookbook();
        for (Recipe sampleRecipe : getSampleRecipes()) {
            sampleCookbook.addRecipe(sampleRecipe);
        }
        return sampleCookbook;
    }

    public static ReadOnlyInventory getSampleInventory() {
        Inventory sampleInventory = new Inventory();
        for (Ingredient sampleIngredient : getSampleIngredients()) {
            sampleInventory.addIngredient(sampleIngredient);
        }
        return sampleInventory;
    }

    public static ReadOnlyCart getSampleCart() {
        Cart sampleCart = new Cart();
        for (Ingredient sampleIngredient : getSampleIngredients()) {
            sampleCart.addIngredient(sampleIngredient);
        }
        return sampleCart;
    }

    public static Ingredient[] getSampleIngredients() {
        return new Ingredient[] {
            new Ingredient(new IngredientName("Eggs"), new IngredientQuantity("10")),
            new Ingredient(new IngredientName("Parmesan"), new IngredientQuantity("1 kg")),
            new Ingredient(new IngredientName("Bacon"), new IngredientQuantity("10 pieces")),
            new Ingredient(new IngredientName("Linguine"), new IngredientQuantity("250 g")),
            new Ingredient(new IngredientName("Olive oil"), new IngredientQuantity("600 ml")),
            new Ingredient(new IngredientName("Black pepper"), new IngredientQuantity("300 g")),
            new Ingredient(new IngredientName("Salt"), new IngredientQuantity("500 g")),
            new Ingredient(new IngredientName("Garlic"), new IngredientQuantity("5 cloves")),
            new Ingredient(new IngredientName("Dried chili flakes"), new IngredientQuantity("50 g")),
            new Ingredient(new IngredientName("Butter"), new IngredientQuantity("3 sticks")),
            new Ingredient(new IngredientName("Minced beef"), new IngredientQuantity("500 g")),
            new Ingredient(new IngredientName("Tomatoes"), new IngredientQuantity("10"))
        };
    }

    public static Recipe[] getSampleRecipes() {
        return new Recipe[]{
            new Recipe(new RecipeName("Scrambled eggs"),
                new RecipeDescription("Gordan Ramsay's famous creamy and fluffy scrambled eggs."),
                new UniqueIngredientList(), new UniqueStepList(),
                getTagSet("Simple", "Celebrity")),
            new Recipe(new RecipeName("Carbonara"),
                new RecipeDescription("The best dish sold in NUS."),
                new UniqueIngredientList(), new UniqueStepList(),
                getTagSet("Pasta", "Italian", "Simple", "Creamy")),
            new Recipe(new RecipeName("Aglio olio"),
                new RecipeDescription("A traditional Italian pasta dish made "
                    + "with garlic in olive oil, and dried red chili flakes."),
                new UniqueIngredientList(), new UniqueStepList(),
                getTagSet("Pasta", "Spicy", "Italian", "Vegan")),
            new Recipe(new RecipeName("Spaghetti bolognese"),
                new RecipeDescription("Jaime Oliver's great version of the classic Italian Bolognese."),
                new UniqueIngredientList(), new UniqueStepList(),
                getTagSet("Pasta", "Tomatoes", "Celebrity"))
        };
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
