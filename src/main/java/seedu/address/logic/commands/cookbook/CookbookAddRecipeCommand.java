package seedu.address.logic.commands.cookbook;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_DESCRIPTION;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;


public class CookbookAddRecipeCommand extends CookbookCommand {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_TYPE = "recipe";

    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD + " " + COMMAND_TYPE
            + ": Adds a new recipe to the cookbook. "
            + "Parameters: "
            + PREFIX_RECIPE_NAME + "NAME "
            + PREFIX_RECIPE_DESCRIPTION + "DESCRIPTION\n"
            + "Example: "
            + COMMAND_WORD + " " + COMMAND_WORD + " "
            + PREFIX_RECIPE_NAME + "Bacon Carbonara "
            + PREFIX_RECIPE_DESCRIPTION + "Bacon Carbonara is an Italian classic pasta dish with creamy egg sauce"
            + " with noodles, pasta, eggs and bacon topped with salty Parmesan cheese in under 30 minutes.";

    public static final String MESSAGE_SUCCESS = "New recipe added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This recipe already exists in the cookbook.";

    private final Recipe toAdd;

    /**
     * Creates a CookbookAddRecipeCommand to add a new {@code recipe} to the cookbook
     */
    public CookbookAddRecipeCommand(Recipe recipe) {
        requireNonNull(recipe);
        toAdd = recipe;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasRecipe(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addRecipe(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CookbookAddRecipeCommand // instanceof handles nulls
                && toAdd.equals(((CookbookAddRecipeCommand) other).toAdd));
    }
}
