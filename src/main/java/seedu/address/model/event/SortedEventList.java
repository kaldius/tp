package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.Overlappable;
import seedu.address.model.SortedOverlappableList;
import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * A list of events is sorted chronologically and does not allow nulls.
 * Duplicate Events are allowed in this Event list to accommodate repeated events with same person.
 *
 * Supports a minimal set of list operations.
 *
 */
public class SortedEventList implements SortedOverlappableList<Event> {
    private class EventComparator implements Comparator<Event> {
        @Override
        public int compare(Event o1, Event o2) {
            return o1.compareTo(o2);
        }
    }

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            new SortedList<>(FXCollections.unmodifiableObservableList(internalList),
                    new EventComparator());

    @Override
    public void add(Event toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    @Override
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
    }

    /**
     * Replaces the target Event in the list with the new edited Event.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EventNotFoundException();
        }

        internalList.set(index, editedEvent);
    }

    /**
     * Replaces the contents of this list with {@code Events}.
     */
    public void setEvent(SortedEventList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Events}.
     */
    public void setEvent(List<Event> events) {
        requireAllNonNull(events);
        internalList.setAll(events);
    }

    @Override
    public ObservableList<Event> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Event> iterator() {
        return internalUnmodifiableList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortedEventList // instanceof handles nulls
                && internalUnmodifiableList.equals(((SortedEventList) other).internalUnmodifiableList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public boolean isOverlappingWith(Overlappable overlappable) {
        boolean hasOverlaps = false;
        for (Overlappable o : internalList) {
            if (o.isOverlappingWith(overlappable)) {
                hasOverlaps = true;
            }
        }

        return hasOverlaps;
    }

    @Override
    public boolean isOverlappingWith(Overlappable overlappable, Overlappable excluding) {
        boolean hasOverlaps = false;
        for (Event e : internalList) {
            if (!e.equals(excluding) && e.isOverlappingWith(overlappable)) {
                hasOverlaps = true;
            }
        }

        return hasOverlaps;
    }
}
