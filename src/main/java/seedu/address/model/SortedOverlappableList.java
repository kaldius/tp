package seedu.address.model;

import javafx.collections.ObservableList;

/**
 * Interface for a list of Overlappables.
 * Must be maintained in a sorted state.
 */
public interface SortedOverlappableList<T extends Overlappable> extends Iterable<T> {

    /**
     * Adds an Overlappable to the list.
     * @param t Overlappable to be added.
     */
    void add(T t);

    /**
     * Removes the equivalent Overlappable form the list.
     * The event must exist in the list.
     * @param t Overlappable to be removed.
     */
    void remove(T t);

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     * @return an ObservableList.
     */
    ObservableList<T> asUnmodifiableObservableList();

    /**
     * Checks if the given Overlappable overlaps with any other in the list.
     * @param overlappable Overlappable to check.
     * @return true if the given Overlappable overlaps with an Overlappable in the list, false otherwise.
     */
    boolean isOverlappingWith(Overlappable overlappable);

}
