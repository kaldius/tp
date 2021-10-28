package seedu.address.testutil;

import seedu.address.model.blockedslot.BlockedSlot;
import seedu.address.model.event.Date;
import seedu.address.model.event.TimeSlot;

/**
 * A utility class to help with building BlockedSlot objects.
 */
public class BlockedSlotBuilder {

    public static final String DEFAULT_DATE = "2020-01-01";
    public static final String DEFAULT_STARTTIME = "0800";
    public static final String DEFAULT_ENDTIME = "0900";

    private Date date;
    private TimeSlot timeSlot;

    /**
     * Creates a {@code BlockedSlotBuilder} with the default details.
     */
    public BlockedSlotBuilder() {
        date = new Date(DEFAULT_DATE);
        timeSlot = new TimeSlot(DEFAULT_STARTTIME, DEFAULT_ENDTIME);
    }

    /**
     * Initializes the BlockedSlotBuilder with the data of {@code eventToCopy}.
     */
    public BlockedSlotBuilder(BlockedSlot eventToCopy) {
        date = eventToCopy.getDate();
        timeSlot = eventToCopy.getTimeSlot();
    }

    /**
     * Sets the {@code Date} of the {@code BlockedSlot} that we are building.
     */
    public BlockedSlotBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code TimeSlot} of the {@code BlockedSlot} that we are building.
     */
    public BlockedSlotBuilder withTimeSlot(String startTime, String endTime) {
        this.timeSlot = new TimeSlot(startTime, endTime);
        return this;
    }


    public BlockedSlot build() {
        return new BlockedSlot(date, timeSlot);
    }

}
