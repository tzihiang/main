package seedu.address.logic.commands.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.recipe.RecipeRemoveIngredientCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalIngredients.ALMOND;
import static seedu.address.testutil.TypicalIngredients.BUTTER;
import static seedu.address.testutil.TypicalRecipes.CARBONARA;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;
import seedu.address.model.ingredient.exceptions.IngredientNotFoundException;

public class RecipeRemoveIngredientCommandTest {
    private static final Index VALID_RECIPE_INDEX = new Index(0);
    private static final Index OUT_OF_BOUNDS_RECIPE_INDEX = new Index(1);
    private static final IngredientName VALID_INGREDIENT_NAME = ALMOND.getName();
    private static final Optional<IngredientQuantity> VALID_INGREDIENT_QUANTITY = Optional.of(ALMOND.getQuantity());

    @Test
    public void constructor_validInput() {
        RecipeRemoveIngredientCommand c = new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX,
                VALID_INGREDIENT_NAME, VALID_INGREDIENT_QUANTITY);
        assertEquals(c, new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX,
                VALID_INGREDIENT_NAME, VALID_INGREDIENT_QUANTITY));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX,
                null, null)); // index, null, null
        assertThrows(NullPointerException.class, () -> new RecipeRemoveIngredientCommand(null,
                null, VALID_INGREDIENT_QUANTITY)); // null, null, quantity
        assertThrows(NullPointerException.class, () -> new RecipeRemoveIngredientCommand(null,
                VALID_INGREDIENT_NAME, null)); // null, name, null
        assertThrows(NullPointerException.class, () -> new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX,
                VALID_INGREDIENT_NAME, null)); // index, name, null
        assertThrows(NullPointerException.class, () -> new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX,
                null, VALID_INGREDIENT_QUANTITY)); // index, null, quantity
        assertThrows(NullPointerException.class, () -> new RecipeRemoveIngredientCommand(null,
                VALID_INGREDIENT_NAME, VALID_INGREDIENT_QUANTITY)); // null, name, quantity
        assertThrows(NullPointerException.class, () -> new RecipeRemoveIngredientCommand(null, null, null)); // all null
    }

    @Test
    public void execute_validInput() throws CommandException {
        RecipeRemoveIngredientCommand c = new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX,
                VALID_INGREDIENT_NAME, VALID_INGREDIENT_QUANTITY);
        Model model = new ModelManager();
        model.addCookbookRecipe(CARBONARA);

        // add ingredient to CARBONARA
        new RecipeAddIngredientCommand(VALID_RECIPE_INDEX, ALMOND).execute(model);
        assertEquals(c.execute(model),
            new CommandResult(String.format(MESSAGE_SUCCESS, CARBONARA.getName().fullRecipeName, ALMOND)));
    }

    @Test
    public void execute_null_throwsNullPointerException() {
        RecipeAddIngredientCommand c = new RecipeAddIngredientCommand(VALID_RECIPE_INDEX, ALMOND);
        assertThrows(NullPointerException.class, () -> c.execute(null));
    }

    @Test
    public void execute_invalidInput() throws CommandException {
        RecipeRemoveIngredientCommand c = new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX,
                VALID_INGREDIENT_NAME, VALID_INGREDIENT_QUANTITY);
        Model model = new ModelManager();

        // removing from a non-existent recipe
        assertThrows(CommandException.class, () -> c.execute(model));

        model.addCookbookRecipe(CARBONARA);

        // recipe index out of bounds
        RecipeRemoveIngredientCommand d = new RecipeRemoveIngredientCommand(OUT_OF_BOUNDS_RECIPE_INDEX,
                VALID_INGREDIENT_NAME, VALID_INGREDIENT_QUANTITY);
        assertThrows(CommandException.class, () -> d.execute(model));

        // add ingredient to CARBONARA
        new RecipeAddIngredientCommand(VALID_RECIPE_INDEX, BUTTER).execute(model);

        // ingredient not found
        RecipeRemoveIngredientCommand e = new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX,
                BUTTER.getName(), Optional.of(BUTTER.getQuantity()));
        e.execute(model);
        assertThrows(IngredientNotFoundException.class, () -> e.execute(model));
    }

    @Test
    public void equalsMethod() {
        RecipeRemoveIngredientCommand c = new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX,
                VALID_INGREDIENT_NAME, VALID_INGREDIENT_QUANTITY);
        assertEquals(c, new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX,
                VALID_INGREDIENT_NAME, VALID_INGREDIENT_QUANTITY));
        assertNotEquals(c, new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX,
                BUTTER.getName(), Optional.of(BUTTER.getQuantity())));
        assertNotEquals(c, null);
    }
}

