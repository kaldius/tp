package seedu.address.model.blockedslot;


import static java.util.Objects.requireNonNull;

import seedu.address.model.Overlappable;
import seedu.address.model.event.Date;
import seedu.address.model.event.TimeSlot;

/**
 * Represents a blocked period of time which an Event should not overlap with.
 */
public class BlockedSlot implements Overlappable {

    public static final String SLOT_BLOCKED = "This slot coincides with a blocked period.";

    private final Date date;
    private final TimeSlot timeSlot;

    /**
     * Constructs a BlockedSlot.
     * @param date Date to block.
     * @param timeSlot TimeSlot to block.
     */
    public BlockedSlot(Date date, TimeSlot timeSlot) {
        requireNonNull(date);
        requireNonNull(timeSlot);
        this.date = date;
        this.timeSlot = timeSlot;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public boolean hasSameDate(Overlappable overlappable) {
        return this.date.hasSameDate(overlappable.getDate());
    }

    /**
     * Checks if this BlockedSlot overlaps with another Overlappable.
     * @param overlappable Other Overlappable to check for overlaps.
     * @return True if overlapping, false otherwise.
     */
    @Override
    public boolean isOverlappingWith(Overlappable overlappable) {
        boolean hasSameDate = this.hasSameDate(overlappable);
        boolean isTimeSlotOverlapping = this.timeSlot
                .isOverlappingWith(overlappable.getTimeSlot());

        return hasSameDate && isTimeSlotOverlapping;
    }

    /**
     * Compares this BlockedSlot instance with another BlockedSlot instance.
     *
     * @param other other BlockedSlot to compare to.
     * @return a positive integer if this BlockedSlot's Date and TimeSlot are chronologically before other BlockedSlot,
     * a negative integer if this BlockedSlot's Date and TimeSlot are chronologically after other BlockedSlot,
     * zero if both BlockedSlots are on the same date and start at the same time.
     */
    public int compareTo(BlockedSlot other) {
        int compareDate = date.compareTo(other.date);
        if (compareDate != 0) {
            return compareDate;
        } else {
            return timeSlot.compareTo(other.timeSlot);
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Date: ")
                .append(getDate())
                .append("; TimeSlot: ")
                .append(getTimeSlot())
                .append(";\n");

        return builder.toString();
    }
}
