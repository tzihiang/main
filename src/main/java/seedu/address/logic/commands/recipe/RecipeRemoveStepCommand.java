package seedu.address.logic.commands.recipe;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * todo
 */
public class RecipeRemoveStepCommand extends RecipeRemoveCommand {

    public static final String MESSAGE_SUCCESS = "step deleted: %1$s";

    private final Index recipeIndex;
    private final Index stepIndex;

    public RecipeRemoveStepCommand(Index recipeIndex, Index stepIndex) {
        requireNonNull(recipeIndex);
        requireNonNull(stepIndex);
        this.recipeIndex = recipeIndex;
        this.stepIndex = stepIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
