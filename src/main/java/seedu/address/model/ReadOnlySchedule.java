package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.blockedslot.BlockedSlot;
import seedu.address.model.event.Event;

/**
 * Unmodifiable view of a schedule.
 */
public interface ReadOnlySchedule {

    /**
     * Returns an unmodifiable view of the events list.
     */
    ObservableList<Event> getEventList();

    /**
     * Returns an unmodifiable view of the blocked slots list.
     */
    ObservableList<BlockedSlot> getBlockedSlotList();

}
