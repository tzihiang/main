package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;

/**
 * Removes an ingredient to the inventory
 */


public class InventoryRemoveIngredientCommand extends InventoryCommand {

    public static final String COMMAND_WORD = "remove ingredient";
    public static final String MESSAGE_SUCCESS = "Ingredient removed: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": This commands allows you to remove ingredients to your inventory.\n"
            + "Parameters for removing an ingredient into your inventory is as follows: \n"
            + PREFIX_INGREDIENT_NAME + "INGREDIENT "
            + PREFIX_INGREDIENT_QUANTITY + "QUANTITY\n"
            + "Example: " + COMMAND_CATEGORY + " "
            + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Eggs";

    private final Ingredient toRemove;

    /**
     * Creates a InventoryAddIngredientCommand to add the specified {@code Ingredient} to the inventory
     */
    public InventoryRemoveIngredientCommand(Ingredient toRemove) {
        requireNonNull(toRemove);
        this.toRemove = toRemove;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // TODO update result display to show the number of ingredients removed instead of the input
        model.removeInventoryIngredient(toRemove);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryRemoveIngredientCommand // instanceof handles nulls
                && toRemove.equals(((InventoryRemoveIngredientCommand) other).toRemove));
    }
}
