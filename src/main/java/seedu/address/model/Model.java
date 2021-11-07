package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.SlotBlockedException;
import seedu.address.model.blockedslot.BlockedSlot;
import seedu.address.model.event.Date;
import seedu.address.model.event.Event;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;
    Predicate<BlockedSlot> PREDICATE_SHOW_ALL_BLOCKED_SLOTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' schedule file path.
     */
    Path getScheduleFilePath();

    /**
     * Sets the user prefs' schedule file path.
     */
    void setScheduleFilePath(Path scheduleFilePath);

    /**
     * Replaces schedule data with the data in {@code schedule}.
     */
    void setSchedule(ReadOnlySchedule schedule);

    /** Returns the Schedule */
    ReadOnlySchedule getSchedule();

    /**
     * Deletes the given event.
     * The event must exist in the schedule.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the schedule.
     * @throws SlotBlockedException if the Date and TimeSlot of the Event to add coincides with the blocked period.
     */
    void addEvent(Event event);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the schedule.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the schedule.
     */
    void setEvent(Event target, Event editedEvent);

    /**
     * Adds the given BlockedSlot.
     */
    void addBlockedSlot(BlockedSlot blockedSlot);

    /**
     * Deletes the given blocked slot.
     * @param blockedSlot BlockedSlot to delete.
     */
    void deleteBlockedSlot(BlockedSlot blockedSlot);

    /**
     * Checks if the given Overlappable is blocked by a BlockedSlot.
     * @param overlappable the Overlappable to be checked.
     * @return true if the Overlappable is blocked, false otherwise.
     */
    boolean isBlockedByBlockedSlot(Overlappable overlappable);

    /**
     * Checks if the given Overlappable is blocked by a BlockedSlot other than the given one.
     * @param overlappable the Overlappable to be checked.
     * @param excluding the BlockedSlot to exclude from the check.
     * @return true if the Overlappable is blocked, false otherwise.
     */
    boolean isBlockedByBlockedSlot(Overlappable overlappable, Overlappable excluding);

    /**
     * Checks if the given Overlappable is blocked by an Event.
     * @param overlappable the Overlappable to be checked.
     * @return true if the Overlappable is blocked, false otherwise.
     */
    boolean isBlockedByEvent(Overlappable overlappable);

    /**
     * Checks if the given Overlappable is blocked by an Event other than the given one.
     * @param overlappable the Overlappable to be checked.
     * @param excluding the Event to exclude from the check.
     * @return true if the Overlappable is blocked, false otherwise.
     */
    boolean isBlockedByEvent(Overlappable overlappable, Overlappable excluding);

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /** Returns an unmodifiable view of the filtered blocked slot list */
    ObservableList<BlockedSlot> getFilteredBlockedSlotList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    /**
     * Updates the filter of the filtered blocked slot list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBlockedSlotList(Predicate<BlockedSlot> predicate);

    /** Returns the BlockedSlot list in String format */
    String filteredBlockedSlotListToString();

    /**
     * Gets the first event in the filtered list.
     */
    Event nextEventInTheList();

    /**
     * Gets a list of all free slots from today's date to last event/block slot.
     *
     * @param date Today's date
     * @return ArrayList of freeSlots
     */
    ArrayList<FreeSlot> getFreeSlots(Date date);
}
