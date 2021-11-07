package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteBlockedSlotCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteBlockedSlotCommand object
 */
public class DeleteBlockedSlotCommandParser implements Parser<DeleteBlockedSlotCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteBlockedSlotCommand
     * and returns a DeleteBlockedSlotCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteBlockedSlotCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteBlockedSlotCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBlockedSlotCommand.MESSAGE_USAGE), pe);
        }
    }

}
