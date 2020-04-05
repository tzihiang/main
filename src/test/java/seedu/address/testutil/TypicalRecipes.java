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

    private static final Recipe[] TYPICAL_RECIPES = SampleDataUtil.getSampleRecipes();
    public static final Recipe SCRAMBLED_EGG = TYPICAL_RECIPES[0];
    public static final Recipe CARBONARA = TYPICAL_RECIPES[1];
    public static final Recipe AGLIO_OLIO = TYPICAL_RECIPES[2];
    public static final Recipe SPAGHETTI_BOLOGNESE = TYPICAL_RECIPES[3];

    /*
    public static final Recipe AGLIO_OLIO = new RecipeBuilder().withRecipeName("Aglio Olio")
            .withRecipeDescription("A traditional Italian pasta dish made with garlic in olive oil,"
                    + " and dried red chili flakes.")
            .withIngredients(new IngredientBuilder().withName("Spaghetti").withQuantity("1 Serving").build())
            .withIngredients(new IngredientBuilder().withName("Garlic").withQuantity("6 cloves").build())
            .withIngredients(new IngredientBuilder().withName("Olive oil").withQuantity("1 tbsp").build())
            .withIngredients(new IngredientBuilder().withName("Red Pepper flakes").withQuantity("1 tsp").build())
            .withIngredients(new IngredientBuilder().withName("Parsley").withQuantity("1 Serving").build())
            .withIngredients(new IngredientBuilder().withName("Cheese").withQuantity("200 g").build())
            .withStep(new StepBuilder("Bring a large pot of lightly salted water to a boil. "
                    + "Cook spaghetti in the boiling water, stirring occasionally until cooked through "
                    + "but firm to the bite, about 12 minutes. Drain and transfer to a pasta bowl.").build())
            .withStep(new StepBuilder("Combine garlic and olive oil in a cold skillet. "
                    + "Cook over medium heat to slowly toast garlic, about 10 minutes. "
                    + "Reduce heat to medium-low when olive oil begins to bubble. "
                    + "Cook and stir until garlic is golden brown, about another 5 minutes. "
                    + "Remove from heat.").build())
            .withStep(new StepBuilder("Stir red pepper flakes, black pepper, and salt into the pasta. "
                    + "Pour in olive oil and garlic, and sprinkle on Italian parsley "
                    + "and half of the cheese; "
                    + "stir until combined.").build())
            .withStep(new StepBuilder("Serve pasta topped with the remaining cheese.").build())
            .build();

    public static final Recipe CARBONARA = new RecipeBuilder().withRecipeName("Carbonara")
            .withRecipeDescription("The best dish sold in NUS.")
            .withIngredients(new IngredientBuilder().withName("Spaghetti").withQuantity("1 Serving").build())
            .withIngredients(new IngredientBuilder().withName("Olive oil").withQuantity("1 tbsp").build())
            .withIngredients(new IngredientBuilder().withName("Bacon").withQuantity("8 Slices").build())
            .withIngredients(new IngredientBuilder().withName("Garlic").withQuantity("1 Clove").build())
            .withIngredients(new IngredientBuilder().withName("Eggs").withQuantity("4").build())
            .withIngredients(new IngredientBuilder().withName("Cheese").withQuantity("200 g").build())
            .withIngredients(new IngredientBuilder().withName("Parsley").withQuantity("1 Serving").build())
            .withStep(new StepBuilder("In a large pot of boiling salted water, cook spaghetti pasta "
                    + "until al dente. Drain well. Toss with 1 tablespoon of olive oil, and set aside.").build())
            .withStep(new StepBuilder("Meanwhile in a large skillet, cook chopped bacon until "
                    + "slightly crisp; remove and drain onto paper towels. Reserve 2 tablespoons of bacon fat; "
                    + "add remaining 1 tablespoon olive oil, and heat in reused large skillet. "
                    + "Add chopped onion, and cook over medium heat until onion is translucent. "
                    + "Add minced garlic, and cook 1 minute more. Add wine if desired; cook one more minute.").build())
            .withStep(new StepBuilder("Return cooked bacon to pan; add cooked and drained spaghetti. "
                    + "Toss to coat and heat through, adding more olive oil if it seems dry or is sticking together. "
                    + "Add beaten eggs and cook, tossing constantly with tongs or large fork "
                    + "until eggs are barely set. Quickly add 1/2 cup cheese, and toss again. "
                    + "Add salt and pepper to taste. (remember that bacon and cheese are very salty).").build())
            .withStep(new StepBuilder("Serve immediately with chopped parsley sprinkled on top, "
                    + "and extra Parmesan cheese at table.").build())
            .withTags("Pasta", "Italian", "Simple", "Creamy").build();

    public static final Recipe SCRAMBLED_EGG = new RecipeBuilder().withRecipeName("Scrambled Egg")
            .withRecipeDescription("Gordan Ramsay's famous creamy and fluffy scrambled eggs")
            .withIngredients(new IngredientBuilder().withName("Eggs").withQuantity("4").build())
            .withIngredients(new IngredientBuilder().withName("Milk").withQuantity("100ml").build())
            .withIngredients(new IngredientBuilder().withName("Butter").withQuantity("30g").build())
            .withStep(new StepBuilder("Beat eggs, milk, salt and pepper "
                    + "in medium bowl until blended.").build())
            .withStep(new StepBuilder("Heat butter in large nonstick skillet over medium heat until hot. "
                    + "Pour in egg mixture. As eggs begin to set, gently PULL the eggs across the pan with a spatula, "
                    + "forming large soft curds.").build())
            .withStep(new StepBuilder("Continue cooking—pulling, lifting and folding eggs—until thickened "
                    + "and no visible liquid egg remains. Do not stir constantly. "
                    + "Remove from heat. Serve immediately.").build())
            .withTags("Simple", "Celebrity").build();

    public static final Recipe VANILLA_ICE_CREAM = new RecipeBuilder().withRecipeName("Vanilla Ice Cream")
            .withRecipeDescription("A simple and easy to make Vanilla Ice cream recipe anybody can make"
                    + " in less than 30 minutes!")
            .withIngredients(new IngredientBuilder().withName("Heavy whipping cream").withQuantity("2 Cups").build())
            .withIngredients(new IngredientBuilder().withName("Sugar").withQuantity("1 Cup").build())
            .withIngredients(new IngredientBuilder().withName("Half-and-half-cream").withQuantity("2 Cups").build())
            .withIngredients(new IngredientBuilder().withName("Vanilla extract").withQuantity("2 tsp").build())
            .withStep(new StepBuilder("Stir sugar, cream, and milk into a saucepan over low heat "
                    + "until sugar has dissolved. Heat just until mix is hot and a "
                    + "small ring of foam appears around the edge.").build())
            .withStep(new StepBuilder("Transfer cream mixture to a pourable container such as a large "
                    + "measuring cup. Stir in vanilla extract and chill mix thoroughly, at least 2 hours. "
                    + "(Overnight is best.)").build())
            .withStep(new StepBuilder("Pour cold ice cream mix into an ice cream maker, "
                    + "turn on the machine, and churn according to manufacturer's directions, "
                    + "20 to 25 minutes.").build())
            .withStep(new StepBuilder("When ice cream is softly frozen, serve immediately or place a "
                    + "piece of plastic wrap directly on the ice cream and place in "
                    + "freezer to ripen, 2 to 3 hours.").build())
            .withTags("Ice Cream", "Beginner")
            .build();

    public static final Recipe SPAGHETTI_BOLOGNESE = new RecipeBuilder().withRecipeName("Spaghetti Bolognese")
            .withRecipeDescription("Jaime Oliver's great version of the classic Italian Bolognese.")
            .withIngredients(new IngredientBuilder().withName("Olive Oil").withQuantity("2 tbsp").build())
            .withIngredients(new IngredientBuilder().withName("Beef").withQuantity("400 g").build())
            .withIngredients(new IngredientBuilder().withName("Onion").withQuantity("1").build())
            .withIngredients(new IngredientBuilder().withName("Garlic").withQuantity("2 cloves").build())
            .withIngredients(new IngredientBuilder().withName("Carrot").withQuantity("100 g").build())
            .withIngredients(new IngredientBuilder().withName("Tomatoes").withQuantity("800 g").build())
            .withIngredients(new IngredientBuilder().withName("Beef Stock Cube").withQuantity("1").build())
            .withIngredients(new IngredientBuilder().withName("Spaghetti").withQuantity("1 Serving").build())
            .withStep(new StepBuilder("Heat a large saucepan over a medium heat. Add a tablespoon "
                    + "of olive oil and once hot add the beef mince and a pinch of salt and pepper. "
                    + "Cook the mince until well browned over a medium high heat. Be careful not to burn the mince. "
                    + "It just needs to be a dark brown colour. Once browned, transfer the "
                    + "mince to a bowl and set aside.").build())
            .withStep(new StepBuilder("Add another tablespoon of oil to the saucepan you browned the mince "
                    + "in and turn the heat to medium. Add the onions and a pinch of salt and fry gently for "
                    + "5 to 6 minutes, or until softened and translucent. Add the garlic and cook for another "
                    + "2 minutes. Add the grated carrot then pour the mince and any juices in the bowl "
                    + "back into the saucepan.").build())
            .withStep(new StepBuilder("Add the tomatoes to the pan and stir well to mix. Pour in the stock,"
                    + "bring to a simmer and then reduce the temperature to simmer gently for 45 minutes,"
                    + "or until the sauce is thick and rich. Taste and adjust the seasoning as necessary.").build())
            .withStep(new StepBuilder("When ready to cook the spaghetti, heat a large saucepan of water "
                    + "and add a pinch of salt. Cook according to the packet instructions. Once the spaghetti is "
                    + "cooked through, drain and add to the pan with the bolognese sauce. "
                    + "Mix well and serve.").build())
            .build();

    // Manually added
    public static final Recipe CHICKEN_CHOP = new RecipeBuilder().withRecipeName("Chicken Chop")
            .withRecipeDescription("Delicious grilled chicken covered in mushroom sauce.").build();
    public static final Recipe FISH_AND_CHIPS = new RecipeBuilder().withRecipeName("Fish & Chips")
            .withRecipeDescription("Fried fish in batter served with deep-fried chips.")
            .withTags("Western").build();
     */


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
        return new ArrayList<>(Arrays.asList(AGLIO_OLIO, CARBONARA, SCRAMBLED_EGG, SPAGHETTI_BOLOGNESE));
    }
}
