package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.getTypicalEvents;
import static seedu.address.testutil.TypicalEvents.getTypicalSchedule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.blockedslot.BlockedSlot;
import seedu.address.model.event.Date;
import seedu.address.model.event.Event;

public class ScheduleTest {

    private final Schedule schedule = new Schedule();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), schedule.getEventList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySchedule_replacesData() {
        Schedule newData = getTypicalSchedule();
        schedule.resetData(newData);
        assertEquals(newData, schedule);
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> schedule.getEventList().remove(0));
    }

    @Test
    public void emptySchedule_getFreeSlot_emptyList() {
        Schedule empty = new Schedule();
        assertEquals(new ArrayList<>(), empty.getFreeSlots(new Date("2020-01-01")));
    }

    @Test
    public void nonEmptySchedule_getFreeSlot_nonEmptyList() {
        Schedule schedule = getTypicalSchedule();
        ArrayList<FreeSlot> freeSlots = schedule.getFreeSlots(new Date("2020-01-01"));
        for (FreeSlot f: freeSlots) {
            for (Event e: getTypicalEvents()) {
                assertTrue(!f.isOverlappingWith(e));
            }
        }
    }

    /**
     * A stub ReadOnlySchedule whose events list can violate interface constraints.
     */
    private static class ScheduleStub implements ReadOnlySchedule {
        private final ObservableList<Event> events = FXCollections.observableArrayList();
        private final ObservableList<BlockedSlot> blockedSlots = FXCollections.observableArrayList();

        ScheduleStub(Collection<Event> events) {
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }

        @Override
        public ObservableList<BlockedSlot> getBlockedSlotList() {
            return blockedSlots;
        }
    }

}
