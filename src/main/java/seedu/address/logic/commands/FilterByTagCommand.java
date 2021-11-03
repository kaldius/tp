package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.event.TagContainsKeywordsPredicate;

public class FilterByTagCommand extends Command {
    public static final String COMMAND_WORD = "filter_tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all consultation events whose tags"
            + " contain any of the specified keywords (case-insensitive)"
            + " and displays them as an indexed list.\n"
            + "Parameters: TAG_NAME [MORE_TAG_NAMES]...\n"
            + "Example: " + COMMAND_WORD + " URGENT supplementary";

    private final TagContainsKeywordsPredicate tagPredicate;

    public FilterByTagCommand(TagContainsKeywordsPredicate predicate) {
        this.tagPredicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(tagPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FILTERED_TAGS, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterByTagCommand // instanceof handles nulls
                && tagPredicate.equals(((FilterByTagCommand) other).tagPredicate)); // state check
    }
}
