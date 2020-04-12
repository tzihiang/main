package seedu.address.logic.commands.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.recipe.RecipeRemoveIngredientCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalIngredients.ALMOND;
import static seedu.address.testutil.TypicalIngredients.BUTTER;
import static seedu.address.testutil.TypicalRecipes.CARBONARA;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ingredient.exceptions.IngredientNotFoundException;

//@@author teo-jun-xiong
public class RecipeRemoveIngredientCommandTest {
    private static final Index VALID_RECIPE_INDEX = new Index(0);
    private static final Index OUT_OF_BOUNDS_RECIPE_INDEX = new Index(1);

    @Test
    public void constructor_validInput() {
        RecipeRemoveIngredientCommand c = new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX, ALMOND);
        assertEquals(c, new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX, ALMOND));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX, null));
        assertThrows(NullPointerException.class, () -> new RecipeRemoveIngredientCommand(null, ALMOND));
        assertThrows(NullPointerException.class, () -> new RecipeRemoveIngredientCommand(null, null));
    }

    @Test
    public void execute_validInput() throws CommandException {
        RecipeRemoveIngredientCommand c = new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX, ALMOND);
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
        RecipeRemoveIngredientCommand c = new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX, ALMOND);
        Model model = new ModelManager();

        // removing from a non-existent recipe
        assertThrows(CommandException.class, () -> c.execute(model));

        model.addCookbookRecipe(CARBONARA);

        // recipe index out of bounds
        RecipeRemoveIngredientCommand d = new RecipeRemoveIngredientCommand(OUT_OF_BOUNDS_RECIPE_INDEX, ALMOND);
        assertThrows(CommandException.class, () -> d.execute(model));

        // add ingredient to CARBONARA
        new RecipeAddIngredientCommand(VALID_RECIPE_INDEX, BUTTER).execute(model);

        // ingredient not found
        RecipeRemoveIngredientCommand e = new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX, BUTTER);
        e.execute(model);
        assertThrows(IngredientNotFoundException.class, () -> e.execute(model));
    }

    @Test
    public void equalsMethod() {
        RecipeRemoveIngredientCommand c = new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX, ALMOND);
        assertEquals(c, new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX, ALMOND));
        assertNotEquals(c, new RecipeRemoveIngredientCommand(VALID_RECIPE_INDEX, BUTTER));
        assertNotEquals(c, null);
    }
}

