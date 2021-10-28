package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventContainsTodaysDatePredicateTest {

    @Test
    public void equals() {
        EventContainsTodaysDatePredicate firstPredicate = new EventContainsTodaysDatePredicate();
        EventContainsTodaysDatePredicate secondPredicate = new EventContainsTodaysDatePredicate();
        EventContainsTodaysDatePredicate thirdPredicate = new EventContainsTodaysDatePredicate(new Date("2021-10-26"));
        EventContainsTodaysDatePredicate fourthPredicate = new EventContainsTodaysDatePredicate(new Date("2021-10-27"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // two objects containing same date -> returns true
        assertTrue(firstPredicate.equals(secondPredicate));


        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different dates -> returns false
        assertFalse(thirdPredicate.equals(fourthPredicate));
    }
}
