package seedu.address.logic.commands.cart;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * This command moves all ingredients from the cart to inventory
 */
public class CartMoveCommand extends CartCommand {

    public static final String COMMAND_WORD = "move";
    public static final String MESSAGE_SUCCESS = "All ingredients from cart successfully added to your inventory!";
    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + "This command allows you to add all your ingredients from the cart to your inventory.\n"
            + "This also empties the cart in the process.\n"
            + "Parameters for moving all ingredients from your cart is as follows: \n"
            + COMMAND_CATEGORY + " " + COMMAND_WORD;

    /**
     * Creates a CartMoveCommand to move the ingredients in the cart to the inventory
     */
    public CartMoveCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.cartMoveIngredients();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof CartMoveCommand;
    }
}
