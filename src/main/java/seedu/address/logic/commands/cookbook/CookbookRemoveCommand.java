package seedu.address.logic.commands.cookbook;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX;

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
public class CookbookRemoveCommand extends CookbookCommand {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": removes the recipe identified by the index number used in the displayed recipe list.\n\n"
            + "Parameters: INDEX (must be a positive integer)\n\n"
            + "Example: " + COMMAND_CATEGORY + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_RECIPE_SUCCESS = "Deleted Recipe: %1$s";

    private final Index targetIndex;

    public CookbookRemoveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredCookbookRecipeList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX + "\n"
                    + CookbookRemoveCommand.MESSAGE_USAGE);
        }

        Recipe recipeToRemove = lastShownList.get(targetIndex.getZeroBased());
        model.removeCookbookRecipe(recipeToRemove);
        return new CommandResult(String.format(MESSAGE_REMOVE_RECIPE_SUCCESS, recipeToRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CookbookRemoveCommand // instanceof handles nulls
                && targetIndex.equals(((CookbookRemoveCommand) other).targetIndex)); // state check
    }
}
