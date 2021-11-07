package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.event.Date;
import seedu.address.model.event.TimeSlot;

public class FreeSlot implements Overlappable {

    private Date date;
    private TimeSlot timeSlot;

    /**
     * Constructs a FreeSlot instance
     *
     * @param date Date of free slot
     * @param timeSlot time of free slot
     */
    public FreeSlot(Date date, TimeSlot timeSlot) {
        requireNonNull(date);
        requireNonNull(timeSlot);
        this.date = date;
        this.timeSlot = timeSlot;
    }

    @Override
    public boolean isOverlappingWith(Overlappable o) {
        if (this.date.equals(o.getDate())) {
            return this.timeSlot.isOverlappingWith(o.getTimeSlot());
        } else {
            return false;
        }
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        return date + ": " + timeSlot;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FreeSlot // instanceof handles nulls
                && date.equals(((FreeSlot) other).date)
                && timeSlot.equals(((FreeSlot) other).timeSlot)); // state check for both fields
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, timeSlot);
    }
}
