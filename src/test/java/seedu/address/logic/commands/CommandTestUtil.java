package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_INGREDIENT_NAME_ALMOND = "Almonds";
    public static final String VALID_INGREDIENT_NAME_BANANA = "Bananas";
    public static final String VALID_INGREDIENT_QUANTITY_ALMOND = "50 g";
    public static final String VALID_INGREDIENT_QUANTITY_BANANA = "3";

    public static final String INGREDIENT_NAME_DESC_ALMOND = " " + PREFIX_INGREDIENT_NAME
            + VALID_INGREDIENT_NAME_ALMOND;
    public static final String INGREDIENT_NAME_DESC_BANANA = " " + PREFIX_INGREDIENT_NAME
            + VALID_INGREDIENT_NAME_BANANA;
    public static final String INGREDIENT_QUANTITY_DESC_ALMOND = " " + PREFIX_INGREDIENT_QUANTITY
            + VALID_INGREDIENT_QUANTITY_ALMOND;
    public static final String INGREDIENT_QUANTITY_DESC_BANANA = " " + PREFIX_INGREDIENT_QUANTITY
            + VALID_INGREDIENT_QUANTITY_BANANA;

    public static final String INVALID_INGREDIENT_NAME_DESC = " " + PREFIX_INGREDIENT_NAME
            + "R!ce"; // '!' not allowed in names
    public static final String INVALID_INGREDIENT_QUANTITY_DESC = " " + PREFIX_INGREDIENT_QUANTITY
            + "*halal"; // '*' not allowed in tags

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    static {
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
    }


}
