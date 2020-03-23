package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.UniqueIngredientList;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeDescription;
import seedu.address.model.recipe.RecipeName;
import seedu.address.model.step.Step;
import seedu.address.model.step.UniqueStepList;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Recipe}.
 */
class JsonAdaptedRecipe {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recipe's %s field is missing!";
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "Recipe contains duplicate ingredient(s).";
    public static final String MESSAGE_DUPLICATE_STEP = "Recipe contains duplicate step(s).";

    private final String recipeName;
    private final String recipeDescription;
    private final List<JsonAdaptedIngredient> ingredients = new ArrayList<>();
    private final List<JsonAdaptedStep> steps = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRecipe} with the given recipe details.
     */
    @JsonCreator
    public JsonAdaptedRecipe(@JsonProperty("recipeName") String recipeName,
                             @JsonProperty("recipeDescription") String recipeDescription,
                             @JsonProperty("ingredients") List<JsonAdaptedIngredient> ingredients,
                             @JsonProperty("steps") List<JsonAdaptedStep> steps,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        if (ingredients != null) {
            this.ingredients.addAll(ingredients);
        }
        if (steps != null) {
            this.steps.addAll(steps);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Recipe} into this class for Jackson use.
     */
    public JsonAdaptedRecipe(Recipe source) {
        recipeName = source.getName().fullRecipeName;
        recipeDescription = source.getDescription().fullRecipeDescription;
        ingredients.addAll(source.getIngredients().stream()
                .map(JsonAdaptedIngredient::new)
                .collect(Collectors.toList()));
        steps.addAll(source.getSteps().stream()
                .map(JsonAdaptedStep::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Recipe toModelType() throws IllegalValueException {
        final List<Tag> recipeTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            recipeTags.add(tag.toModelType());
        }

        final UniqueIngredientList recipeIngredients = new UniqueIngredientList();
        for (JsonAdaptedIngredient jsonAdaptedIngredient : ingredients) {
            Ingredient ingredient = jsonAdaptedIngredient.toModelType();
            if (recipeIngredients.contains(ingredient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INGREDIENT);
            }
            recipeIngredients.add(ingredient);
        }

        final UniqueStepList recipeSteps = new UniqueStepList();
        for (JsonAdaptedStep jsonAdaptedStep : steps) {
            Step step = jsonAdaptedStep.toModelType();
            if (recipeSteps.contains(step)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STEP);
            }
            recipeSteps.add(step);
        }

        if (recipeName == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, RecipeName.class.getSimpleName()));
        }
        if (!RecipeName.isValidRecipeName(recipeName)) {
            throw new IllegalValueException(RecipeName.MESSAGE_CONSTRAINTS);
        }

        if (recipeDescription == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, RecipeDescription.class.getSimpleName()));
        }
        if (!RecipeDescription.isValidRecipeDescription(recipeDescription)) {
            throw new IllegalValueException(RecipeDescription.MESSAGE_CONSTRAINTS);
        }

        final RecipeName modelName = new RecipeName(recipeName);
        final RecipeDescription modelDescription = new RecipeDescription(recipeDescription);
        final Set<Tag> modelTags = new HashSet<>(recipeTags);

        return new Recipe(modelName, modelDescription, recipeIngredients, recipeSteps, modelTags);
    }

}
