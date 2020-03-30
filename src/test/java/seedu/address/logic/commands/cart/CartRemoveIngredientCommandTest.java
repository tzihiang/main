package seedu.address.logic.commands.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.cart.CartRemoveIngredientCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIngredients.ALMOND;
import static seedu.address.testutil.TypicalIngredients.BUTTER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ingredient.exceptions.IngredientNotFoundException;

public class CartRemoveIngredientCommandTest {

    @Test
    public void constructor_validInput() {
        CartRemoveIngredientCommand c = new CartRemoveIngredientCommand(ALMOND);
        assertEquals(c, new CartRemoveIngredientCommand(ALMOND));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CartRemoveIngredientCommand(null));
    }

    @Test
    public void execute_validInput() throws CommandException {
        CartRemoveIngredientCommand c = new CartRemoveIngredientCommand(ALMOND);
        Model model = new ModelManager();
        model.addCartIngredient(ALMOND);

        // removing existing ingredient
        assertEquals(c.execute(model), new CommandResult(String.format(MESSAGE_SUCCESS, ALMOND)));
    }

    @Test
    public void execute_invalidInput_throwsIngredientNotFoundException() {
        CartRemoveIngredientCommand c = new CartRemoveIngredientCommand(ALMOND);
        Model model = new ModelManager();

        assertThrows(IngredientNotFoundException.class, () -> c.execute(model));
    }

    @Test
    public void execute_null_throwsNullPointerException() {
        CartRemoveIngredientCommand c = new CartRemoveIngredientCommand(ALMOND);
        assertThrows(NullPointerException.class, () -> c.execute(null));
    }

    @Test
    public void equalsMethod() {
        CartRemoveIngredientCommand c = new CartRemoveIngredientCommand(ALMOND);
        assertEquals(c, new CartRemoveIngredientCommand(ALMOND));
        assertNotEquals(c, new CartRemoveIngredientCommand(BUTTER));
        assertNotEquals(c, null);
    }
}
