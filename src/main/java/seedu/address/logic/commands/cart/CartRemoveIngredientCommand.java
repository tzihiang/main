package seedu.address.logic.commands.cart;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientQuantity;

/**
 * Removes an ingredient from the cart
 */
public class CartRemoveIngredientCommand extends CartCommand {

    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_SUCCESS = "Ingredient removed: %1$s of %2$s";
    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + "This commands allows you to remove ingredients from your cart.\n"
            + "Parameters for removing an ingredient into your cart is as follows: \n"
            + PREFIX_INGREDIENT_NAME + "INGREDIENT "
            + "Example: " + COMMAND_CATEGORY + " "
            + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Egg " + PREFIX_INGREDIENT_QUANTITY + "10";

    private final Ingredient toRemove;

    /**
     * Creates a CartRemoveIngredientCommand to add the specified {@code Ingredient} to the cart
     */
    public CartRemoveIngredientCommand(Ingredient toRemove) {
        requireNonNull(toRemove);
        this.toRemove = toRemove;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Ingredient originalIngredient = null;
        if (model.getCart().getCompatibleIngredientList().contains(toRemove)) {
            originalIngredient = model.findCartIngredient(toRemove);
        }

        model.removeCartIngredient(toRemove);

        assert originalIngredient != null;
        IngredientQuantity originalQuantity = originalIngredient.getQuantity();
        String quantityRemoved;

        if (model.getCart().getCompatibleIngredientList().contains(toRemove)) {
            IngredientQuantity finalQuantity;
            finalQuantity = model.findCartIngredient(toRemove).getQuantity();
            quantityRemoved = originalQuantity.subtract(finalQuantity).toString();
        } else {
            quantityRemoved = originalQuantity.toString();
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, quantityRemoved, toRemove.getName().ingredientName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CartRemoveIngredientCommand // instanceof handles nulls
                && toRemove.equals(((CartRemoveIngredientCommand) other).toRemove));
    }
}
