package seedu.address.logic.commands.cart;

import seedu.address.model.ingredient.Ingredient;

import static java.util.Objects.requireNonNull;

public class CartRemoveIngredientCommand {

    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_SUCCESS = "Ingredient removed: %1$s";

    private final Ingredient toRemove;

    /**
     * Creates a CartRemoveIngredientCommand to add the specified {@code Ingredient} to the cart
     */
    public CartRemoveIngredientCommand(Ingredient toRemove) {
        requireNonNull(toRemove);
        this.toRemove = toRemove;
    }

    // TODO: Implement Remove method

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CartRemoveIngredientCommand // instanceof handles nulls
                && toRemove.equals(((CartRemoveIngredientCommand) other).toRemove));
    }

}
