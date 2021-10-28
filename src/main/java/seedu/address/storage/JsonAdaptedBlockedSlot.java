package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.blockedslot.BlockedSlot;
import seedu.address.model.event.Date;
import seedu.address.model.event.TimeSlot;

/**
 * Jackson-friendly version of {@link BlockedSlot}.
 */
class JsonAdaptedBlockedSlot {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "BlockedSlot's %s field is missing!";

    private final String date;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedBlockedSlot} with the given blocked slot details.
     */
    @JsonCreator
    public JsonAdaptedBlockedSlot(@JsonProperty("date") String date,
                            @JsonProperty("startTime") String startTime,
                                  @JsonProperty("endTime") String endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code BlockedSlot} into this class for Jackson use.
     */
    public JsonAdaptedBlockedSlot(BlockedSlot source) {
        date = source.getDate().value;
        startTime = source.getTimeSlot().startTimeToString();
        endTime = source.getTimeSlot().endTimeToString();
    }

    /**
     * Converts this Jackson-friendly adapted blocked slot object into the model's {@code BlockedSlot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted blocked slot.
     */
    public BlockedSlot toModelType() throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (startTime == null || endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TimeSlot.class.getSimpleName()));
        }
        if (!TimeSlot.isValidTimeSlot(startTime, endTime)) {
            throw new IllegalValueException(TimeSlot.MESSAGE_CONSTRAINTS);
        }
        final TimeSlot modelTimeSlot = new TimeSlot(startTime, endTime);

        return new BlockedSlot(modelDate, modelTimeSlot);
    }

}
