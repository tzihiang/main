package seedu.address.model.recipe;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.UniqueIngredientList;
import seedu.address.model.step.UniqueStepList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagComparator;

/**
 * Represents a Recipe in the cookbook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Recipe {

    // Identity fields
    private final RecipeName name;

    // Data fields
    private final RecipeDescription description;
    private final UniqueIngredientList ingredients;
    private final UniqueStepList steps;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Recipe constructor for only recipe name and description.
     * Every field must be present and not null.
     */
    public Recipe(RecipeName name, RecipeDescription description) {
        requireAllNonNull(name, description);
        this.name = name;
        this.description = description;
        this.ingredients = new UniqueIngredientList();
        this.steps = new UniqueStepList();
    }

    /**
     * Recipe constructor for optional fields
     * Every field must be present and not null.
     */
    public Recipe(RecipeName name, RecipeDescription description,
            UniqueIngredientList ingredients, UniqueStepList steps, Set<Tag> tags) {
        requireAllNonNull(name, description, ingredients, steps, tags);
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
        this.tags.addAll(tags);
    }

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

    public Set<Tag> getTags() {
        return tags;
    }

    public String getTagsString() {
        StringBuilder sb = new StringBuilder();
        getTags().forEach(tag -> sb.append(" ").append(tag.toString()));
        return sb.toString().trim().replace("[", "").replace("]", " ");
    }

    public String getIngredientNamesString() {
        StringBuilder sb = new StringBuilder();

        for (Ingredient i : getIngredients()) {
            sb.append(i.getName().ingredientName).append(" ");
        }

        return sb.toString();
    }

    /**
     * Returns true if both recipes have the same name.
     * This defines a weaker notion of equality between two recipes.
     */
    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && otherRecipe.getName().equals(getName());
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
                .append("\nDescription: ")
                .append(getDescription())
                .append("\nIngredients: ")
                .append(getIngredients())
                .append("\nPreparation Steps:")
                .append(getSteps())
                .append("\nTags:");
        List<Tag> tags = new ArrayList<>(getTags());
        tags.sort(new TagComparator());
        tags.forEach(tag -> builder.append(" ").append(tag.toString()));
        return builder.toString();
    }

}
