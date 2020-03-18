package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.person.Person;
import seedu.address.model.recipe.Recipe;

import java.util.List;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    ObservableList<Ingredient> getIngredientList();
    ObservableList<Recipe> getRecipeList();
    ObservableList<Ingredient> getCart();
}
