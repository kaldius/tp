package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.FreeSlot;
import seedu.address.model.Model;
import seedu.address.model.Overlappable;
import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Schedule;
import seedu.address.model.blockedslot.BlockedSlot;
import seedu.address.model.event.Date;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new AddCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void equals() {
        Event alice = new EventBuilder().withName("Alice").build();
        Event bob = new EventBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different event -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing, except isBlockedByBlockedSlot and isBlockedByEvent.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getScheduleFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setScheduleFilePath(Path scheduleFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSchedule(ReadOnlySchedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySchedule getSchedule() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBlockedSlot(BlockedSlot blockedSlot) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBlockedSlot(BlockedSlot blockedSlot) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isBlockedByBlockedSlot(Overlappable overlappable) {
            //This method needs to be called.
            //Always returns false so that any Event can always be added.
            return false;
        }

        @Override
        public boolean isBlockedByBlockedSlot(Overlappable overlappable, Overlappable excluding) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isBlockedByEvent(Overlappable overlappable) {
            //This method needs to be called.
            //Always returns false so that any Event can always be added.
            return false;
        }

        @Override
        public boolean isBlockedByEvent(Overlappable overlappable, Overlappable excluding) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<BlockedSlot> getFilteredBlockedSlotList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBlockedSlotList(Predicate<BlockedSlot> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String filteredBlockedSlotListToString() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Event nextEventInTheList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<FreeSlot> getFreeSlots(Date date, LocalTime now) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single event.
     */
    private class ModelStubWithEvent extends ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }
    }

    /**
     * A Model stub that always accept the event being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public ReadOnlySchedule getSchedule() {
            return new Schedule();
        }
    }

}
