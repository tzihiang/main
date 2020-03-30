package seedu.address.logic.commands.cookbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.cookbook.CookbookRemoveCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.testutil.TypicalRecipes;

public class CookbookRemoveCommandTest {

    private static final Index VALID_RECIPE_INDEX = new Index(0);
    private static final Index OUT_OF_BOUNDS_RECIPE_INDEX = new Index(1);

    @Test
    public void constructor_validInput() {
        CookbookRemoveCommand c = new CookbookRemoveCommand(VALID_RECIPE_INDEX);
        assertEquals(c, new CookbookRemoveCommand(VALID_RECIPE_INDEX));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CookbookRemoveCommand(null));
    }

    @Test
    public void execute_validInput() throws CommandException {
        CookbookRemoveCommand c = new CookbookRemoveCommand(VALID_RECIPE_INDEX);
        Model model = new ModelManager();
        model.addCookbookRecipe(TypicalRecipes.AGLIO_OLIO);
        assertEquals(c.execute(model), new CommandResult(String.format(MESSAGE_SUCCESS, TypicalRecipes.AGLIO_OLIO)));

        // after adding multiple recipes
        model.addCookbookRecipe(TypicalRecipes.CARBONARA);
        model.addCookbookRecipe(TypicalRecipes.SPAGHETTI_BOLOGNESE);
        assertEquals(c.execute(model), new CommandResult(String.format(MESSAGE_SUCCESS, TypicalRecipes.AGLIO_OLIO)));
    }

    @Test
    public void execute_invalidInput() {
        Model model = new ModelManager();

        // index greater than size of UniqueRecipeList in Cookbook
        CookbookRemoveCommand c = new CookbookRemoveCommand(OUT_OF_BOUNDS_RECIPE_INDEX);
        assertThrows(CommandException.class, () -> c.execute(model));
    }
}
