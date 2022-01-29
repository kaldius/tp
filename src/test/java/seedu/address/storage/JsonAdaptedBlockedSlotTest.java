package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedBlockedSlot.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBlockedSlots.EIGHT_TO_NINE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Date;
import seedu.address.model.event.TimeSlot;

public class JsonAdaptedBlockedSlotTest {
    private static final String INVALID_DATE = "2020";
    private static final String INVALID_STARTIME = "15:30";
    private static final String INVALID_ENDTIME = "16:30";

    private static final String VALID_DATE = EIGHT_TO_NINE.getDate().toString();
    private static final String VALID_STARTTIME = EIGHT_TO_NINE.getTimeSlot().startTimeToString();
    private static final String VALID_ENDTIME = EIGHT_TO_NINE.getTimeSlot().endTimeToString();


    @Test
    public void toModelType_validBlockedSlotDetails_returnsBlockedSlot() throws Exception {
        JsonAdaptedBlockedSlot blockedSlot = new JsonAdaptedBlockedSlot(EIGHT_TO_NINE);
        assertEquals(EIGHT_TO_NINE, blockedSlot.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedBlockedSlot blockedSlot = new JsonAdaptedBlockedSlot(INVALID_DATE, VALID_STARTTIME, VALID_ENDTIME);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, blockedSlot::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedBlockedSlot blockedSlot = new JsonAdaptedBlockedSlot(null, VALID_STARTTIME, VALID_ENDTIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, blockedSlot::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedBlockedSlot blockedSlot = new JsonAdaptedBlockedSlot(VALID_DATE, INVALID_STARTIME, VALID_ENDTIME);
        String expectedMessage = TimeSlot.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, blockedSlot::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedBlockedSlot blockedSlot = new JsonAdaptedBlockedSlot(VALID_DATE, VALID_STARTTIME, INVALID_ENDTIME);
        String expectedMessage = TimeSlot.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, blockedSlot::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedBlockedSlot blockedSlot = new JsonAdaptedBlockedSlot(VALID_DATE, null, VALID_ENDTIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimeSlot.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, blockedSlot::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedBlockedSlot blockedSlot = new JsonAdaptedBlockedSlot(VALID_DATE, VALID_STARTTIME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimeSlot.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, blockedSlot::toModelType);
    }
}
