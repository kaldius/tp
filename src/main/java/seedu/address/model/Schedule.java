package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.blockedslot.BlockedSlot;
import seedu.address.model.blockedslot.SortedBlockedSlotList;
import seedu.address.model.event.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.SortedEventList;
import seedu.address.model.event.TimeSlot;

/**
 * Wraps all data at the schedule level
 * Duplicates are not allowed (by .isSameEvent comparison)
 */
public class Schedule implements ReadOnlySchedule {

    private final SortedEventList events;
    private final SortedBlockedSlotList blockedSlots;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        events = new SortedEventList();
        blockedSlots = new SortedBlockedSlotList();
    }

    public Schedule() {
    }

    /**
     * Creates a Schedule using the Events in the {@code toBeCopied}
     */
    public Schedule(ReadOnlySchedule toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvent(events);
    }

    /**
     * Replaces the contents of the blocked slot list with {@code blockedSlots}.
     * {@code events} must not contain duplicate events.
     */
    public void setBlockedSlots(List<BlockedSlot> blockedSlots) {
        this.blockedSlots.setBlockedSlot(blockedSlots);
    }

    /**
     * Resets the existing data of this {@code Schedule} with {@code newData}.
     */
    public void resetData(ReadOnlySchedule newData) {
        requireNonNull(newData);

        setEvents(newData.getEventList());
        setBlockedSlots(newData.getBlockedSlotList());
    }

    //// event-level operations

    /**
     * Adds an event to the schedule.
     * The event must not already exist in the schedule.
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the schedule.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the schedule.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);

        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} from this {@code Schedule}.
     * {@code key} must exist in the schedule.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    /**
     * Adds a block with the given BlockedSlot.
     * @param blockedSlot BlockedSlot to be added.
     */
    public void addBlockedSlot(BlockedSlot blockedSlot) {
        blockedSlots.add(blockedSlot);
    }

    /**
     * Removes specified BlockedSlot from this Schedule.
     * @param key BlockedSlot to be removed.
     */
    public void removeBlockedSlot(BlockedSlot key) {
        blockedSlots.remove(key);
    }

    /**
     * Checks if the given Overlappable is blocked by a BlockedSlot.
     * @param overlappable the Overlappable to be checked.
     * @return true if the Overlappable is blocked, false otherwise.
     */
    public boolean isBlockedByBlockedSlot(Overlappable overlappable) {
        return blockedSlots.isOverlappingWith(overlappable);
    }

    /**
     * Checks if the given Overlappable is blocked by a BlockedSlot excluding the given Overlappable.
     * @param overlappable the Overlappable to be checked.
     * @param excluding the Overlappable to exclude from the check.
     * @return true if the Overlappable is blocked, false otherwise.
     */
    public boolean isBlockedByBlockedSlot(Overlappable overlappable, Overlappable excluding) {
        return blockedSlots.isOverlappingWith(overlappable, excluding);
    }

    /**
     * Checks if the given Overlappable is blocked by an Event.
     * @param overlappable the Overlappable to be checked.
     * @return true if the Overlappable is blocked, false otherwise.
     */
    public boolean isBlockedByEvent(Overlappable overlappable) {
        return events.isOverlappingWith(overlappable);
    }

    /**
     * Checks if the given Overlappable is blocked by an Event excluding the given Overlappable.
     * @param overlappable the Overlappable to be checked.
     * @param excluding the Overlappable to exclude from the check.
     * @return true if the Overlappable is blocked, false otherwise.
     */
    public boolean isBlockedByEvent(Overlappable overlappable, Overlappable excluding) {
        return events.isOverlappingWith(overlappable, excluding);
    }

    //// util methods

    @Override
    public String toString() {
        return events.asUnmodifiableObservableList().size() + " events";
        // TODO: refine later
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<BlockedSlot> getBlockedSlotList() {
        return blockedSlots.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Schedule // instanceof handles nulls
                && events.equals(((Schedule) other).events));
    }

    @Override
    public int hashCode() {
        return events.hashCode();
    }

    /**
     * Comparator class to sort list of Overlappable
     */
    private class OverlappableComparator implements Comparator<Overlappable> {

        @Override
        public int compare(Overlappable o1, Overlappable o2) {
            int compareDate = o1.getDate().compareTo(o2.getDate());
            if (compareDate != 0) {
                return compareDate;
            } else {
                return o1.getTimeSlot().compareTo(o2.getTimeSlot());
            }
        }
    }

    private ArrayList<Overlappable> merge() {
        Iterator<Event> eventsIterator = events.iterator();
        Iterator<BlockedSlot> blockedIterator = blockedSlots.iterator();
        ArrayList<Overlappable> allOverlappables = new ArrayList<>();
        eventsIterator.forEachRemaining(e -> allOverlappables.add(e));
        blockedIterator.forEachRemaining(b -> allOverlappables.add(b));
        allOverlappables.sort(new OverlappableComparator());
        return allOverlappables;
    }

    /**
     * Goes through both sortedEventList and sortedBlockedSlotList to find free time slots
     * between events and blocked slots.
     *
     * @param today starting date
     * @param now time noe
     * @return ArrayList of FreeSlot between now to 2359 of date with last event/blocked slot
     */
    public ArrayList<FreeSlot> getFreeSlots(Date today, LocalTime now) {
        ArrayList<Overlappable> allOverlappables = merge();
        ArrayList<FreeSlot> freeSlots = new ArrayList<>();
        if (allOverlappables.isEmpty()) {
            return freeSlots;
        }
        // adds FreeSlots from today 0000 to start time of first Overlappable
        Overlappable first = allOverlappables.get(0);
        addEmptyDates(freeSlots, today, first.getDate());
        addToList(freeSlots, first.getDate(), "0000", first.getTimeSlot().startTimeToString());
        // adds FreeSlots between all Overlappables
        for (int i = 1; i < allOverlappables.size(); i++) {
            Overlappable prev = allOverlappables.get(i - 1);
            Overlappable curr = allOverlappables.get(i);
            addFreeSlotBetween(freeSlots, prev, curr);
        }
        // adds FreeSlot from end time of last overlappable to 2359
        Overlappable last = allOverlappables.get(allOverlappables.size() - 1);
        addToList(freeSlots, last.getDate(), last.getTimeSlot().endTimeToString(), "2359");

        return removePastFreeSlots(freeSlots, today, now);
    }

    /**
     * Creates a new ArrayList using given list containing all freeslots starting from now.
     *
     * @param allFreeSlots list of all freeslots, past and future.
     * @param today today's date.
     * @return list of all freeslots after now.
     */
    private ArrayList<FreeSlot> removePastFreeSlots(ArrayList<FreeSlot> allFreeSlots, Date today, LocalTime now) {
        ArrayList<FreeSlot> futureFreeSlots = new ArrayList<>();
        for (FreeSlot f: allFreeSlots) {
            if (f.getDate().date.isBefore(today.date)) {
                continue;
            } else if (f.getDate().date.isAfter(today.date)) {
                futureFreeSlots.add(f);
            } else {
                if (f.getTimeSlot().endTime.isBefore(now)) {
                    continue;
                } else if (f.getTimeSlot().startTime.isAfter(now)) {
                    futureFreeSlots.add(f);
                } else {
                    String[] nowString = now.toString().split(":");
                    String nowFormatted = nowString[0] + nowString[1];
                    addToList(futureFreeSlots, today, nowFormatted, f.getTimeSlot().endTimeToString());
                }
            }
        }
        return futureFreeSlots;
    }

    /**
     * Creates a freeSlot on date d and timeslot between start and end.
     * Adds freeSlot to list of freeSlots.
     * Ignores if start and end are the same.
     *
     * @param freeSlots list of freeSlots
     * @param d date of freeSlot
     * @param start start time of freeSlot
     * @param end end time of freeSlots
     */
    private void addToList(ArrayList<FreeSlot> freeSlots, Date d, String start, String end) {
        if (!start.equals(end)) {
            freeSlots.add(new FreeSlot(d, new TimeSlot(start, end)));
        }
    }

    /**
     * Adds freeSlots for timeslot between first and second events/blocked slots.
     *
     * @param freeSlots list of freeSlots
     * @param first first event/blocked slot
     * @param second second event/blocked slot
     */
    public void addFreeSlotBetween(ArrayList<FreeSlot> freeSlots, Overlappable first, Overlappable second) {
        String firstEndTime = first.getTimeSlot().endTimeToString();
        String secondStartTime = second.getTimeSlot().startTimeToString();

        if (first.getDate().equals(second.getDate())) {
            addToList(freeSlots, first.getDate(), firstEndTime, secondStartTime);
        } else {
            addToList(freeSlots, first.getDate(), firstEndTime, "2359");
            addEmptyDates(freeSlots, new Date(first.getDate().date.plusDays(1)), second.getDate());
            addToList(freeSlots, second.getDate(), "0000", secondStartTime);
        }
    }

    /**
     * Adds freeSlots for whole days between start (inclusive) and end (exclusive) dates.
     *  @param freeSlots list of freeSlots
     * @param start start date
     * @param end end date
     */
    public void addEmptyDates(ArrayList<FreeSlot> freeSlots, Date start, Date end) {
        if (!start.date.isBefore(end.date)) {
            return;
        } else {
            freeSlots.add(new FreeSlot(start, new TimeSlot("0000", "2359")));
            addEmptyDates(freeSlots, new Date(start.date.plusDays(1)), end);
        }
    }

}
