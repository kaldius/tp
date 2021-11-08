package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE;
import static seedu.address.testutil.TypicalEvents.BENSON;
import static seedu.address.testutil.TypicalEvents.getTypicalBlockedSlots;
import static seedu.address.testutil.TypicalEvents.getTypicalEvents;
import static seedu.address.testutil.TypicalEvents.getTypicalSchedule;

import java.time.LocalTime;
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
    public void addEmptyDates() {
        ArrayList<FreeSlot> sameStartAndEnd = new ArrayList<>();
        schedule.addEmptyDates(sameStartAndEnd, ALICE.getDate(), ALICE.getDate());
        assertTrue(sameStartAndEnd.isEmpty());

        ArrayList<FreeSlot> startBeforeEnd = new ArrayList<>();
        schedule.addEmptyDates(startBeforeEnd, ALICE.getDate(), BENSON.getDate());
        assertTrue(startBeforeEnd.size() == 1);

        ArrayList<FreeSlot> startAfterEnd = new ArrayList<>();
        schedule.addEmptyDates(startAfterEnd, BENSON.getDate(), ALICE.getDate());
        assertTrue(startAfterEnd.isEmpty());
    }

    @Test
    public void getFreeSlot_emptySchedule_emptyList() {
        Schedule empty = new Schedule();
        assertEquals(new ArrayList<>(), empty.getFreeSlots(new Date("2020-01-01"), LocalTime.of(0, 0)));
    }

    @Test
    public void getFreeSlot_scheduleWithSingleEvent() {
        Schedule schedule = new Schedule();
        schedule.addEvent(BENSON);
        ArrayList<FreeSlot> freeSlotsSameDay =
                schedule.getFreeSlots(new Date("2020-01-02"), LocalTime.of(0, 0));
        assertTrue(freeSlotsSameDay.size() == 2);

        ArrayList<FreeSlot> freeSlotsDayBefore =
                schedule.getFreeSlots(new Date("2020-01-01"), LocalTime.of(0, 0));
        assertTrue(freeSlotsDayBefore.size() == 3);

        ArrayList<FreeSlot> freeSlotsDayAfter =
                schedule.getFreeSlots(new Date("2020-01-03"), LocalTime.of(0, 0));
        assertTrue(freeSlotsDayAfter.size() == 0);

    }

    @Test
    public void getFreeSlot_nonEmptySchedule_nonEmptyList() {
        Schedule schedule = getTypicalSchedule();
        ArrayList<FreeSlot> freeSlots =
                schedule.getFreeSlots(new Date("2020-01-01"), LocalTime.of(0, 0));
        assertFalse(freeSlots.isEmpty());
        for (FreeSlot f: freeSlots) {
            for (Event e: getTypicalEvents()) {
                assertFalse(f.isOverlappingWith(e));
            }
            for (BlockedSlot b: getTypicalBlockedSlots()) {
                assertFalse(f.isOverlappingWith(b));
            }
        }
    }

    @Test
    public void getFreeSlot_backToBackEvents_nonEmptyList() {
        Schedule schedule = new Schedule();
        for (BlockedSlot b: getTypicalBlockedSlots()) {
            schedule.addBlockedSlot(b);
        }
        ArrayList<FreeSlot> freeSlots = schedule.getFreeSlots(new Date("2020-01-01"), LocalTime.of(0, 0));
        for (FreeSlot f: freeSlots) {
            for (BlockedSlot b: getTypicalBlockedSlots()) {
                assertFalse(f.isOverlappingWith(b));
            }
        }
    }

    @Test
    public void getFreeSlot_noBackToBackEvents_nonEmptyList() {
        Schedule schedule = new Schedule();
        for (Event e: getTypicalEvents()) {
            schedule.addEvent(e);
        }
        ArrayList<FreeSlot> freeSlots = schedule.getFreeSlots(new Date("2020-01-01"), LocalTime.of(0, 0));
        for (FreeSlot f: freeSlots) {
            for (Event e: getTypicalEvents()) {
                assertFalse(f.isOverlappingWith(e));
            }
        }
    }

    @Test
    public void getFreeSlot_eventsBeforeStartDate_ignorePastEvents() {
        Schedule scheduleWithSomePastEvents = getTypicalSchedule();
        ArrayList<FreeSlot> freeSlots1 = scheduleWithSomePastEvents
                .getFreeSlots(new Date("2020-01-03"), LocalTime.of(12, 0));
        Schedule scheduleWithoutSomePastEvents = getTypicalSchedule();
        scheduleWithoutSomePastEvents.removeEvent(ALICE);
        scheduleWithoutSomePastEvents.removeEvent(BENSON);
        ArrayList<FreeSlot> freeSlots2 = scheduleWithoutSomePastEvents
                .getFreeSlots(new Date("2020-01-03"), LocalTime.of(12, 0));
        assertEquals(freeSlots2, freeSlots1);

        Schedule scheduleWithAllPastEvents = getTypicalSchedule();
        ArrayList<FreeSlot> freeSlots3 = scheduleWithAllPastEvents
                .getFreeSlots(new Date("2020-10-10"), LocalTime.of(0, 0));
        Schedule scheduleWithoutAllPastEvents = new Schedule();
        ArrayList<FreeSlot> freeSlots4 = scheduleWithoutAllPastEvents
                .getFreeSlots(new Date("2020-10-10"), LocalTime.of(0, 0));
        assertEquals(freeSlots4, freeSlots3);
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
