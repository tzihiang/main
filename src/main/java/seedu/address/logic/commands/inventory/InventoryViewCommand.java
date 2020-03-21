package seedu.address.logic.commands.inventory;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class InventoryViewCommand extends InventoryCommand {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": This command allows you to view your entire inventory. \n"
            + "Simply just enter the command as follows: \n"
            + COMMAND_CATEGORY + " " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Inventory view as shown";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // TODO: Implement this model
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
