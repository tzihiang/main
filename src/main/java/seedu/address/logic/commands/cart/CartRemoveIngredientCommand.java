package seedu.address.logic.commands.cart;

import seedu.address.model.ingredient.Ingredient;

import static java.util.Objects.requireNonNull;

public class InventoryRemoveIngredientCommand {

    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_SUCCESS = "Ingredient removed: %1$s";

    private final Ingredient toRemove;

    /**
     * Creates a InventoryAddIngredientCommand to add the specified {@code Ingredient} to the inventory
     */
    public InventoryRemoveIngredientCommand(Ingredient toRemove) {
        requireNonNull(toRemove);
        this.toRemove = toRemove;
    }

    // TODO: Implement Remove method

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryRemoveIngredientCommand // instanceof handles nulls
                && toRemove.equals(((InventoryRemoveIngredientCommand) other).toRemove));
    }

}
