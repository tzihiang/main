package seedu.address.logic.commands.recipe;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STEP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STEP_INDEX;

/**
 * Remove a step or ingredient in a recipe inside the cookbook.
 */
public abstract class RecipeRemoveCommand extends RecipeCommand {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": Removes an ingredient or a step to a recipe. "
            + "Parameters for removing an ingredient: "
            + "INDEX (must be a positive integer)"
            + PREFIX_INGREDIENT_NAME + "INGREDIENT "
            + "[" + PREFIX_INGREDIENT_QUANTITY + "QUANTITY]\n"
            + "Example: " + COMMAND_CATEGORY + " 1 "
            + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Eggs "
            + PREFIX_INGREDIENT_QUANTITY + "12\n"
            + "Parameters for removing a step: "
            + "INDEX (must be a positive integer)"
            + PREFIX_STEP_INDEX + "STEP_INDEX\n"
            + "Example: " + COMMAND_CATEGORY + " 1 "
            + COMMAND_WORD + " "
            + PREFIX_STEP_INDEX + "1 ";
}
