package seedu.address.logic.commands.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECIPES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.UniqueIngredientList;
import seedu.address.model.recipe.Recipe;

/**
 * Adds a person to the address book.
 */
public class RecipeAddIngredientCommand extends RecipeCommand {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_TYPE = "ingredient";

    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD + " " + COMMAND_TYPE
            + ": Adds an ingredient to a recipe. "
            + "Parameters: "
            + "INDEX (must be a positive integer)"
            + PREFIX_INGREDIENT_NAME + "INGREDIENT "
            + PREFIX_INGREDIENT_QUANTITY + "QUANTITY\n"
            + "Example: " + COMMAND_CATEGORY + " 1 "
            + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Eggs "
            + PREFIX_INGREDIENT_QUANTITY + "12";

    public static final String MESSAGE_SUCCESS = "New ingredient added: %1$s";
    public static final String MESSAGE_INCOMPATIBLE_UNITS = "This ingredient has different units "
            + "from the same ingredient in the recipe";

    private final Index index;
    private final Ingredient toAdd;

    /**
     * Creates a RecipeAddIngredientCommand to add the specified {@code Ingredient} to the recipe
     */
    public RecipeAddIngredientCommand(Index index, Ingredient toAdd) {
        requireNonNull(index);
        requireNonNull(toAdd);
        this.index = index;
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToEdit = lastShownList.get(index.getZeroBased());
        UniqueIngredientList ingredients = recipeToEdit.getIngredients();
        ingredients.add(toAdd);

        EditRecipeDescriptor editRecipeDescriptor = new EditRecipeDescriptor();
        editRecipeDescriptor.setIngredients(ingredients);
        Recipe editedRecipe = EditRecipeDescriptor.createEditedRecipe(recipeToEdit, editRecipeDescriptor);

        model.setRecipe(recipeToEdit, editedRecipe);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecipeAddIngredientCommand // instanceof handles nulls
                && toAdd.equals(((RecipeAddIngredientCommand) other).toAdd));
    }
}
