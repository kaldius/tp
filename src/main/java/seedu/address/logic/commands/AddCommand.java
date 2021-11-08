package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.SlotBlockedException;
import seedu.address.model.Model;
import seedu.address.model.blockedslot.BlockedSlot;
import seedu.address.model.event.Event;

/**
 * Adds an event to the schedule.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the schedule. " + "\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIMESLOT + "TIMESLOT "
            + PREFIX_LOCATION + "LOCATION "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_REMARK + "REMARK]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Jacob Ng "
            + PREFIX_DATE + "2021-11-13 "
            + PREFIX_TIMESLOT + "1300-1400 "
            + PREFIX_LOCATION + "The Deck "
            + PREFIX_TAG + "CS1231S "
            + PREFIX_TAG + "URGENT "
            + PREFIX_REMARK + "Wants to review Tutorial 4";


    public static final String MESSAGE_SUCCESS = "New event added: %1$s";

    private final Event toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Event}
     */
    public AddCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isBlockedByBlockedSlot(toAdd)) {
            throw new SlotBlockedException(BlockedSlot.SLOT_BLOCKED);
        } else if (model.isBlockedByEvent(toAdd)) {
            throw new SlotBlockedException(Event.SLOT_BLOCKED);
        }
        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
