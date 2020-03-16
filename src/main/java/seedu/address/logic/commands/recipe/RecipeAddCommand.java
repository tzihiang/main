package seedu.address.logic.commands.recipe;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STEP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STEP_INDEX;

/**
 * Adds a person to the address book.
 */
public abstract class RecipeAddCommand extends RecipeCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": Adds an ingredient or a step to a recipe. "
            + "Parameters for adding an ingredient: "
            + "INDEX (must be a positive integer)"
            + PREFIX_INGREDIENT_NAME + "INGREDIENT "
            + PREFIX_INGREDIENT_QUANTITY + "QUANTITY\n"
            + "Example: " + COMMAND_CATEGORY + " 1 "
            + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Eggs "
            + PREFIX_INGREDIENT_QUANTITY + "12\n"
            + "Parameters for adding a step: "
            + "INDEX (must be a positive integer)"
            + PREFIX_STEP_INDEX + "STEP_INDEX "
            + PREFIX_STEP_DESCRIPTION + "STEP_DESCRIPTION\n"
            + "Example: " + COMMAND_CATEGORY + " 1 "
            + COMMAND_WORD + " "
            + PREFIX_STEP_INDEX + "1 "
            + PREFIX_STEP_DESCRIPTION + "Add potatoes and water to a large pot, and bring to a boil.\n";

}
