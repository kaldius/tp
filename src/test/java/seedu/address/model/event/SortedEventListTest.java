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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateEventException;
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
    public void contains_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sortedEventList.contains(null));
    }

    @Test
    public void contains_eventNotInList_returnsFalse() {
        assertFalse(sortedEventList.contains(ALICE));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        sortedEventList.add(ALICE);
        assertTrue(sortedEventList.contains(ALICE));
    }

    @Test
    public void contains_eventWithSameIdentityFieldsInList_returnsTrue() {
        sortedEventList.add(ALICE);
        Event editedAlice = new EventBuilder(ALICE).withLocation(VALID_LOCATION_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(sortedEventList.contains(editedAlice));
    }

    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sortedEventList.add(null));
    }

    @Test
    public void add_duplicateEvent_throwsDuplicateEventException() {
        sortedEventList.add(ALICE);
        assertThrows(DuplicateEventException.class, () -> sortedEventList.add(ALICE));
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
    public void setEvent_editedEventHasNonUniqueIdentity_throwsDuplicateEventException() {
        sortedEventList.add(ALICE);
        sortedEventList.add(BOB);
        assertThrows(DuplicateEventException.class, () -> sortedEventList.setEvent(ALICE, BOB));
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
    public void setEvent_listWithDuplicateEvents_throwsDuplicateEventException() {
        List<Event> listWithDuplicateEvents = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateEventException.class, () -> sortedEventList.setEvent(listWithDuplicateEvents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> sortedEventList.asUnmodifiableObservableList().remove(0));
    }
}
