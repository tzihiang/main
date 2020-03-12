package seedu.address.model.recipe;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.ingredient.UniqueIngredientList;
import seedu.address.model.step.UniqueStepList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Recipe in the cookbook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Recipe {

    // Identity fields
    private final RecipeName name;
    private final RecipeDescription description;

    // Data fields
    private final UniqueIngredientList ingredients = new UniqueIngredientList();
    private final UniqueStepList steps = new UniqueStepList();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Recipe constructor for only recipe name and description.
     * Every field must be present and not null.
     */
    public Recipe(RecipeName name, RecipeDescription description) {
        requireAllNonNull(name, description);
        this.name = name;
        this.description = description;
    }

    /**
     * Recipe constructor for optional fields
     * Every field must be present and not null.
     */
    //public Recipe(RecipeName name, RecipeDescription description,
    //  IngredientList ingredients, StepList steps, Set<Tag> tags) {
    //    requireAllNonNull(name, description, ingredients, steps, tags);
    //    this.name = name;
    //    this.description = description;
    //    this.ingredients = ingredients;
    //    this.steps = steps;
    //    this.tags.addAll(tags);
    //}

    public RecipeName getName() {
        return name;
    }

    public RecipeDescription getDescription() {
        return description;
    }

    public UniqueIngredientList getIngredients() {
        return ingredients;
    }

    public UniqueStepList getSteps() {
        return steps;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both recipes of the same name have descriptions that are the same.
     * This defines a weaker notion of equality between two recipes.
     */
    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && otherRecipe.getName().equals(getName())
                && otherRecipe.getDescription().equals(getDescription());
    }

    /**
     * Returns true if both recipes have the same identity and data fields.
     * This defines a stronger notion of equality between two recipes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Recipe)) {
            return false;
        }

        Recipe otherRecipe = (Recipe) other;
        return otherRecipe.getName().equals(getName())
                && otherRecipe.getDescription().equals(getDescription())
                && otherRecipe.getIngredients().equals(getIngredients())
                && otherRecipe.getSteps().equals(getSteps())
                && otherRecipe.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, ingredients, steps, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Description: ")
                .append(getDescription())
                .append(" Ingredients: ")
                .append(getIngredients())
                .append(" Preparation Steps: ")
                .append(getSteps())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
