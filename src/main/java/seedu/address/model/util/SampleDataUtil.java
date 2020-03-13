package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeDescription;
import seedu.address.model.recipe.RecipeName;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
                new Person(new Name("Alex Yeoh"), getTagSet("friends")),
                new Person(new Name("Bernice Yu"), getTagSet("colleagues", "friends")),
                new Person(new Name("Charlotte Oliveiro"), getTagSet("neighbours")),
                new Person(new Name("David Li"), getTagSet("family")),
                new Person(new Name("Irfan Ibrahim"), getTagSet("classmates")),
                new Person(new Name("Roy Balakrishnan"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Ingredient sampleIngredient : getSampleIngredients()) {
            sampleAb.addIngredient(sampleIngredient);
        }

        return sampleAb;
    }

    public static Ingredient[] getSampleIngredients() {
        return new Ingredient[] {
                new Ingredient(new IngredientName("Eggs"), new IngredientQuantity("10")),
                new Ingredient(new IngredientName("Parmesan"), new IngredientQuantity("100 g")),
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
                new Recipe(new RecipeName("Carbonara"), new RecipeDescription("The best dish sold in NUS.")),
                new Recipe(new RecipeName("Aglio olio"), new RecipeDescription("A traditional Italian pasta dish from made with garlic in olive oil, and dried red chili flakes.")),
                new Recipe(new RecipeName("Spaghetti bolognese"), new RecipeDescription("Jaime Oliver's great version of the classic Italian Bolognese."))
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
