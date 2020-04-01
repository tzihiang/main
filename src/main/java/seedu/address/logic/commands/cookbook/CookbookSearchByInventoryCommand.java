package seedu.address.logic.commands.cookbook;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

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
        // TODO
    }

    @Override
    public CommandResult execute(Model model) {
        // TODO
        requireNonNull(model);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return false; // TODO
    }
}

