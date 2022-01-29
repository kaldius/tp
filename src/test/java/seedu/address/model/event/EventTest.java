package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE;
import static seedu.address.testutil.TypicalEvents.BENSON;
import static seedu.address.testutil.TypicalEvents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Event event = new EventBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> event.getTags().remove(0));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event aliceCopy = new EventBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different event -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Event editedAlice = new EventBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different date -> returns false
        editedAlice = new EventBuilder(ALICE).withDate(VALID_DATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different time -> returns false
        editedAlice = new EventBuilder(ALICE).withTimeSlot(VALID_STARTTIME_BOB, VALID_ENDTIME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new EventBuilder(ALICE).withLocation(VALID_LOCATION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new EventBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void hashCodeMethod() {
        Event eventA = new EventBuilder().build();
        Event sameAsEventA = new EventBuilder().build();
        Event eventB = new EventBuilder().withName("Different Name").build();
        assertEquals(eventA.hashCode(), sameAsEventA.hashCode());
        assertNotEquals(eventA.hashCode(), eventB.hashCode());
    }

    @Test
    public void compareTo() {
        Event earlierEvent = ALICE;
        Event sameAsEarlierEvent = new EventBuilder(BENSON)
                .withTimeSlot("0800", "0900")
                .withDate("2020-01-01")
                .build();
        Event laterDateEvent = new EventBuilder(BENSON)
                .withTimeSlot("0800", "0900")
                .withDate("2020-01-02")
                .build();
        Event laterSlotEvent = new EventBuilder(BENSON)
                .withTimeSlot("1000", "1100")
                .withDate("2020-01-01")
                .build();
        assertTrue(earlierEvent.compareTo(sameAsEarlierEvent) == 0);
        assertTrue(earlierEvent.compareTo(laterDateEvent) < 0);
        assertTrue(earlierEvent.compareTo(laterSlotEvent) < 0);
        assertTrue(laterDateEvent.compareTo(earlierEvent) > 0);
        assertTrue(laterSlotEvent.compareTo(earlierEvent) > 0);
    }

    @Test
    public void isOverlappingWith() {
        Event toCompareEvent = ALICE;
        Event noOverlappingDateNoOverlappingSlot = new EventBuilder(BENSON)
                .withTimeSlot("0900", "1000")
                .withDate("2020-01-02")
                .build();
        Event noOverlappingDateHasOverlappingSlot = new EventBuilder(BENSON)
                .withTimeSlot("0830", "0930")
                .withDate("2020-01-02")
                .build();
        Event overlappingDateNoOverlappingSlot = new EventBuilder(BENSON)
                .withTimeSlot("0900", "1000")
                .withDate("2020-01-01")
                .build();
        Event overlappingDateHasOverlappingSlot1 = new EventBuilder(BENSON)
                .withTimeSlot("0830", "0930")
                .withDate("2020-01-01")
                .build();
        Event overlappingDateHasOverlappingSlot2 = new EventBuilder(BENSON)
                .withTimeSlot("0730", "0830")
                .withDate("2020-01-01")
                .build();
        Event overlappingDateHasOverlappingSlot3 = new EventBuilder(BENSON)
                .withTimeSlot("0830", "0845")
                .withDate("2020-01-01")
                .build();
        Event overlappingDateHasOverlappingSlot4 = new EventBuilder(BENSON)
                .withTimeSlot("0700", "1000")
                .withDate("2020-01-01")
                .build();

        assertFalse(toCompareEvent.isOverlappingWith(noOverlappingDateNoOverlappingSlot));
        assertFalse(toCompareEvent.isOverlappingWith(noOverlappingDateHasOverlappingSlot));
        assertFalse(toCompareEvent.isOverlappingWith(overlappingDateNoOverlappingSlot));
        assertTrue(toCompareEvent.isOverlappingWith(overlappingDateHasOverlappingSlot1));
        assertTrue(toCompareEvent.isOverlappingWith(overlappingDateHasOverlappingSlot2));
        assertTrue(toCompareEvent.isOverlappingWith(overlappingDateHasOverlappingSlot3));
        assertTrue(toCompareEvent.isOverlappingWith(overlappingDateHasOverlappingSlot4));
    }
}


