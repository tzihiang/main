package seedu.address.logic.commands.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalIngredients.ALMOND;
import static seedu.address.testutil.TypicalIngredients.BUTTER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ingredient.exceptions.IngredientNotFoundException;

public class InventoryRemoveIngredientCommandTest {
    @Test
    public void constructor_validInput() {
        InventoryRemoveIngredientCommand c = new InventoryRemoveIngredientCommand(ALMOND);
        assertEquals(c, new InventoryRemoveIngredientCommand(ALMOND));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InventoryRemoveIngredientCommand(null));
    }

    @Test
    public void execute_validInput() throws CommandException {
        InventoryRemoveIngredientCommand c = new InventoryRemoveIngredientCommand(ALMOND);
        Model model = new ModelManager();
        model.addInventoryIngredient(ALMOND);

        // removing existing ingredient
        assertEquals(c.execute(model), new CommandResult(String.format(InventoryRemoveIngredientCommand.MESSAGE_SUCCESS,
                ALMOND.getQuantity().toString(), ALMOND.getName().ingredientName)));

        // error thrown after removing ALMOND
        assertThrows(IngredientNotFoundException.class, () -> c.execute(model));
    }

    @Test
    public void execute_null_throwsNullPointerException() {
        InventoryRemoveIngredientCommand c = new InventoryRemoveIngredientCommand(ALMOND);
        assertThrows(NullPointerException.class, () -> c.execute(null));
    }

    @Test
    public void execute_invalidInput_throwsIngredientNotFoundException() {
        InventoryRemoveIngredientCommand c = new InventoryRemoveIngredientCommand(ALMOND);
        Model model = new ModelManager();

        assertThrows(IngredientNotFoundException.class, () -> c.execute(model));
    }

    @Test
    public void equalsMethod() {
        InventoryRemoveIngredientCommand c = new InventoryRemoveIngredientCommand(ALMOND);
        assertEquals(c, new InventoryRemoveIngredientCommand(ALMOND));
        assertNotEquals(c, new InventoryRemoveIngredientCommand(BUTTER));
        assertNotEquals(c, null);
    }
}
