package seedu.address.logic.commands.cookbook;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_RECIPES_LISTED_OVERVIEW;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.recipe.RecipeContainsIngredientsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class CookbookSearchByInventoryCommand extends CookbookSearchCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all recipes whose recipe names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " bacon carbonara";

    public CookbookSearchByInventoryCommand() {
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCookbookRecipeList(
                new RecipeContainsIngredientsPredicate(model.getInventory().getIngredientNamesString()));
        return new CommandResult(
                String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}

