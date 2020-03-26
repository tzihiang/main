package seedu.address.logic.commands.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECIPES;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.UniqueIngredientList;
import seedu.address.model.recipe.Recipe;



/**
 * Removes an ingredient from a recipe.
 */
public class RecipeRemoveIngredientCommand extends RecipeRemoveCommand {

    public static final String MESSAGE_SUCCESS = "Ingredient removed for %1$s: %2$s";
    public static final String MESSAGE_INCOMPATIBLE_UNITS = "This ingredient has different units "
            + "from the same ingredient in the recipe";

    private final Index index;
    private final Ingredient toRemove;

    /**
     * Creates a RecipeRemoveIngredientCommand to remove the specified {@code Ingredient} from the recipe
     */
    public RecipeRemoveIngredientCommand(Index index, Ingredient toRemove) {
        requireNonNull(index);
        requireNonNull(toRemove);
        this.index = index;
        this.toRemove = toRemove;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredCookbookRecipeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX,
                    RecipeRemoveCommand.MESSAGE_USAGE));
        }

        Recipe recipeToEdit = lastShownList.get(index.getZeroBased());
        UniqueIngredientList ingredients = recipeToEdit.getIngredients();
        ingredients.remove(toRemove);
        System.out.println(ingredients);
        EditRecipeDescriptor editRecipeDescriptor = new EditRecipeDescriptor();
        editRecipeDescriptor.setIngredients(ingredients);
        Recipe editedRecipe = EditRecipeDescriptor.createEditedRecipe(recipeToEdit, editRecipeDescriptor);

        model.setCookbookRecipe(recipeToEdit, editedRecipe);
        model.updateFilteredCookbookRecipeList(PREDICATE_SHOW_ALL_RECIPES);

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedRecipe.getName().fullRecipeName, toRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecipeRemoveIngredientCommand // instanceof handles nulls
                && toRemove.equals(((RecipeRemoveIngredientCommand) other).toRemove));
    }
}

