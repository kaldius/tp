package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBlockedSlots.EIGHT_TO_NINE;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.Date;
import seedu.address.model.event.TimeSlot;



public class FreeSlotTest {
    @Test
    public void constructor_startTimeNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FreeSlot(null, new TimeSlot("0000", "2359")));
    }

    @Test
    public void constructor_endTimeNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FreeSlot(new Date("2021-10-31"), null));
    }

    @Test
    public void isOverlappingWith() {
        Overlappable toCompare = EIGHT_TO_NINE;
        FreeSlot same = new FreeSlot(new Date("2020-01-01"), new TimeSlot("0800", "0900"));
        FreeSlot diffDate = new FreeSlot(new Date("2020-01-02"), new TimeSlot("0800", "0900"));
        FreeSlot diffTime = new FreeSlot(new Date("2020-01-01"), new TimeSlot("0900", "1000"));
        assertTrue(same.isOverlappingWith(toCompare));
        assertFalse(diffDate.isOverlappingWith(toCompare));
        assertFalse(diffTime.isOverlappingWith(toCompare));
    }
}
