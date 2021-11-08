package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.SlotBlockedException;
import seedu.address.model.Model;
import seedu.address.model.blockedslot.BlockedSlot;
import seedu.address.model.event.Event;

/**
 * Blocks out a time slot in the schedule.
 */
public class AddBlockedSlotCommand extends Command {

    public static final String COMMAND_WORD = "block";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Blocks a specified time slot in the schedule. "
            + "\n"
            + "Parameters: "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIMESLOT + "TIMESLOT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "2021-11-13 "
            + PREFIX_TIMESLOT + "1300-1400";

    public static final String MESSAGE_SUCCESS = "New slot blocked out: %1$s\n";

    private final BlockedSlot blockedSlot;

    /**
     * Creates a AddBlockedSlotCommand to block the specified BlockedSlot.
     * @param blockedSlot
     */
    public AddBlockedSlotCommand(BlockedSlot blockedSlot) {
        requireNonNull(blockedSlot);
        this.blockedSlot = blockedSlot;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //TODO: instead of throwing error, merge with other blocked periods
        if (model.isBlockedByBlockedSlot(blockedSlot)) {
            throw new SlotBlockedException(BlockedSlot.SLOT_BLOCKED);
        } else if (model.isBlockedByEvent(blockedSlot)) {
            throw new SlotBlockedException(Event.SLOT_BLOCKED);
        }
        model.addBlockedSlot(blockedSlot);
        return new CommandResult(String.format(MESSAGE_SUCCESS, blockedSlot));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBlockedSlotCommand // instanceof handles nulls
                && blockedSlot.equals(((AddBlockedSlotCommand) other).blockedSlot));
    }
}
