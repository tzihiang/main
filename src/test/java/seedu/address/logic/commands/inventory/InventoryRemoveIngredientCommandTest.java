package seedu.address.logic.commands.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalIngredients.ALMOND;
import static seedu.address.testutil.TypicalIngredients.BUTTER;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ingredient.exceptions.IngredientNotFoundException;

public class InventoryRemoveIngredientCommandTest {
    @Test
    public void constructor_validInput() {
        InventoryRemoveIngredientCommand c = new InventoryRemoveIngredientCommand(ALMOND.getName(),
                Optional.of(ALMOND.getQuantity()));
        assertEquals(c, new InventoryRemoveIngredientCommand(ALMOND.getName(), Optional.of(ALMOND.getQuantity())));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InventoryRemoveIngredientCommand(null, Optional.empty()));
        assertThrows(NullPointerException.class, () -> new InventoryRemoveIngredientCommand(ALMOND.getName(), null));
        assertThrows(NullPointerException.class, () -> new InventoryRemoveIngredientCommand(null, null));
    }

    @Test
    public void execute_validInput() throws CommandException {
        InventoryRemoveIngredientCommand c = new InventoryRemoveIngredientCommand(ALMOND.getName(),
                Optional.of(ALMOND.getQuantity()));
        Model model = new ModelManager();
        model.addInventoryIngredient(ALMOND);

        // removing existing ingredient
        assertEquals(c.execute(model),
                new CommandResult(String.format(InventoryRemoveIngredientCommand.MESSAGE_SUCCESS, ALMOND)));

        // error thrown after removing ALMOND
        assertThrows(IngredientNotFoundException.class, () -> c.execute(model));
    }

    @Test
    public void execute_null_throwsNullPointerException() {
        InventoryRemoveIngredientCommand c = new InventoryRemoveIngredientCommand(ALMOND.getName(),
                Optional.of(ALMOND.getQuantity()));
        assertThrows(NullPointerException.class, () -> c.execute(null));
    }

    @Test
    public void execute_invalidInput_throwsIngredientNotFoundException() {
        InventoryRemoveIngredientCommand c = new InventoryRemoveIngredientCommand(ALMOND.getName(),
                Optional.of(ALMOND.getQuantity()));
        Model model = new ModelManager();

        assertThrows(IngredientNotFoundException.class, () -> c.execute(model));
    }

    @Test
    public void equalsMethod() {
        InventoryRemoveIngredientCommand c = new InventoryRemoveIngredientCommand(ALMOND.getName(),
                Optional.of(ALMOND.getQuantity()));
        assertEquals(c, new InventoryRemoveIngredientCommand(ALMOND.getName(), Optional.of(ALMOND.getQuantity())));
        assertNotEquals(c, new InventoryRemoveIngredientCommand(BUTTER.getName(), Optional.of(BUTTER.getQuantity())));
        assertNotEquals(c, null);
    }
}
