package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists out all the blocked slots in the schedule.
 */
public class ListBlockedSlotsCommand extends Command {

    public static final String COMMAND_WORD = "list_blocked";
    public static final String MESSAGE_SUCCESS_NONEMPTY = "Here are all your blocked slots:\n%1$s";
    public static final String MESSAGE_SUCCESS_EMPTY = "You have not blocked any slots.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String blockedSlots = model.filteredBlockedSlotListToString();
        String successMessage;
        if (blockedSlots.equals("")) {
            successMessage = MESSAGE_SUCCESS_EMPTY;
        } else {
            successMessage = MESSAGE_SUCCESS_NONEMPTY;
        }
        return new CommandResult(String.format(successMessage, blockedSlots));
    }
}
