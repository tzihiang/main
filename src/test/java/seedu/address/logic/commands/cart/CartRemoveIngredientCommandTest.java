package seedu.address.logic.commands.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

public class CartRemoveIngredientCommandTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CartRemoveIngredientCommand(null, Optional.empty()));
        assertThrows(NullPointerException.class, () -> new CartRemoveIngredientCommand(ALMOND.getName(), null));
        assertThrows(NullPointerException.class, () -> new CartRemoveIngredientCommand(null, null));
    }

    @Test
    public void execute_validInput() throws CommandException {
        CartRemoveIngredientCommand c = new CartRemoveIngredientCommand(ALMOND.getName(),
                Optional.of(ALMOND.getQuantity()));
        Model model = new ModelManager();
        model.addCartIngredient(ALMOND);

        // removing existing ingredient
        assertEquals(c.execute(model),
                new CommandResult(String.format(CartRemoveIngredientCommand.MESSAGE_SUCCESS, ALMOND)));

        // error thrown after removing ALMOND
        assertThrows(IngredientNotFoundException.class, () -> c.execute(model));

        assertFalse(model.hasCartIngredient(ALMOND));
    }

    @Test
    public void execute_null_throwsNullPointerException() {
        CartRemoveIngredientCommand c = new CartRemoveIngredientCommand(ALMOND.getName(),
                Optional.of(ALMOND.getQuantity()));
        assertThrows(NullPointerException.class, () -> c.execute(null));
    }

    @Test
    public void execute_invalidInput_throwsIngredientNotFoundException() {
        CartRemoveIngredientCommand c = new CartRemoveIngredientCommand(ALMOND.getName(),
                Optional.of(ALMOND.getQuantity()));
        Model model = new ModelManager();

        assertThrows(IngredientNotFoundException.class, () -> c.execute(model));
    }

    @Test
    public void equalsMethod() {
        CartRemoveIngredientCommand c = new CartRemoveIngredientCommand(ALMOND.getName(),
                Optional.of(ALMOND.getQuantity()));
        assertEquals(c, new CartRemoveIngredientCommand(ALMOND.getName(), Optional.of(ALMOND.getQuantity())));
        assertNotEquals(c, new CartRemoveIngredientCommand(BUTTER.getName(), Optional.of(BUTTER.getQuantity())));
        assertNotEquals(c, null);
    }
}
