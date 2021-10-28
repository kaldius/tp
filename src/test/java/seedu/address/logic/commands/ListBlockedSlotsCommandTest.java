package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalBlockedSlots;
import static seedu.address.testutil.TypicalEvents.getTypicalSchedule;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Schedule;
import seedu.address.model.UserPrefs;
import seedu.address.model.blockedslot.BlockedSlot;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListBlockedSlotsCommand.
 */
public class ListBlockedSlotsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSchedule(), new UserPrefs());
        expectedModel = new ModelManager(model.getSchedule(), new UserPrefs());
    }

    @Test
    public void execute_noBlockedSlots_showsEmptyMessage() {
        model = new ModelManager(new Schedule(), new UserPrefs());
        expectedModel = new ModelManager(model.getSchedule(), new UserPrefs());
        assertCommandSuccess(new ListBlockedSlotsCommand(),
                model,
                ListBlockedSlotsCommand.MESSAGE_SUCCESS_EMPTY,
                expectedModel);
    }

    @Test
    public void execute_typicalBlockedSlots_showsNonEmptyMessageWithList() {
        String expectedList = "";
        List<BlockedSlot> typicalBlockedSlots = getTypicalBlockedSlots();
        for (int i = 0; i < typicalBlockedSlots.size(); i++) {
            expectedList += (i + 1) + ". " + typicalBlockedSlots.get(i);
        }
        assertCommandSuccess(new ListBlockedSlotsCommand(),
                model,
                String.format(ListBlockedSlotsCommand.MESSAGE_SUCCESS_NONEMPTY, expectedList),
                expectedModel);
    }
}
