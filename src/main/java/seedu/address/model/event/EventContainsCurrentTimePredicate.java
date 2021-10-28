package seedu.address.model.event;

import java.time.LocalTime;
import java.util.function.Predicate;

/**
 * Tests that an {@code Event}'s {@code Timeslot} is after current time.
 */
public class EventContainsCurrentTimePredicate implements Predicate<Event> {
    private final LocalTime time;
    private final Date today;

    /**
     * Constructs a {@code EventContainsCurrentTimePredicate}
     */
    public EventContainsCurrentTimePredicate() {
        this.time = LocalTime.now();
        this.today = Date.TODAY;
    }
    /**
     * Constructs a {@code EventContainsCurrentTimePredicate} with {@code LocalTime}
     * and {@code Date} arguments
     */
    public EventContainsCurrentTimePredicate(LocalTime time, Date date) {
        this.time = time;
        this.today = date;
    }
    @Override
    public boolean test(Event event) {
        return event.getTimeSlot().startTime.compareTo(time) > 0 && event.getDate().equals(today);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventContainsCurrentTimePredicate // instanceof handles nulls
                && time.equals(((EventContainsCurrentTimePredicate) other).time)); // state check
    }

}
