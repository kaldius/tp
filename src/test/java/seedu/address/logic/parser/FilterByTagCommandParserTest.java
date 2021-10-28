package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterByTagCommand;
import seedu.address.model.event.TagContainsKeywordsPredicate;

public class FilterByTagCommandParserTest {

    private FilterByTagCommandParser parser = new FilterByTagCommandParser();

    @Test
    public void parse_noTag_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterByTagCommand.MESSAGE_USAGE));
    }

    @Test
     public void parse_validArgs_returnsFilterByTagCommand() {
        // no leading and trailing whitespaces
        FilterByTagCommand expectedFilteredTagCommand =
                new FilterByTagCommand(new TagContainsKeywordsPredicate(Arrays.asList("friends", "owesMoney")));
        assertParseSuccess(parser, "friends owesMoney", expectedFilteredTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n friends \n \t owesMoney  \t", expectedFilteredTagCommand);
    }
}
