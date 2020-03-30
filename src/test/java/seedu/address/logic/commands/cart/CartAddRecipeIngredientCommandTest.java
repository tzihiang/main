package seedu.address.logic.commands.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.cart.CartAddRecipeIngredientCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.testutil.TypicalRecipes;

public class CartAddRecipeIngredientCommandTest {

    private static final int VALID_RECIPE_INDEX = 1;
    private static final int ZERO_RECIPE_INDEX = 0;

    @Test
    public void constructor_validInput() {
        CartAddRecipeIngredientCommand c = new CartAddRecipeIngredientCommand(VALID_RECIPE_INDEX);
        assertEquals(c, new CartAddRecipeIngredientCommand(VALID_RECIPE_INDEX));
    }

    @Test
    public void execute_validInput() throws CommandException {
        CartAddRecipeIngredientCommand c = new CartAddRecipeIngredientCommand(VALID_RECIPE_INDEX);
        Model model = new ModelManager();
        model.addCookbookRecipe(TypicalRecipes.CARBONARA);
        assertEquals(c.execute(model), new CommandResult(String.format(MESSAGE_SUCCESS, VALID_RECIPE_INDEX)));

        // after adding multiple recipes
        model.addCookbookRecipe(TypicalRecipes.AGLIO_OLIO);
        model.addCookbookRecipe(TypicalRecipes.SCRAMBLED_EGG);
        model.addCookbookRecipe(TypicalRecipes.SPAGHETTI_BOLOGNESE);
        assertEquals(c.execute(model), new CommandResult(String.format(MESSAGE_SUCCESS, VALID_RECIPE_INDEX)));

    }

    @Test
    public void execute_invalidInput() {
        Model model = new ModelManager();

        // invalid index
        CartAddRecipeIngredientCommand c = new CartAddRecipeIngredientCommand(ZERO_RECIPE_INDEX);
        assertThrows(CommandException.class, () -> c.execute(model));

        // index greater than size of UniqueRecipeList in Cookbook
        CartAddRecipeIngredientCommand d = new CartAddRecipeIngredientCommand(VALID_RECIPE_INDEX);
        assertThrows(CommandException.class, () -> d.execute(model));
    }
}
