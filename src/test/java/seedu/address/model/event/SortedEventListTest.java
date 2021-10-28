package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE;
import static seedu.address.testutil.TypicalEvents.BENSON;
import static seedu.address.testutil.TypicalEvents.BOB;
import static seedu.address.testutil.TypicalEvents.CARL;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.testutil.EventBuilder;

public class SortedEventListTest {

    private final SortedEventList sortedEventList = new SortedEventList();

    @Test
    public void sortEventByDate() {
        sortedEventList.add(BENSON);
        sortedEventList.add(ALICE);
        SortedEventList expectedSortedEventList = new SortedEventList();
        expectedSortedEventList.add(ALICE);
        expectedSortedEventList.add(BENSON);
        assertEquals(expectedSortedEventList, sortedEventList);
    }

    @Test
    public void sortEventByEditedDate() {
        sortedEventList.add(BENSON);
        sortedEventList.add(ALICE);
        Event editedAlice = new EventBuilder(ALICE).withDate("2020-01-03").build();
        sortedEventList.setEvent(ALICE, editedAlice);
        SortedEventList expectedSortedEventList = new SortedEventList();
        expectedSortedEventList.add(BENSON);
        expectedSortedEventList.add(editedAlice);
        assertEquals(expectedSortedEventList, sortedEventList);
    }

    @Test
    public void sortEventByTimeSlot() {
        Event sameDateCarl = new EventBuilder(CARL).withDate("2020-01-01").build();
        sortedEventList.add(sameDateCarl);
        sortedEventList.add(ALICE);
        SortedEventList expectedSortedEventList = new SortedEventList();
        expectedSortedEventList.add(ALICE);
        expectedSortedEventList.add(sameDateCarl);
        assertEquals(expectedSortedEventList, sortedEventList);
    }

    @Test
    public void sortEventByTimeSlotDate() {
        Event sameDateCarl = new EventBuilder(CARL).withDate("2020-01-01").build();
        sortedEventList.add(ALICE);
        sortedEventList.add(sameDateCarl);
        Event editedAlice = new EventBuilder(ALICE).withTimeSlot("1200", "1230").build();
        sortedEventList.setEvent(ALICE, editedAlice);
        SortedEventList expectedSortedEventList = new SortedEventList();
        expectedSortedEventList.add(sameDateCarl);
        expectedSortedEventList.add(editedAlice);
        assertEquals(expectedSortedEventList, sortedEventList);
    }

    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sortedEventList.add(null));
    }

    @Test
    public void setEvent_nullTargetEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sortedEventList.setEvent(null, ALICE));
    }

    @Test
    public void setEvent_nullEditedEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sortedEventList.setEvent(ALICE, null));
    }

    @Test
    public void setEvent_targetEventNotInList_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> sortedEventList.setEvent(ALICE, ALICE));
    }

    @Test
    public void setEvent_editedEventIsSameEvent_success() {
        sortedEventList.add(ALICE);
        sortedEventList.setEvent(ALICE, ALICE);
        SortedEventList expectedSortedEventList = new SortedEventList();
        expectedSortedEventList.add(ALICE);
        assertEquals(expectedSortedEventList, sortedEventList);
    }

    @Test
    public void setEvent_editedEventHasSameIdentity_success() {
        sortedEventList.add(ALICE);
        Event editedAlice = new EventBuilder(ALICE).withLocation(VALID_LOCATION_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        sortedEventList.setEvent(ALICE, editedAlice);
        SortedEventList expectedSortedEventList = new SortedEventList();
        expectedSortedEventList.add(editedAlice);
        assertEquals(expectedSortedEventList, sortedEventList);
    }

    @Test
    public void setEvent_editedEventHasDifferentIdentity_success() {
        sortedEventList.add(ALICE);
        sortedEventList.setEvent(ALICE, BOB);
        SortedEventList expectedSortedEventList = new SortedEventList();
        expectedSortedEventList.add(BOB);
        assertEquals(expectedSortedEventList, sortedEventList);
    }

    @Test
    public void remove_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sortedEventList.remove(null));
    }

    @Test
    public void remove_eventDoesNotExist_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> sortedEventList.remove(ALICE));
    }

    @Test
    public void remove_existingEvent_removesEvent() {
        sortedEventList.add(ALICE);
        sortedEventList.remove(ALICE);
        SortedEventList expectedSortedEventList = new SortedEventList();
        assertEquals(expectedSortedEventList, sortedEventList);
    }

    @Test
    public void setEvent_nullUniqueEventList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sortedEventList.setEvent((SortedEventList) null));
    }

    @Test
    public void setEvent_uniqueEventList_replacesOwnListWithProvidedUniqueEventList() {
        sortedEventList.add(ALICE);
        SortedEventList expectedSortedEventList = new SortedEventList();
        expectedSortedEventList.add(BOB);
        sortedEventList.setEvent(expectedSortedEventList);
        assertEquals(expectedSortedEventList, sortedEventList);
    }

    @Test
    public void setEvent_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sortedEventList.setEvent((List<Event>) null));
    }

    @Test
    public void setEvent_list_replacesOwnListWithProvidedList() {
        sortedEventList.add(ALICE);
        List<Event> eventList = Collections.singletonList(BOB);
        sortedEventList.setEvent(eventList);
        SortedEventList expectedSortedEventList = new SortedEventList();
        expectedSortedEventList.add(BOB);
        assertEquals(expectedSortedEventList, sortedEventList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> sortedEventList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void isOverlappingWith() {
        sortedEventList.add(ALICE);
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

        assertFalse(sortedEventList.isOverlappingWith(noOverlappingDateNoOverlappingSlot));
        assertFalse(sortedEventList.isOverlappingWith(noOverlappingDateHasOverlappingSlot));
        assertFalse(sortedEventList.isOverlappingWith(overlappingDateNoOverlappingSlot));
        assertTrue(sortedEventList.isOverlappingWith(overlappingDateHasOverlappingSlot1));
        assertTrue(sortedEventList.isOverlappingWith(overlappingDateHasOverlappingSlot2));
        assertTrue(sortedEventList.isOverlappingWith(overlappingDateHasOverlappingSlot3));
        assertTrue(sortedEventList.isOverlappingWith(overlappingDateHasOverlappingSlot4));
    }
}
