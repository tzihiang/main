package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.cookbook.CookbookCommand;
import seedu.address.logic.commands.inventory.InventoryCommand;
import seedu.address.logic.commands.recipe.RecipeAddCommand;
import seedu.address.logic.commands.recipe.RecipeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class CookingPapaParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandCategory>\\S+)(?<arguments>.*)");
    private static final Pattern RECIPE_COMMAND_ARGUMENT_FORMAT = Pattern
            .compile("(?<index>\\S+) *(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into a command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform to the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandCategory = matcher.group("commandCategory");
        final String arguments = matcher.group("arguments");
        switch (commandCategory) {

        case CookbookCommand.COMMAND_CATEGORY:
            return parseCookbookCommand();

        case RecipeCommand.COMMAND_CATEGORY:
            return parseRecipeCommand(arguments);

        case InventoryCommand.COMMAND_CATEGORY:
            return parseInventoryCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private CookbookCommand parseCookbookCommand() {
        // TODO: implement
        return null;
    }

    /**
     * Parses arguments into a recipe command for execution.
     *
     * @param args the arguments of the user input
     * @return the {@code RecipeCommand} based on the user input
     * @throws ParseException if the arguments do not conform to the expected format
     */
    private RecipeCommand parseRecipeCommand(String args) throws ParseException {
        final Matcher matcher = RECIPE_COMMAND_ARGUMENT_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String index = matcher.group("index");
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case RecipeAddCommand.COMMAND_WORD:
            return new RecipeAddCommandParser().parse(index + " " + arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private InventoryCommand parseInventoryCommand() {
        // TODO: implement
        return null;
    }

}
