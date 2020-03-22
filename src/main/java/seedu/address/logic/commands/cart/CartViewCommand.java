package seedu.address.logic.commands.cart;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Creates a new CartViewCommand and views all the ingredients currently in cart.
 */
public class CartViewCommand extends CartCommand {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": This command allows you to view your entire cart. \n"
            + "Simply just enter the command as follows: \n"
            + COMMAND_CATEGORY + " " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Cart view as shown";

    /**
     * Creates a CartViewCommand to view the entire cart
     */
    /*
    public CartViewCommand() {
        //TODO: Implement this method.
    }
    */

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // TODO: Implement this model
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
