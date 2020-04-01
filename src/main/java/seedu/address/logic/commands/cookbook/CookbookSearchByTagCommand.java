package seedu.address.logic.commands.cookbook;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class CookbookSearchByTagCommand extends CookbookSearchCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all recipes whose recipe names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: " + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_CATEGORY + " " + COMMAND_WORD + "breakfast";

    public CookbookSearchByTagCommand(Tag toSearch) {
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
        // TODO
        return false;
    }
}

