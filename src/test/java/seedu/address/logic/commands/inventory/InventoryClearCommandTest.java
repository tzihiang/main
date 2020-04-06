package seedu.address.logic.commands.inventory;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.testutil.TypicalIngredients;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.inventory.InventoryClearCommand.MESSAGE_SUCCESS;

public class InventoryClearCommandTest {

    @Test
    public void execute_success() throws CommandException {
        Model model = new ModelManager();
        InventoryClearCommand i = new InventoryClearCommand();

        // empty inventory
        assertEquals(i.execute(model), new CommandResult(MESSAGE_SUCCESS));

        // with ingredients inside the inventory
        model.addInventoryIngredient(TypicalIngredients.ALMOND);
        model.addInventoryIngredient(TypicalIngredients.BANANA);
        model.addInventoryIngredient(TypicalIngredients.CHICKEN);
        assertEquals(i.execute(model), new CommandResult(MESSAGE_SUCCESS));
    }

    @Test
    public void equalsMethod() {
        assertEquals(new InventoryClearCommand(), new InventoryClearCommand());
        assertNotEquals(new InventoryClearCommand(), null);
    }
}
