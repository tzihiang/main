package seedu.address.logic.commands.cart;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 *  Exports the cart to a pdf file.
 */
public class CartExportCommand extends CartCommand {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "All cart ingredients have been exported to a pdf file!";
    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
        + "This commands allows you to export all ingredients inside the cart to a pdf file located in the same folder"
        + "as Cooking Papa\n. Parameters for exporting all ingredients inside the cart is as follows:\n"
        + COMMAND_CATEGORY + " " + COMMAND_WORD;

    private static final String MESSAGE_FILE_NOT_FOUND = "File not found.";

    public CartExportCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            model.exportCart();
        } catch (IOException | DocumentException e) {
            return new CommandResult(MESSAGE_FILE_NOT_FOUND);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof CartExportCommand;
    }
}
