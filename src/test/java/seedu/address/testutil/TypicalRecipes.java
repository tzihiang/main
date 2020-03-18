package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.recipe.Recipe;

/**
 * A utility class containing a list of {@code Recipe} objects to be used in tests.
 */
public class TypicalRecipes {

    public static final Recipe CARBONARA = new RecipeBuilder().withRecipeName("Carbonara")
            .withRecipeDescription("The best dish sold in NUS.")
            .withTags("Pasta", "Italian", "Simple", "Creamy").build();
    public static final Recipe SCRAMBLED_EGG = new RecipeBuilder().withRecipeName("Scrambled Egg")
            .withRecipeDescription("Gordan Ramsay's famous creamy and fluffly srambled eggs")
            .withTags("Simple", "Celebrity").build();
    public static final Recipe AGLIO_OLIO = new RecipeBuilder().withRecipeName("Aglio Olio")
            .withRecipeDescription("A traditional Italian pasta dish made with garlic in olive oil,"
                    + " and dried red chili flakes.").build();
    public static final Recipe SPAGHETTI_BOLOGNESE = new RecipeBuilder().withRecipeName("Spaghetti bolognese")
            .withRecipeDescription("Jaime Oliver's great version of the classic Italian Bolognese.").build();

    private TypicalRecipes() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical recipes.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();

        for (Recipe recipe : getTypicalRecipes()) {
            ab.addRecipe(recipe);
        }

        return ab;
    }

    public static List<Recipe> getTypicalRecipes() {
        return new ArrayList<>(Arrays.asList(CARBONARA, SCRAMBLED_EGG, AGLIO_OLIO, SPAGHETTI_BOLOGNESE));
    }
}
