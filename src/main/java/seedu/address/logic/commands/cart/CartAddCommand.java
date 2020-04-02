package seedu.address.logic.commands.cart;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;

/**
 * Adds ingredients to the cart.
 */
public abstract class CartAddCommand extends CartCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": This commands allows you to add an ingredient to your cart.\n"
            + "Parameters for adding an ingredient into your cart is as follows: \n"
            + PREFIX_INGREDIENT_NAME + "INGREDIENT "
            + PREFIX_INGREDIENT_QUANTITY + "QUANTITY\n"
            + "Example: " + COMMAND_CATEGORY + " "
            + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Eggs "
            + PREFIX_INGREDIENT_QUANTITY + "10\n\n"
            + COMMAND_CATEGORY + " " + COMMAND_WORD + "recipe INDEX"
            + ": This commands allows you to add all the ingredients from a recipe to your cart.\n"
            + "Parameters for adding all ingredients in the recipe into your cart is as follows: \n"
            + COMMAND_CATEGORY + " " + COMMAND_WORD + "recipe INDEX\n"
            + "Example: " + COMMAND_CATEGORY + " " + COMMAND_WORD + " recipe 1\n";

}
