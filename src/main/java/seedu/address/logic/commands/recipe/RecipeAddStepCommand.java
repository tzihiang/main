package seedu.address.logic.commands.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECIPES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.step.Step;
import seedu.address.model.step.UniqueStepList;

/**
 * Adds a step to a recipe.
 */
public class RecipeAddStepCommand extends RecipeAddCommand {

    public static final String MESSAGE_SUCCESS = "New step added: %1$s";
    public static final String MESSAGE_DUPLICATE_STEPS = "This step already exists in the recipe";

    private final Index recipeIndex;
    private final Index stepIndex;
    private final Step toAdd;

    public RecipeAddStepCommand(Index recipeIndex, Index stepIndex, Step toAdd) {
        requireNonNull(recipeIndex);
        requireNonNull(stepIndex);
        requireNonNull(toAdd);
        this.recipeIndex = recipeIndex;
        this.stepIndex = stepIndex;
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredCookbookRecipeList();

        if (recipeIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToEdit = lastShownList.get(recipeIndex.getZeroBased());
        UniqueStepList targetStepList = recipeToEdit.getSteps();

        if (stepIndex.getZeroBased() > targetStepList.asUnmodifiableObservableList().size()) {
            // ensure the step index is valid
            throw new CommandException((Messages.MESSAGE_INVALID_STEP_DISPLAYED_INDEX));
        }

        targetStepList.add(stepIndex, toAdd);

        EditRecipeDescriptor editRecipeDescriptor = new EditRecipeDescriptor();
        editRecipeDescriptor.setSteps(targetStepList);
        Recipe editedRecipe = EditRecipeDescriptor.createEditedRecipe(recipeToEdit, editRecipeDescriptor);
        model.setCookbookRecipe(recipeToEdit, editedRecipe);
        model.updateFilteredCookbookRecipeList(PREDICATE_SHOW_ALL_RECIPES);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecipeAddStepCommand // instanceof handles nulls
                && toAdd.equals(((RecipeAddStepCommand) other).toAdd));
    }
}
