package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid \n %s";
    public static final String MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX = "The recipe index provided is invalid \n %s";
    public static final String MESSAGE_INVALID_STEP_DISPLAYED_INDEX = "The step index provided is invalid";
    public static final String MESSAGE_DUPLICATE_STEPS = "This step already exists in the recipe";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in the recipe";
    public static final String MESSAGE_INVALID_TAG = "The Tag provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

    public static final String MESSAGE_DIFFERENT_NUMBER_OF_INPUTS = "The number of ingredient names provided (%d)"
            + " should be equal to the number of ingredient quantities (%d) provided.";
}
