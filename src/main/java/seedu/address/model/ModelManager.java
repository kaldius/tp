package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.blockedslot.BlockedSlot;
import seedu.address.model.event.Date;
import seedu.address.model.event.Event;

/**
 * Represents the in-memory model of the schedule data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Schedule schedule;
    private final UserPrefs userPrefs;
    private final FilteredList<Event> filteredEvents;
    private final FilteredList<BlockedSlot> filteredBlockedSlots;

    /**
     * Initializes a ModelManager with the given schedule and userPrefs.
     */
    public ModelManager(ReadOnlySchedule schedule, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(schedule, userPrefs);

        logger.fine("Initializing with schedule: " + schedule + " and user prefs " + userPrefs);

        this.schedule = new Schedule(schedule);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredEvents = new FilteredList<>(this.schedule.getEventList());
        filteredBlockedSlots = new FilteredList<>(this.schedule.getBlockedSlotList());
    }

    public ModelManager() {
        this(new Schedule(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getScheduleFilePath() {
        return userPrefs.getScheduleFilePath();
    }

    @Override
    public void setScheduleFilePath(Path scheduleFilePath) {
        requireNonNull(scheduleFilePath);
        userPrefs.setScheduleFilePath(scheduleFilePath);
    }

    //=========== Schedule ================================================================================

    @Override
    public void setSchedule(ReadOnlySchedule schedule) {
        this.schedule.resetData(schedule);
    }

    @Override
    public ReadOnlySchedule getSchedule() {
        return schedule;
    }

    @Override
    public void deleteEvent(Event target) {
        schedule.removeEvent(target);
    }

    @Override
    public void addEvent(Event event) {
        schedule.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        schedule.setEvent(target, editedEvent);
    }

    @Override
    public void addBlockedSlot(BlockedSlot blockedSlot) {
        schedule.addBlockedSlot(blockedSlot);
        updateFilteredBlockedSlotList(PREDICATE_SHOW_ALL_BLOCKED_SLOTS);
    }

    @Override
    public void deleteBlockedSlot(BlockedSlot blockedSlot) {
        schedule.removeBlockedSlot(blockedSlot);
    }

    @Override
    public boolean isBlockedByBlockedSlot(Overlappable overlappable) {
        return schedule.isBlockedByBlockedSlot(overlappable);
    }

    @Override
    public boolean isBlockedByBlockedSlot(Overlappable overlappable, Overlappable excluding) {
        return schedule.isBlockedByBlockedSlot(overlappable, excluding);
    }

    @Override
    public boolean isBlockedByEvent(Overlappable overlappable) {
        return schedule.isBlockedByEvent(overlappable);
    }

    @Override
    public boolean isBlockedByEvent(Overlappable overlappable, Overlappable excluding) {
        return schedule.isBlockedByEvent(overlappable, excluding);
    }

    //=========== Filtered Event List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Event}s.
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    //=========== Filtered Blocked Slot List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code BlockedSlot}s.
     */
    @Override
    public ObservableList<BlockedSlot> getFilteredBlockedSlotList() {
        return filteredBlockedSlots;
    }

    @Override
    public void updateFilteredBlockedSlotList(Predicate<BlockedSlot> predicate) {
        requireNonNull(predicate);
        filteredBlockedSlots.setPredicate(predicate);
    }

    @Override
    public String filteredBlockedSlotListToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < filteredBlockedSlots.size(); i++) {
            builder.append((i + 1) + ". ")
                    .append(filteredBlockedSlots.get(i));
        }
        return builder.toString();
    }

    @Override
    public Event nextEventInTheList() {
        if (filteredEvents.isEmpty()) {
            return null;
        } else {
            return filteredEvents.get(0);
        }

    }

    @Override
    public ArrayList<FreeSlot> getFreeSlots(Date date, LocalTime now) {
        return schedule.getFreeSlots(date, now);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return schedule.equals(other.schedule)
                && userPrefs.equals(other.userPrefs)
                && filteredEvents.equals(other.filteredEvents)
                && filteredBlockedSlots.equals(other.filteredBlockedSlots);
    }



}
