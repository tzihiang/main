package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

import seedu.address.model.ingredient.CompatibleIngredientList;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;

/**
 * Wraps all data at the ingredient list level
 * Duplicates are not allowed
 */
public abstract class IngredientList implements ReadOnlyIngredientList {

    private final CompatibleIngredientList ingredients = new CompatibleIngredientList();

    public IngredientList() {}

    public IngredientList(ReadOnlyIngredientList toBeCopied) {
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
     * Resets the existing data of this {@code IngredientList} with {@code newData}.
     * Called in constructor.
     */
    public void resetData(ReadOnlyIngredientList newData) {
        requireNonNull(newData);

        setIngredients(newData.getIngredientList());
    }

    // For ingredient level:

    /**
     * Returns true if an ingredient with the same identity as {@code ingredient} exists in the list of ingredients.
     */
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        return ingredients.contains(ingredient);
    }

    /**
     * Adds an ingredient to the list of ingredients.
     * The ingredient must not already exist in the list of ingredients.
     */
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    /**
     * Replaces the given ingredient {@code target} in the list with {@code editedIngredient}.
     * {@code target} must exist in the list of ingredients.
     * The ingredient identity of {@code editedIngredient} must not be the same as another existing ingredient in the
     * list of ingredients.
     */
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireNonNull(editedIngredient);

        ingredients.setIngredient(target, editedIngredient);
    }

    /**
     * Removes {@code key} from this {@code ingredientList}.
     * {@code key} must exist in the list of ingredients.
     */
    public void removeIngredient(Ingredient key) {
        ingredients.remove(key);
    }

    /**
     * Removes ingredients with the name {@code key} from this {@code ingredientList}.
     * {@code key} must exist in the list of ingredients.
     */
    public void removeIngredient(IngredientName key) {
        ingredients.remove(key);
    }

    @Override
    public String toString() {
        return ingredients.asUnmodifiableObservableList().size() + " ingredients";
        // TODO: refine later
    }

    @Override
    public CompatibleIngredientList getCompatibleIngredientList() {
        return ingredients;
    }

    @Override
    public ObservableList<Ingredient> getIngredientList() {
        return ingredients.asUnmodifiableObservableList();
    }
}
