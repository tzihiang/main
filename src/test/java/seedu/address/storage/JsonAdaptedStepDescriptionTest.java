package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStepDescription.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSteps.CARBONARA_ONE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.step.Step;

public class JsonAdaptedStepDescriptionTest {
    private static final String INVALID_STEP_DESCRIPTION = " ";

    private static final String VALID_STEP_DESCRIPTION = CARBONARA_ONE.toString();

    @Test
    public void toModelType_validStepDescription_returnsStep() throws Exception {
        JsonAdaptedStepDescription step = new JsonAdaptedStepDescription(VALID_STEP_DESCRIPTION);
        assertEquals(CARBONARA_ONE, step.toModelType());
    }

    @Test
    public void toModelType_invalidStepDescription_throwsIllegalValueException() {
        JsonAdaptedStepDescription step = new JsonAdaptedStepDescription(INVALID_STEP_DESCRIPTION);
        String expectedMessage = Step.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, step::toModelType);
    }

    @Test
    public void toModelType_nullStepDescription_throwsIllegalValueException() {
        JsonAdaptedStepDescription step = new JsonAdaptedStepDescription((String) null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "step description");
        assertThrows(IllegalValueException.class, expectedMessage, step::toModelType);
    }
}
