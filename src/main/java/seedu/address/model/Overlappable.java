package seedu.address.model;

import seedu.address.model.event.Date;
import seedu.address.model.event.TimeSlot;

/**
 * An Overlappable can overlap with another based on date and time.
 */
public interface Overlappable {

    /**
     * Checks if there is an overlap between the given Overlappable and this instance.
     * @param overlappable Other Overlappable to check for overlaps.
     * @return True if there is an overlap, false otherwise.
     */
    boolean isOverlappingWith(Overlappable overlappable);

    Date getDate();

    TimeSlot getTimeSlot();

}
