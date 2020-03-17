package seedu.address.logic.commands.cart;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.model.Cart;
import seedu.address.model.ReadOnlyIngredientList;
import seedu.address.model.ingredient.UniqueIngredientList;

import java.util.Optional;

/**
 * Represents an Cart command with hidden internal logic and the ability to be executed.
 */
public abstract class CartCommand extends Command {

    public static final String COMMAND_CATEGORY = "cart";

    /**
     * Stores the details to edit the cart with.
     */
    public static class EditCartDescriptor {
        private UniqueIngredientList ingredients;

        public EditCartDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCartDescriptor(EditCartDescriptor toCopy) {
            setIngredients(toCopy.ingredients);
        }

        /**
         * Creates and returns a {@code Cart} with the details of {@code CartToEdit}
         * edited with {@code editCartDescriptor}.
         */
        public static Cart createEditedCart(Cart cartToEdit, EditCartDescriptor editCartDescriptor) {
            assert cartToEdit != null;

            UniqueIngredientList updatedIngredients = editCartDescriptor.getIngredients()
                    .orElse(cartToEdit.getUniqueIngredientList());

            return new Cart((ReadOnlyIngredientList) updatedIngredients);
        }
        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(ingredients);
        }

        public void setIngredients(UniqueIngredientList ingredients) {
            this.ingredients = ingredients;
        }

        public Optional<UniqueIngredientList> getIngredients() {
            return Optional.ofNullable(ingredients);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof CartCommand.EditCartDescriptor)) {
                return false;
            }

            // state check
            EditCartDescriptor e = (EditCartDescriptor) other;

            return getIngredients().equals(e.getIngredients());
        }
    }

}
