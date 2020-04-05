package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientQuantity;

/**
 * Removes an ingredient to the inventory
 */


public class InventoryRemoveIngredientCommand extends InventoryCommand {

    public static final String COMMAND_WORD = "remove ingredient";
    public static final String MESSAGE_SUCCESS = "Ingredient removed from inventory: %1$s of %2$s";
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
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Ingredient originalIngredient = null;
        if (model.getInventory().getCompatibleIngredientList().contains(toRemove)) {
            originalIngredient = model.findInventoryIngredient(toRemove);
        }

        model.removeInventoryIngredient(toRemove);

        assert originalIngredient != null;
        IngredientQuantity originalQuantity = originalIngredient.getQuantity();
        String quantityRemoved;

        if (model.getInventory().getCompatibleIngredientList().contains(toRemove)) {
            IngredientQuantity finalQuantity;
            finalQuantity = model.findInventoryIngredient(toRemove).getQuantity();
            quantityRemoved = originalQuantity.subtract(finalQuantity).toString();
        } else {
            quantityRemoved = originalQuantity.toString();
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, quantityRemoved, toRemove.getName().ingredientName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryRemoveIngredientCommand // instanceof handles nulls
                && toRemove.equals(((InventoryRemoveIngredientCommand) other).toRemove));
    }
}
