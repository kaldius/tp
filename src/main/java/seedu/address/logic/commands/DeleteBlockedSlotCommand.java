package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.blockedslot.BlockedSlot;

/**
 * Deletes a blocked slot identified using it's displayed index from the schedule.
 */
public class DeleteBlockedSlotCommand extends Command {

    public static final String COMMAND_WORD = "delete_blocked";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the blocked slot identified by the index number used in the displayed blocked slot list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BLOCKED_SLOT_SUCCESS = "Deleted Blocked Slot: %1$s";

    private final Index targetIndex;

    public DeleteBlockedSlotCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<BlockedSlot> lastShownList = model.getFilteredBlockedSlotList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        BlockedSlot blockedSlotToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteBlockedSlot(blockedSlotToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BLOCKED_SLOT_SUCCESS, blockedSlotToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBlockedSlotCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteBlockedSlotCommand) other).targetIndex)); // state check
    }
}
