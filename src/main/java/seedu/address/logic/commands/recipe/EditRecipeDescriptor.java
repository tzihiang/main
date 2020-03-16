package seedu.address.logic.commands.recipe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.ingredient.UniqueIngredientList;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeDescription;
import seedu.address.model.recipe.RecipeName;
import seedu.address.model.step.UniqueStepList;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the recipe with. Each non-empty field value will replace the
 * corresponding field value of the recipe.
 */
public class EditRecipeDescriptor {
    private RecipeName name;
    private RecipeDescription description;
    private UniqueIngredientList ingredients;
    private UniqueStepList steps;
    private Set<Tag> tags;

    public EditRecipeDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditRecipeDescriptor(EditRecipeDescriptor toCopy) {
        setName(toCopy.name);
        setDescription(toCopy.description);
        setIngredients(toCopy.ingredients);
        setSteps(toCopy.steps);
        setTags(toCopy.tags);
    }

    /**
     * Creates and returns a {@code Recipe} with the details of {@code recipeToEdit}
     * edited with {@code editRecipeDescriptor}.
     */
    public static Recipe createEditedRecipe(Recipe recipeToEdit, EditRecipeDescriptor editRecipeDescriptor) {
        assert recipeToEdit != null;

        RecipeName updatedName = editRecipeDescriptor.getName()
                .orElse(recipeToEdit.getName());
        RecipeDescription updatedDescription = editRecipeDescriptor.getDescription()
                .orElse(recipeToEdit.getDescription());
        UniqueIngredientList updatedIngredients = editRecipeDescriptor.getIngredients()
                .orElse(recipeToEdit.getIngredients());
        UniqueStepList updatedSteps = editRecipeDescriptor.getSteps()
                .orElse(recipeToEdit.getSteps());
        Set<Tag> updatedTags = editRecipeDescriptor.getTags()
                .orElse(recipeToEdit.getTags());

        return new Recipe(updatedName, updatedDescription, updatedIngredients, updatedSteps, updatedTags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, description, ingredients, steps, tags);
    }

    public void setName(RecipeName name) {
        this.name = name;
    }

    public Optional<RecipeName> getName() {
        return Optional.ofNullable(name);
    }

    public void setDescription(RecipeDescription description) {
        this.description = description;
    }

    public Optional<RecipeDescription> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setIngredients(UniqueIngredientList ingredients) {
        this.ingredients = ingredients;
    }

    public Optional<UniqueIngredientList> getIngredients() {
        return Optional.ofNullable(ingredients);
    }

    public void setSteps(UniqueStepList steps) {
        this.steps = steps;
    }

    public Optional<UniqueStepList> getSteps() {
        return Optional.ofNullable(steps);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditRecipeDescriptor)) {
            return false;
        }

        // state check
        EditRecipeDescriptor e = (EditRecipeDescriptor) other;

        return getName().equals(e.getName())
                && getDescription().equals(e.getDescription())
                && getIngredients().equals(e.getIngredients())
                && getSteps().equals(e.getSteps())
                && getTags().equals(e.getTags());
    }
}
