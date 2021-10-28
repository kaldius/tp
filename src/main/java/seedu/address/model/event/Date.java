package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event's date number in the schedule.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final Date TODAY = new Date(LocalDate.now());
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should only contain numbers, and it should be in the form yyyy-mm-dd";
    public static final String VALIDATION_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
    public final LocalDate date;
    public final String value;



    /**
     * Constructs a {@code Date}.
     *
     * @param date A date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
        this.value = date;
    }

    /**
     * Construct a Date instance on the given date
     * @param date date
     */
    public Date(LocalDate date) {
        requireNonNull(date);
        this.date = date;
        this.value = date.format(DateTimeFormatter.ISO_DATE);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Compares this Date instance with another Date instance.
     * @param other Other Date to compare to.
     * @return A positive integer if this Date is earlier than the other, a negative integer if this Date
     * is later than the other and zero if the 2 Dates share the same date.
     */
    public int compareTo(Date other) {
        return date.compareTo(other.date);
    }

    /**
     * Checks if this Date instance has the same date as another Date instance.
     * @param other Other Date to compare to.
     * @return True if has same date, false otherwise.
     */
    public boolean hasSameDate(Date other) {
        if (this.compareTo(other) == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
