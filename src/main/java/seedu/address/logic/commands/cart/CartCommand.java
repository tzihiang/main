package seedu.address.logic.commands.inventory;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.model.Inventory;
import seedu.address.model.ReadOnlyIngredientList;
import seedu.address.model.ingredient.UniqueIngredientList;

import java.util.Optional;

/**
 * Represents an inventory command with hidden internal logic and the ability to be executed.
 */
public abstract class InventoryCommand extends Command {

    public static final String COMMAND_CATEGORY = "inventory";

    /**
     * Stores the details to edit the inventory with.
     */
    public static class EditInventoryDescriptor {
        private UniqueIngredientList ingredients;

        public EditInventoryDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInventoryDescriptor(EditInventoryDescriptor toCopy) {
            setIngredients(toCopy.ingredients);
        }

        /**
         * Creates and returns a {@code Inventory} with the details of {@code inventoryToEdit}
         * edited with {@code editInventoryDescriptor}.
         */
        public static Inventory createEditedInventory(Inventory inventoryToEdit, EditInventoryDescriptor editInventoryDescriptor) {
            assert inventoryToEdit != null;

            UniqueIngredientList updatedIngredients = editInventoryDescriptor.getIngredients()
                    .orElse(inventoryToEdit.getUniqueIngredientList());

            return new Inventory((ReadOnlyIngredientList) updatedIngredients);
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
            if (!(other instanceof InventoryCommand.EditInventoryDescriptor)) {
                return false;
            }

            // state check
            EditInventoryDescriptor e = (EditInventoryDescriptor) other;

            return getIngredients().equals(e.getIngredients());
        }
    }

}
