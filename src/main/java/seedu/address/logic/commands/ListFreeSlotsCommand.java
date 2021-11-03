package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FreeSlot;
import seedu.address.model.Model;
import seedu.address.model.event.Date;

public class ListFreeSlotsCommand extends Command {
    public static final String COMMAND_WORD = "list_free";

    public static final String MESSAGE_SUCCESS = "Here are your free slots:";

    private Date today;

    /**
     * Constructor for a FreeSlotsCommand.
     *
     * @param today Date instance for today
     */
    public ListFreeSlotsCommand(Date today) {
        requireNonNull(today);
        this.today = today;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ArrayList<FreeSlot> freeSlots = model.getFreeSlots(Date.TODAY);
        StringBuilder result = new StringBuilder(MESSAGE_SUCCESS);
        for (FreeSlot f: freeSlots) {
            result.append("\n" + f.toString());
        }
        return new CommandResult(result.toString());
    }
}
