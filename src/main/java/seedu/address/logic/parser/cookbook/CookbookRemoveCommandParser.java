package seedu.address.logic.parser.cookbook;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.cookbook.CookbookRemoveCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CookbookRemoveCommand object
 */
public class CookbookRemoveCommandParser implements Parser<CookbookRemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CookbookRemoveCommand
     * and returns a CookbookRemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CookbookRemoveCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CookbookRemoveCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX,
                    CookbookRemoveCommand.MESSAGE_USAGE));
        }
    }
}
