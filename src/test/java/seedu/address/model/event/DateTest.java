package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date((String) null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date number
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date numbers
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("2020-01")); // only yyyy-mm
        assertFalse(Date.isValidDate("date")); // non-numeric
        assertFalse(Date.isValidDate("2020a-01-01")); // alphabets within digits
        assertFalse(Date.isValidDate("2020 01 01")); // spaces instead of hyphens

        // valid date numbers
        assertTrue(Date.isValidDate("2020-01-01"));
        assertTrue(Date.isValidDate("2024-02-29")); // leap year
    }

    @Test
    public void compareTo() {
        Date earlierDate = new Date("2020-01-01");
        Date sameAsEarlierDate = new Date("2020-01-01");
        Date laterDate = new Date("2020-01-02");
        assertTrue(earlierDate.compareTo(laterDate) < 0);
        assertTrue(earlierDate.compareTo(sameAsEarlierDate) == 0);
    }

    @Test void hasSameDate() {
        Date earlierDate = new Date("2020-01-01");
        Date sameAsEarlierDate = new Date("2020-01-01");
        Date laterDate = new Date("2020-01-02");
        assertTrue(earlierDate.hasSameDate(sameAsEarlierDate));
        assertFalse(earlierDate.hasSameDate(laterDate));
    }

}
