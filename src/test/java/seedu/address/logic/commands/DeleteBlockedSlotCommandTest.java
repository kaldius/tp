package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalSchedule;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.blockedslot.BlockedSlot;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteBlockedSlotCommandTest.
 */
public class DeleteBlockedSlotCommandTest {

    private Model model = new ModelManager(getTypicalSchedule(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        BlockedSlot blockedSlotToDelete = model.getFilteredBlockedSlotList().get(INDEX_FIRST_EVENT.getZeroBased());
        DeleteBlockedSlotCommand deleteBlockedSlotCommand = new DeleteBlockedSlotCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(DeleteBlockedSlotCommand.MESSAGE_DELETE_BLOCKED_SLOT_SUCCESS,
                blockedSlotToDelete);

        ModelManager expectedModel = new ModelManager(model.getSchedule(), new UserPrefs());
        expectedModel.deleteBlockedSlot(blockedSlotToDelete);

        assertCommandSuccess(deleteBlockedSlotCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBlockedSlotList().size() + 1);
        DeleteBlockedSlotCommand deleteBlockedSlotCommand = new DeleteBlockedSlotCommand(outOfBoundIndex);

        assertCommandFailure(deleteBlockedSlotCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteBlockedSlotCommand deleteFirstBlockedSlotCommand = new DeleteBlockedSlotCommand(INDEX_FIRST_EVENT);
        DeleteBlockedSlotCommand deleteSecondBlockedSlotCommand = new DeleteBlockedSlotCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(deleteFirstBlockedSlotCommand.equals(deleteFirstBlockedSlotCommand));

        // same values -> returns true
        DeleteBlockedSlotCommand deleteFirstBlockedSlotCommandCopy = new DeleteBlockedSlotCommand(INDEX_FIRST_EVENT);
        assertTrue(deleteFirstBlockedSlotCommand.equals(deleteFirstBlockedSlotCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstBlockedSlotCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstBlockedSlotCommand.equals(null));

        // different event -> returns false
        assertFalse(deleteFirstBlockedSlotCommand.equals(deleteSecondBlockedSlotCommand));
    }

}
