package seedu.address.logic.commands.recipe;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STEP_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Remove a step/ingredient/tag in a recipe inside the cookbook.
 */
public abstract class RecipeRemoveCommand extends RecipeCommand {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": removes an ingredient, a step, or a tag from a recipe. "
            + "\nParameters: \n"
            + "INDEX (must be a positive integer) "
            + PREFIX_INGREDIENT_NAME + "INGREDIENT "
            + "[" + PREFIX_INGREDIENT_QUANTITY + "QUANTITY]\n"
            + "Example: " + COMMAND_CATEGORY + " 1 "
            + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Eggs "
            + PREFIX_INGREDIENT_QUANTITY + "12\n"
            + "\nParameters for removing a step: "
            + "INDEX (must be a positive integer) "
            + PREFIX_STEP_INDEX + "STEP_INDEX\n"
            + "Example: " + COMMAND_CATEGORY + " 1 "
            + COMMAND_WORD + " "
            + PREFIX_STEP_INDEX + "1\n"
            + "\nParameters for removing a tag: "
            + "INDEX (must be a positive integer) "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_CATEGORY + " 1 "
            + COMMAND_WORD + " "
            + PREFIX_TAG + "Pasta";
}
