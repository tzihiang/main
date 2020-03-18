package seedu.address.logic.parser.cookbook;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.cookbook.CookbookViewCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CookbookViewCommand object
 */
public class CookbookViewCommandParser implements Parser<CookbookViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CookbookViewCommand
     * and returns a CookbookViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CookbookViewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CookbookViewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CookbookViewCommand.MESSAGE_USAGE), pe);
        }
    }

}
