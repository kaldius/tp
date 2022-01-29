package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    public void getDate() {
        Date expectedDate = new Date("2020-01-01");
        TimeSlot timeSlot = new TimeSlot("0800", "0900");
        FreeSlot freeSlot = new FreeSlot(expectedDate, timeSlot);
        assertEquals(expectedDate, freeSlot.getDate());
    }

    @Test
    public void getTimeSlot() {
        Date date = new Date("2020-01-01");
        TimeSlot expectedTimeSlot = new TimeSlot("0800", "0900");
        FreeSlot freeSlot = new FreeSlot(date, expectedTimeSlot);
        assertEquals(expectedTimeSlot, freeSlot.getTimeSlot());
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

    @Test
    public void hashCodeMethod() {
        Date dateA = new Date("2020-01-01");
        Date dateB = new Date("2020-01-02");
        TimeSlot timeSlot = new TimeSlot("0800", "0900");
        FreeSlot freeSlotA = new FreeSlot(dateA, timeSlot);
        FreeSlot sameAsFreeSlotA = new FreeSlot(dateA, timeSlot);
        FreeSlot freeSlotB = new FreeSlot(dateB, timeSlot);

        assertEquals(freeSlotA.hashCode(), freeSlotA.hashCode());
        assertEquals(freeSlotA.hashCode(), sameAsFreeSlotA.hashCode());
        assertNotEquals(freeSlotA.hashCode(), freeSlotB.hashCode());
    }
}
