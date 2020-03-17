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
            .compile("(?<commandWord>\\S+) (?<index>\\S+)");
    private static final Pattern COOKBOOK_ADD_COMMAND_ARGUMENT_FORMAT = Pattern
            .compile("(?<commandWord>\\S+) (?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of a CookbookCommand
     * and returns a CookbookCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CookbookCommand parse(String args) throws ParseException {
        final Matcher matcher1 = COOKBOOK_COMMAND_ARGUMENT_FORMAT.matcher(args.trim());
        final Matcher matcher2 = COOKBOOK_ADD_COMMAND_ARGUMENT_FORMAT.matcher(args.trim());

        if (!matcher1.matches() && !matcher2.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher1.group("commandWord");
        final String index = matcher1.group("index");

        final String arguments = matcher2.group("arguments");

        switch(commandWord) {
        case CookbookAddCommand.COMMAND_WORD:
            return new CookbookAddCommandParser().parse(arguments);
        case CookbookViewCommand.COMMAND_WORD:
            return new CookbookViewCommandParser().parse(index);
        case CookbookRemoveCommand.COMMAND_WORD:
            return new CookbookRemoveCommandParser().parse(commandWord + " " + index);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
