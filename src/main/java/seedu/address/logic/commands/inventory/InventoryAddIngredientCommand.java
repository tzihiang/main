package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ingredient.Ingredient;

/**
 * Adds an ingredient to the inventory
 */

public class InventoryAddIngredientCommand {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_SUCCESS = "New ingredient added: %1$s";

    private final Ingredient toAdd;

    /**
     * Creates a InventoryAddIngredientCommand to add the specified {@code Ingredient} to the inventory
     */
    public InventoryAddIngredientCommand(Ingredient toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    // TODO: Implement add method

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryAddIngredientCommand // instanceof handles nulls
                && toAdd.equals(((InventoryAddIngredientCommand) other).toAdd));
    }

}
