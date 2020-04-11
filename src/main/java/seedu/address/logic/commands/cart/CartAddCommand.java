package seedu.address.logic.commands.cart;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;

/**
 * Adds ingredients to the cart.
 */
public abstract class CartAddCommand extends CartCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": adds an ingredient to your cart.\n"
            + "Parameters: \n"
            + PREFIX_INGREDIENT_NAME + "INGREDIENT "
            + PREFIX_INGREDIENT_QUANTITY + "QUANTITY\n"
            + "Example: " + COMMAND_CATEGORY + " "
            + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Eggs "
            + PREFIX_INGREDIENT_QUANTITY + "10\n\n"
            + COMMAND_CATEGORY + " " + COMMAND_WORD + "recipe INDEX"
            + ": add all the ingredients from a recipe to your cart.\n"
            + "Parameters: \n"
            + COMMAND_CATEGORY + " " + COMMAND_WORD + "recipe INDEX\n"
            + "Example: " + COMMAND_CATEGORY + " " + COMMAND_WORD + " recipe 1\n";

}
