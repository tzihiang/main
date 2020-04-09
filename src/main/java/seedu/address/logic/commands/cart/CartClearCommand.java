package seedu.address.logic.commands.cart;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Cart;
import seedu.address.model.Model;

/**
 * Removes all ingredients from the cart
 */
public class CartClearCommand extends CartCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All ingredients from cart cleared!";
    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + "This command allows you to remove all ingredients from your cart.\n"
            + "Parameters for removing all ingredients from your cart is as follows: \n"
            + COMMAND_CATEGORY + " " + COMMAND_WORD;

    /**
     * Creates a CartClearCommand to clear the cart
     */
    public CartClearCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.setCart(new Cart());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || other instanceof CartClearCommand;
    }
}
