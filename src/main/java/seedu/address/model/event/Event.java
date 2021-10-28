package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.Overlappable;
import seedu.address.model.tag.Tag;

/**
 * Represents an Event in the schedule.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event implements Overlappable {

    // Identity fields
    private final Name name;
    private final Date date;
    private final TimeSlot timeSlot;

    // Data fields
    private final Location location;
    private final Set<Tag> tags = new HashSet<>();
    private final Remark remark;

    /**
     * Every field must be present and not null.
     */
    public Event(Name name, Date date, TimeSlot timeSlot, Location location, Set<Tag> tags, Remark remark) {
        requireAllNonNull(name, date, timeSlot, location, tags, remark);
        this.name = name;
        this.date = date;
        this.timeSlot = timeSlot;
        this.location = location;
        this.tags.addAll(tags);
        this.remark = remark;
    }

    public Name getName() {
        return name;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Location getLocation() {
        return location;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Compares this Event instance with another Event instance.
     *
     * @param other other Event to compare to.
     * @return a positive integer if this Event's Date and timeSlot are chronologically before other Event,
     * a negative integer if this Event's Date and timeSlot are chronologically after other Event,
     * zero if both Events are on the same date and start at the same time.
     */
    public int compareTo(Event other) {
        int compareDate = date.compareTo(other.date);
        if (compareDate != 0) {
            return compareDate;
        } else {
            return timeSlot.compareTo(other.timeSlot);
        }
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getName().equals(getName())
                && otherEvent.getDate().equals(getDate())
                && otherEvent.getTimeSlot().equals(getTimeSlot())
                && otherEvent.getLocation().equals(getLocation())
                && otherEvent.getTags().equals(getTags())
                && otherEvent.getRemark().equals(getRemark());

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, timeSlot, location, tags, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Date: ")
                .append(getDate())
                .append("; TimeSlot: ")
                .append(getTimeSlot())
                .append("; Location: ")
                .append(getLocation());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append("; Remark: ").append(getRemark());

        return builder.toString();
    }

    public boolean hasSameDate(Overlappable overlappable) {
        return this.date.hasSameDate(overlappable.getDate());
    }

    /**
     * Checks if this Event overlaps with another Overlappable.
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
}
