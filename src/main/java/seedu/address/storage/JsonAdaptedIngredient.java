package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;

/**
 * Jackson-friendly version of {@link Ingredient}.
 */
class JsonAdaptedIngredient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Ingredient's %s field is missing!";

    private final String ingredientName;
    private final String ingredientQuantity;

    /**
     * Constructs a {@code JsonAdaptedIngredient} with the given {@code ingredientName} and {@code ingredientQuantity}.
     */
    @JsonCreator
    public JsonAdaptedIngredient(@JsonProperty("name") String ingredientName,
            @JsonProperty("quantity") String ingredientQuantity) {
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
    }

    /**
     * Converts a given {@code Ingredient} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(Ingredient source) {
        ingredientName = source.getName().toString();
        ingredientQuantity = source.getQuantity().toString();
    }

    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code Ingredient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ingredient.
     */
    public Ingredient toModelType() throws IllegalValueException {
        if (ingredientName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    IngredientName.class.getSimpleName()));
        } else if (ingredientQuantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    IngredientQuantity.class.getSimpleName()));
        } else if (!IngredientName.isValidIngredientName(ingredientName)) {
            throw new IllegalValueException(IngredientName.MESSAGE_CONSTRAINTS);
        } else if (!IngredientQuantity.isValidIngredientQuantity(ingredientQuantity)) {
            throw new IllegalValueException(IngredientQuantity.MESSAGE_CONSTRAINTS);
        }

        final IngredientName modelIngredientName = new IngredientName(ingredientName);
        final IngredientQuantity modelIngredientQuantity = new IngredientQuantity(ingredientQuantity);
        return new Ingredient(modelIngredientName, modelIngredientQuantity);
    }

}
