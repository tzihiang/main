package seedu.address.logic.commands.cookbook;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;

/**
 * Removes a recipe identified using it's displayed index from the cookbook.
 */
public class CookbookRemoveRecipeCommand extends CookbookCommand {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": Removes the recipe identified by the index number used in the displayed recipe list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_CATEGORY + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_RECIPE_SUCCESS = "Deleted Recipe: %1$s";

    private final Index targetIndex;

    public CookbookRemoveRecipeCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToRemove = lastShownList.get(targetIndex.getZeroBased());
        model.removeRecipe(recipeToRemove);
        return new CommandResult(String.format(MESSAGE_REMOVE_RECIPE_SUCCESS, recipeToRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CookbookRemoveRecipeCommand // instanceof handles nulls
                && targetIndex.equals(((CookbookRemoveRecipeCommand) other).targetIndex)); // state check
    }
}
