package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Recipe;

/**
 * Removes all the ingredients of the selected recipe from the inventory
 */

public class InventoryCookCommand extends InventoryCommand {

    public static final String COMMAND_WORD = "cook";
    public static final String RECIPE_KEYWORD = "recipe";
    public static final String MESSAGE_SUCCESS = "All ingredients from recipe %1$s removed from inventory";
    public static final String MESSAGE_MISSING_INGREDIENT =
            "Your inventory does not contain all the ingredients of recipe %1$s.";
    public static final String MESSAGE_INSUFFICIENT_QUANTITY =
            "Your inventory contains insufficient quantities of the ingredients in recipe %1$s.";
    public static final String MESSAGE_NO_INGREDIENT_IN_RECIPE =
            "Recipe %1$s does not require any ingredients to cook.";

    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": This commands allows you to remove all ingredients of a recipe from your inventory.\n"
            + "Parameters for removing an ingredient into your inventory is as follows: \n"
            + "INDEX\n"
            + "Example:\n"
            + COMMAND_CATEGORY + " " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Creates a InventoryCookRecipeCommand to remove all the {@code Ingredient} of a recipe from the inventory
     */
    public InventoryCookCommand(Index targetIndex) {
        requireAllNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    /**
     * Returns true if all the ingredients in recipe exist in the inventory
     */
    private boolean hasInventoryIngredients(ReadOnlyInventory inventory, Recipe recipe) {
        requireNonNull(inventory);
        requireNonNull(recipe);
        ObservableList<Ingredient> inventoryList = inventory.getIngredientList();
        ObservableList<Ingredient> recipeIngredients = recipe.getIngredients().asUnmodifiableObservableList();

        return recipeIngredients.stream().map(recipeIngredient -> inventoryList
                .stream().map(inventoryIngredient -> inventoryIngredient.isCompatibleWith(recipeIngredient))
                .reduce(false, (x, y) -> x || y, (x , y) -> x || y))
                .allMatch(isCompatible -> isCompatible.equals(true));
    }

    /**
     * Returns true if all ingredients's quantity in recipe is lesser than the the same ingredient's quantity
     * in inventory
     * The ingredients in other list must exist in this list.
     */
    private boolean hasSufficientInventoryIngredients(ReadOnlyInventory inventory, Recipe recipe) {
        requireNonNull(inventory);
        requireNonNull(recipe);
        ObservableList<Ingredient> inventoryList = inventory.getIngredientList();
        ObservableList<Ingredient> recipeIngredients = recipe.getIngredients().asUnmodifiableObservableList();

        return recipeIngredients.stream().map(recipeIngredient -> inventoryList.stream()
                .filter(inventoryIngredient -> inventoryIngredient.isCompatibleWith(recipeIngredient))
                .findFirst().get().sufficientQuantity(recipeIngredient))
                .allMatch(isSufficient -> isSufficient.equals(true));
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Recipe> recipeList = model.getFilteredCookbookRecipeList();

        if (targetIndex.getZeroBased() >= recipeList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX,
                    InventoryCookCommand.MESSAGE_USAGE));
        }

        Recipe selectedRecipe = recipeList.get(targetIndex.getZeroBased());

        if (selectedRecipe.getIngredients().asUnmodifiableObservableList().size() == 0) {
            throw new CommandException(String.format(MESSAGE_NO_INGREDIENT_IN_RECIPE,
                    selectedRecipe.getName().toString()));
        }

        if (!hasInventoryIngredients(model.getInventory(), selectedRecipe)) {
            throw new CommandException(String.format(MESSAGE_MISSING_INGREDIENT,
                    selectedRecipe.getName().toString()));
        } else if (!hasSufficientInventoryIngredients(model.getInventory(), selectedRecipe)) {
            throw new CommandException(String.format(MESSAGE_INSUFFICIENT_QUANTITY,
                    selectedRecipe.getName().toString()));
        }

        List<Ingredient> ingredientList = selectedRecipe.getIngredients().asUnmodifiableObservableList();
        for (Ingredient ingredient : ingredientList) {
            model.removeInventoryIngredient(ingredient);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryCookCommand // instanceof handles nulls
                && targetIndex.equals(((InventoryCookCommand) other).targetIndex));
    }
}
