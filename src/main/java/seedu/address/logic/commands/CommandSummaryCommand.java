package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Opens a pop-window to show a summary of all possible commands to the user.
 */
public class CommandSummaryCommand extends Command {
    public static final String COMMAND_WORD = "command_summary";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows summary of commands. \n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_COMMAND_SUMMARY_MESSAGE = "Opened command summary window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_COMMAND_SUMMARY_MESSAGE, false, true, false);
    }
}
