package seedu.address.testutil;

import seedu.address.model.event.BlockedSlot;

/**
 * A utility class containing a list of {@code TypicalBlocked} objects to be used in tests.
 */
public class TypicalBlockedSlots {

    public static final BlockedSlot NINE_TO_TEN = new BlockedSlotBuilder()
            .withTimeSlot("0900", "1000").withDate("2020-01-01").build();

    public static final BlockedSlot EIGHT_TO_NINE = new BlockedSlotBuilder()
            .withTimeSlot("0800", "0900").withDate("2020-01-01").build();


    private TypicalBlockedSlots() {
    } // prevents instantiation

}