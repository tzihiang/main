package seedu.address.logic.commands.cookbook;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_NAME;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;

/**
 * Adds a recipe into the cookbook.
 */
public class CookbookAddCommand extends CookbookCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": Adds a new recipe to the cookbook.\n"
            + "Parameters: "
            + PREFIX_RECIPE_NAME + "NAME "
            + PREFIX_RECIPE_DESCRIPTION + "DESCRIPTION\n"
            + "Example: "
            + COMMAND_CATEGORY + " " + COMMAND_WORD + " "
            + PREFIX_RECIPE_NAME + "Bacon Carbonara "
            + PREFIX_RECIPE_DESCRIPTION + "An Italian classic pasta dish with creamy egg sauce"
            + " and bacon topped with salty Parmesan cheese.";

    public static final String MESSAGE_SUCCESS = "New recipe (index %2$d) added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the cookbook.";

    private final Recipe toAdd;

    /**
     * Creates a CookbookAddCommand to add a new {@code recipe} to the cookbook
     */
    public CookbookAddCommand(Recipe recipe) {
        requireNonNull(recipe);
        toAdd = recipe;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCookbookRecipe(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.addCookbookRecipe(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getName().fullRecipeName,
                model.getCookbook().getRecipeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CookbookAddCommand // instanceof handles nulls
                && toAdd.equals(((CookbookAddCommand) other).toAdd));
    }
}
