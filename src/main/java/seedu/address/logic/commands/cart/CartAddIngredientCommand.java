package seedu.address.logic.commands.cart;

import seedu.address.model.ingredient.Ingredient;

import static java.util.Objects.requireNonNull;

/**
 * Adds an ingredient to the cart
 */

public class CartAddIngredientCommand {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_SUCCESS = "New ingredient added: %1$s";

    private final Ingredient toAdd;

    /**
     * Creates a InventoryAddIngredientCommand to add the specified {@code Ingredient} to the inventory
     */
    public CartAddIngredientCommand(Ingredient toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    // TODO: Implement add method

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CartAddIngredientCommand // instanceof handles nulls
                && toAdd.equals(((CartAddIngredientCommand) other).toAdd));
    }

}
