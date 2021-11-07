package seedu.address.model.event;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
* Tests that a {@code Event}'s {@code Tag} matches any of the keywords given.
*/
public class TagContainsKeywordsPredicate implements Predicate<Event> {

    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        Set<Tag> tags = event.getTags();
        return keywords.stream()
                .anyMatch(keyword -> matchTagsIgnoreCase(tags, keyword));
    }

    private boolean matchTagsIgnoreCase(Set<Tag> tags, String keyword) {
        return tags.stream()
                .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.getTagName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}

