package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_UPCOMING_EVENT_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.ALICE;
import static seedu.address.testutil.TypicalEvents.getTypicalSchedule;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Date;
import seedu.address.model.event.EventContainsTodaysDatePredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class UpcomingEventsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSchedule(), new UserPrefs());
        expectedModel = new ModelManager(model.getSchedule(), new UserPrefs());
    }

    @Test
    public void execute_upcomingEvents() {
        String expectedMessage = String.format(MESSAGE_UPCOMING_EVENT_LISTED_OVERVIEW, 1);
        EventContainsTodaysDatePredicateStub predicate = new EventContainsTodaysDatePredicateStub();
        UpcomingEventsCommand command = new UpcomingEventsCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredEventList());
    }
    @Test
    public void equals() {
        UpcomingEventsCommand firstCommand = new UpcomingEventsCommand();
        UpcomingEventsCommand secondCommand = new UpcomingEventsCommand();
        EventContainsTodaysDatePredicate firstPredicate =
                new EventContainsTodaysDatePredicate(new Date("2021-10-26"));
        EventContainsTodaysDatePredicate secondPredicate =
                new EventContainsTodaysDatePredicate(new Date("2021-10-27"));
        UpcomingEventsCommand thirdCommand = new UpcomingEventsCommand(firstPredicate);
        UpcomingEventsCommand fourthCommand = new UpcomingEventsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // two objects containing same date -> returns true
        assertTrue(firstCommand.equals(secondCommand));


        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different dates -> returns false
        assertFalse(thirdCommand.equals(fourthCommand));
    }

    /**
     * An EventContainsTodaysDatePredicate stub that always stores the date 2020-01-01.
     */
    private class EventContainsTodaysDatePredicateStub extends EventContainsTodaysDatePredicate {
        public EventContainsTodaysDatePredicateStub() {
            super(new Date("2020-01-01"));
        }
    }

}
