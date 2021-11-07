package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
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
import seedu.address.testutil.BlockedSlotBuilder;

public class AddBlockedSlotCommandTest {

    @Test
    public void constructor_nullBlockedSlot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBlockedSlotCommand(null));
    }

    @Test
    public void execute_blockedSlotAcceptedByModel_addSuccessful() throws Exception {
        AddBlockedSlotCommandTest.ModelStubAcceptingBlockedSlotAdded modelStub =
                new AddBlockedSlotCommandTest.ModelStubAcceptingBlockedSlotAdded();
        BlockedSlot validBlockedSlot = new BlockedSlotBuilder().build();

        CommandResult commandResult = new AddBlockedSlotCommand(validBlockedSlot).execute(modelStub);

        assertEquals(String.format(AddBlockedSlotCommand.MESSAGE_SUCCESS, validBlockedSlot),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBlockedSlot), modelStub.blockedSlotsAdded);
    }

    @Test
    public void equals() {
        BlockedSlot slotA = new BlockedSlotBuilder().withDate("2021-01-01").build();
        BlockedSlot slotB = new BlockedSlotBuilder().withDate("2021-01-02").build();
        AddBlockedSlotCommand addSlotACommand = new AddBlockedSlotCommand(slotA);
        AddBlockedSlotCommand addSlotBCommand = new AddBlockedSlotCommand(slotB);

        // same object -> returns true
        assertTrue(addSlotACommand.equals(addSlotACommand));

        // same values -> returns true
        AddBlockedSlotCommand addSlotACommandCopy = new AddBlockedSlotCommand(slotA);
        assertTrue(addSlotACommand.equals(addSlotACommandCopy));

        // different types -> returns false
        assertFalse(addSlotACommand.equals(1));

        // null -> returns false
        assertFalse(addSlotACommandCopy.equals(null));

        // different event -> returns false
        assertFalse(addSlotACommand.equals(addSlotBCommand));
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
            //Always returns false so that any BlockedSlot can always be added.
            return false;
        }

        @Override
        public boolean isBlockedByBlockedSlot(Overlappable overlappable, Overlappable excluding) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isBlockedByEvent(Overlappable overlappable) {
            //This method needs to be called.
            //Always returns false so that any BlockedSlot can always be added.
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
        public ArrayList<FreeSlot> getFreeSlots(Date date) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single blocked slot.
     */
    private class ModelStubWithBlockedSlot extends AddBlockedSlotCommandTest.ModelStub {
        private final BlockedSlot blockedSlot;

        ModelStubWithBlockedSlot(BlockedSlot blockedSlot) {
            requireNonNull(blockedSlot);
            this.blockedSlot = blockedSlot;
        }
    }

    /**
     * A Model stub that always accept the blocked slot being added.
     */
    private class ModelStubAcceptingBlockedSlotAdded extends AddBlockedSlotCommandTest.ModelStub {
        final ArrayList<BlockedSlot> blockedSlotsAdded = new ArrayList<>();

        @Override
        public void addBlockedSlot(BlockedSlot blockedSlot) {
            requireNonNull(blockedSlot);
            blockedSlotsAdded.add(blockedSlot);
        }

        @Override
        public ReadOnlySchedule getSchedule() {
            return new Schedule();
        }
    }
}
