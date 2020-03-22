package seedu.address.logic.parser.inventory;

import seedu.address.logic.commands.inventory.InventoryCommand;
import seedu.address.logic.commands.inventory.InventoryViewCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Returns a new InventoryViewCommand object.
 */
public class InventoryViewCommandParser implements Parser<InventoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CartCommand
     * and returns a CartAddIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InventoryViewCommand parse(String args) throws ParseException {
        throw new ParseException("Not implemented yet");
    }
}
