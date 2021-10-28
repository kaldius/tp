package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FilterByTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.TagContainsKeywordsPredicate;

/**
* Parses input arguments and creates a new FilterByTagCommand object
*/
public class FilterByTagCommandParser implements Parser<FilterByTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterByTagCommand
     * and returns a FilterByTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterByTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterByTagCommand.MESSAGE_USAGE));
        }
        String[] tagKeywords = trimmedArgs.split("\\s+");
        return new FilterByTagCommand(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
    }
}
