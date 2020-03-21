package seedu.address.logic.commands.cart;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;

public abstract class CartRemoveCommand extends CartCommand {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + "This commands allows you to remove ingredients from your cart.\n"
            + "Parameters for adding an ingredient into your cart is as follows: \n"
            + PREFIX_INGREDIENT_NAME + "INGREDIENT "
            + "Example: " + COMMAND_CATEGORY + " "
            + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Eggs";
}
