package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import javafx.collections.ObservableList;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.UniqueIngredientList;

/**
 * Wraps all data at the inventory level
 * Duplicates are not allowed
 */
public class Inventory implements ReadOnlyIngredientList {

    private final UniqueIngredientList ingredients = new UniqueIngredientList();

    public Inventory() {}

    public Inventory(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the ingredient list with {@code ingredients}.
     * {@code ingredients} must not contain duplicate ingredients.
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients.setIngredients(ingredients);
    }


    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     * Called in constructor.
     */
    public void resetData(ReadOnlyIngredientList newData) {
        requireNonNull(newData);

        setIngredients(newData.getIngredientList());
    }

    // For ingredient level:

    /**
     * Returns true if an ingredient with the same identity as {@code ingredient} exists in the address book.
     */
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        return ingredients.contains(ingredient);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeIngredient(Ingredient key) {
        ingredients.remove(key);
    }

    @Override
    public String toString() {
        // TODO: After implementing Ingredients
    }

    @Override
    public ObservableList<Ingredient> getIngredientList() {
        return ingredients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && ingredients.equals(((AddressBook) other).ingredients));
    }
}
