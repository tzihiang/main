package seedu.address.logic.commands.cookbook;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_KEYWORD;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.recipe.RecipeNameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class CookbookSearchByKeywordCommand extends CookbookSearchCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all recipes whose recipe names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: " + PREFIX_SEARCH_KEYWORD + "KEYWORD\n"
            + "Example: " + COMMAND_WORD + " bacon carbonara";

    private final RecipeNameContainsKeywordsPredicate predicate;

    public CookbookSearchByKeywordCommand(RecipeNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCookbookRecipeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CookbookSearchByKeywordCommand
                && predicate.equals(((CookbookSearchByKeywordCommand) other).predicate));
    }
}

