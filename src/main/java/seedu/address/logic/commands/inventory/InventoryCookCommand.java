package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Recipe;

/**
 * Removes all the ingredients of the selected recipe from the inventory
 */

public class InventoryCookCommand extends InventoryCommand {

    public static final String COMMAND_WORD = "cook recipe";
    public static final String MESSAGE_SUCCESS = "All ingredients from recipe %1$s removed from inventory";
    public static final String MESSAGE_MISSING_INGREDIENT =
            "Your inventory does not contain all the ingredients of recipe %1$s.";
    public static final String MESSAGE_INSUFFICIENT_QUANTITY =
            "Your inventory contains insufficient quantities of the ingredients in recipe %1$s.";

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

    private boolean areIngredientsPresent(Recipe recipe) {
        // TODO
        return true;
    }

    private boolean areIngredientsSufficient(Recipe recipe) {
        // TODO
        return true;
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
        if (!areIngredientsPresent(selectedRecipe)) {
            throw new CommandException(String.format(MESSAGE_MISSING_INGREDIENT));
        } else if (!areIngredientsSufficient(selectedRecipe)) {
            throw new CommandException(String.format(MESSAGE_INSUFFICIENT_QUANTITY));
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
