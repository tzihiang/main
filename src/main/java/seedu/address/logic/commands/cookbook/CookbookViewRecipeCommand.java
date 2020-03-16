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
 * View a recipe identified using it's displayed index from the cookbook.
 */
public class CookbookViewRecipeCommand extends CookbookCommand {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": Views the recipe identified by the index number used in the displayed recipe list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_CATEGORY + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_RECIPE_SUCCESS = "Selected Recipe %1$s";

    private final Index targetIndex;

    public CookbookViewRecipeCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToView = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_REMOVE_RECIPE_SUCCESS, recipeToView));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CookbookViewRecipeCommand // instanceof handles nulls
                && targetIndex.equals(((CookbookViewRecipeCommand) other).targetIndex)); // state check
    }
}
