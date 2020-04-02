package seedu.address.logic.commands.cookbook;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_KEYWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Searches for a recipe in the cookbook based on inventory/keyword/tag.
 */
public abstract class CookbookSearchCommand extends CookbookCommand {

    public static final String COMMAND_WORD = "search recipe";
    public static final String SEARCH_INVENTORY_COMMAND = "inventory";

    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": Searches the cookbook by keyword, tag(s) or your inventory.\n"
            + "Parameters for searching the cookbook by keyword: "
            + PREFIX_SEARCH_KEYWORD + "KEYWORD\n"
            + "Example: " + COMMAND_CATEGORY + " " + COMMAND_WORD + " "
            + PREFIX_SEARCH_KEYWORD + "carbonara\n"
            + "Parameters for searching the cookbook by tag(s): "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_CATEGORY + " " + COMMAND_WORD + " "
            + PREFIX_TAG + "breakfast\n"
            + "Parameters for searching the cookbook using your inventory"
            + SEARCH_INVENTORY_COMMAND + "\n"
            + "Example: " + COMMAND_CATEGORY + " " + COMMAND_WORD + " "
            + SEARCH_INVENTORY_COMMAND;
}
