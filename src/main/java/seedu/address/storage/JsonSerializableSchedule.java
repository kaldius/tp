package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.Schedule;
import seedu.address.model.blockedslot.BlockedSlot;
import seedu.address.model.event.Event;

/**
 * An Immutable Schedule that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableSchedule {

    private final List<JsonAdaptedEvent> events = new ArrayList<>();
    private final List<JsonAdaptedBlockedSlot> blockedSlots = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableSchedule} with the given events.
     */
    @JsonCreator
    public JsonSerializableSchedule(@JsonProperty("events") List<JsonAdaptedEvent> events,
                                    @JsonProperty("blockedSlots") List<JsonAdaptedBlockedSlot> blockedSlots) {
        this.events.addAll(events);
        this.blockedSlots.addAll(blockedSlots);
    }

    /**
     * Converts a given {@code ReadOnlySchedule} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSchedule}.
     */
    public JsonSerializableSchedule(ReadOnlySchedule source) {
        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
        blockedSlots.addAll(source.getBlockedSlotList().stream()
                .map(JsonAdaptedBlockedSlot::new).collect(Collectors.toList()));
    }

    /**
     * Converts this schedule into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Schedule toModelType() throws IllegalValueException {
        Schedule schedule = new Schedule();
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            schedule.addEvent(event);
        }
        for (JsonAdaptedBlockedSlot jsonAdaptedBlockedSlots : blockedSlots) {
            BlockedSlot blockedSlot = jsonAdaptedBlockedSlots.toModelType();
            schedule.addBlockedSlot(blockedSlot);
        }
        return schedule;
    }
}
