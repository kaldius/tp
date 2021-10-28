package seedu.address.model.blockedslot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalBlockedSlots.EIGHT_TO_NINE;
import static seedu.address.testutil.TypicalBlockedSlots.NINE_TO_TEN;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BlockedSlotBuilder;

public class BlockedSlotTest {

    @Test
    public void compareTo() {
        BlockedSlot earlierBlockedSlot = NINE_TO_TEN;
        BlockedSlot sameAsEarlierBlockedSlot = new BlockedSlotBuilder(NINE_TO_TEN)
                .withTimeSlot("0900", "1000")
                .withDate("2020-01-01")
                .build();
        BlockedSlot laterDateBlockedSlot = new BlockedSlotBuilder(NINE_TO_TEN)
                .withTimeSlot("0900", "1000")
                .withDate("2020-01-02")
                .build();
        BlockedSlot laterSlotBlockedSlot = new BlockedSlotBuilder(NINE_TO_TEN)
                .withTimeSlot("1000", "1100")
                .withDate("2020-01-01")
                .build();
        assertTrue(earlierBlockedSlot.compareTo(sameAsEarlierBlockedSlot) == 0);
        assertTrue(earlierBlockedSlot.compareTo(laterDateBlockedSlot) < 0);
        assertTrue(earlierBlockedSlot.compareTo(laterSlotBlockedSlot) < 0);
        assertTrue(laterDateBlockedSlot.compareTo(earlierBlockedSlot) > 0);
        assertTrue(laterSlotBlockedSlot.compareTo(earlierBlockedSlot) > 0);
    }

    @Test
    public void isOverlappingWith() {
        BlockedSlot toCompareEvent = EIGHT_TO_NINE;
        BlockedSlot noOverlappingDateNoOverlappingSlot = new BlockedSlotBuilder(EIGHT_TO_NINE)
                .withTimeSlot("0900", "1000")
                .withDate("2020-01-02")
                .build();
        BlockedSlot noOverlappingDateHasOverlappingSlot = new BlockedSlotBuilder(EIGHT_TO_NINE)
                .withTimeSlot("0830", "0930")
                .withDate("2020-01-02")
                .build();
        BlockedSlot overlappingDateNoOverlappingSlot = new BlockedSlotBuilder(EIGHT_TO_NINE)
                .withTimeSlot("0900", "1000")
                .withDate("2020-01-01")
                .build();
        BlockedSlot overlappingDateHasOverlappingSlot1 = new BlockedSlotBuilder(EIGHT_TO_NINE)
                .withTimeSlot("0830", "0930")
                .withDate("2020-01-01")
                .build();
        BlockedSlot overlappingDateHasOverlappingSlot2 = new BlockedSlotBuilder(EIGHT_TO_NINE)
                .withTimeSlot("0730", "0830")
                .withDate("2020-01-01")
                .build();
        BlockedSlot overlappingDateHasOverlappingSlot3 = new BlockedSlotBuilder(EIGHT_TO_NINE)
                .withTimeSlot("0830", "0845")
                .withDate("2020-01-01")
                .build();
        BlockedSlot overlappingDateHasOverlappingSlot4 = new BlockedSlotBuilder(NINE_TO_TEN)
                .withTimeSlot("0700", "1000")
                .withDate("2020-01-01")
                .build();

        assertFalse(toCompareEvent.isOverlappingWith(noOverlappingDateNoOverlappingSlot));
        assertFalse(toCompareEvent.isOverlappingWith(noOverlappingDateHasOverlappingSlot));
        assertFalse(toCompareEvent.isOverlappingWith(overlappingDateNoOverlappingSlot));
        assertTrue(toCompareEvent.isOverlappingWith(overlappingDateHasOverlappingSlot1));
        assertTrue(toCompareEvent.isOverlappingWith(overlappingDateHasOverlappingSlot2));
        assertTrue(toCompareEvent.isOverlappingWith(overlappingDateHasOverlappingSlot3));
        assertTrue(toCompareEvent.isOverlappingWith(overlappingDateHasOverlappingSlot4));
    }
}


