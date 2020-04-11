package seedu.address.logic.commands.cookbook;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_RECIPES_LISTED_OVERVIEW;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.recipe.RecipeContainsInventoryIngredientsPredicate;
import seedu.address.model.recipe.RecipeInventorySimilarityComparator;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class CookbookSearchByInventoryCommand extends CookbookSearchCommand {

    public static final String MESSAGE_USAGE = "\n" + COMMAND_CATEGORY + " " + COMMAND_WORD + ": Finds all recipes "
            + "whose recipe names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " bacon carbonara";

    public CookbookSearchByInventoryCommand() {
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ReadOnlyInventory inventory = model.getInventory();
        model.sortCookbook(new RecipeInventorySimilarityComparator(inventory));
        model.updateFilteredCookbookRecipeList(new RecipeContainsInventoryIngredientsPredicate(inventory));
        return new CommandResult(
                String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, model.getFilteredCookbookRecipeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof CookbookSearchByInventoryCommand;
    }
}
