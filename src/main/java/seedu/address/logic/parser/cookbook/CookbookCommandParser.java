package seedu.address.logic.parser.cookbook;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.cookbook.CookbookAddCommand;
import seedu.address.logic.commands.cookbook.CookbookCommand;
import seedu.address.logic.commands.cookbook.CookbookRemoveCommand;
import seedu.address.logic.commands.cookbook.CookbookViewCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
* Parses input arguments and into a {@code CookbookCommand}.
 */
public class CookbookCommandParser implements Parser<CookbookCommand> {

    private static final Pattern COOKBOOK_COMMAND_ARGUMENT_FORMAT = Pattern
            .compile("(?<commandWord>\\S+) (?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of a CookbookCommand
     * and returns a CookbookCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CookbookCommand parse(String args) throws ParseException {
        final Matcher matcher = COOKBOOK_COMMAND_ARGUMENT_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch(commandWord) {
        case CookbookAddCommand.COMMAND_WORD:
            return new CookbookAddCommandParser().parse(arguments);
        case CookbookViewCommand.COMMAND_WORD:
            return new CookbookViewCommandParser().parse(arguments);
        case CookbookRemoveCommand.COMMAND_WORD:
            return new CookbookRemoveCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
