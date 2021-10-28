package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.getTypicalSchedule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.SlotBlockedException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSchedule(), new UserPrefs());
    }

    @Test
    public void execute_newEvent_success() {
        Event validEvent = new EventBuilder().withDate("2020-01-01").withTimeSlot("1000", "1100").build();

        Model expectedModel = new ModelManager(model.getSchedule(), new UserPrefs());
        expectedModel.addEvent(validEvent);

        assertCommandSuccess(new AddCommand(validEvent), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validEvent), expectedModel);
    }

    @Test
    public void execute_eventClashesWithBlockedSlot_throwsSlotBlockedException() {
        Model model = new ModelManager(getTypicalSchedule(), new UserPrefs());
        Event clashingEvent = new EventBuilder().withDate("2020-02-06").withTimeSlot("1130", "1230").build();

        assertThrows(SlotBlockedException.class, () -> new AddCommand(clashingEvent).execute(model));
    }

    @Test
    public void execute_eventClashesWithEvent_throwsSlotBlockedException() {
        Model model = new ModelManager(getTypicalSchedule(), new UserPrefs());
        Event clashingEvent = new EventBuilder().withDate("2020-01-01").withTimeSlot("0830", "0930").build();

        assertThrows(SlotBlockedException.class, () -> new AddCommand(clashingEvent).execute(model));
    }

}
