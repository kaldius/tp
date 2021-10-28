package seedu.address.model.blockedslot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBlockedSlots.EIGHT_TO_NINE;
import static seedu.address.testutil.TypicalBlockedSlots.NINE_TO_TEN;

import org.junit.jupiter.api.Test;

import seedu.address.model.blockedslot.exceptions.BlockedSlotNotFoundException;
import seedu.address.testutil.BlockedSlotBuilder;

public class SortedBlockedSlotListTest {

    private final SortedBlockedSlotList sortedBlockedSlotList = new SortedBlockedSlotList();

    @Test
    public void sortBlockedSlotByDate() {
        BlockedSlot differentDateBlocked =
                new BlockedSlotBuilder(EIGHT_TO_NINE).withDate("2020-02-02").build();
        sortedBlockedSlotList.add(EIGHT_TO_NINE);
        sortedBlockedSlotList.add(differentDateBlocked);
        SortedBlockedSlotList expectedSortedBlockedSlotList = new SortedBlockedSlotList();
        expectedSortedBlockedSlotList.add(differentDateBlocked);
        expectedSortedBlockedSlotList.add(EIGHT_TO_NINE);
        assertEquals(expectedSortedBlockedSlotList, sortedBlockedSlotList);
    }

    @Test
    public void sortBlockedSlotByTimeSlot() {
        sortedBlockedSlotList.add(EIGHT_TO_NINE);
        sortedBlockedSlotList.add(NINE_TO_TEN);
        SortedBlockedSlotList expectedSortedBlockedSlotList = new SortedBlockedSlotList();
        expectedSortedBlockedSlotList.add(NINE_TO_TEN);
        expectedSortedBlockedSlotList.add(EIGHT_TO_NINE);
        assertEquals(expectedSortedBlockedSlotList, sortedBlockedSlotList);
    }

    @Test
    public void sortBlockedSlotByTimeSlotDate() {
        BlockedSlot differentDateBlocked =
                new BlockedSlotBuilder(EIGHT_TO_NINE).withDate("2020-01-01").build();
        sortedBlockedSlotList.add(EIGHT_TO_NINE);
        sortedBlockedSlotList.add(differentDateBlocked);
        sortedBlockedSlotList.add(NINE_TO_TEN);
        SortedBlockedSlotList expectedSortedBlockedSlotList = new SortedBlockedSlotList();
        expectedSortedBlockedSlotList.add(NINE_TO_TEN);
        expectedSortedBlockedSlotList.add(EIGHT_TO_NINE);
        expectedSortedBlockedSlotList.add(differentDateBlocked);
        assertEquals(expectedSortedBlockedSlotList, sortedBlockedSlotList);
    }

    @Test
    public void add_nullBlockedSlot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sortedBlockedSlotList.add(null));
    }

    @Test
    public void remove_nullBlockedSlot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sortedBlockedSlotList.remove(null));
    }

    @Test
    public void remove_eventDoesNotExist_throwsBlockedSlotNotFoundException() {
        assertThrows(BlockedSlotNotFoundException.class, () -> sortedBlockedSlotList.remove(NINE_TO_TEN));
    }

    @Test
    public void remove_existingBlockedSlot_removesBlockedSlot() {
        sortedBlockedSlotList.add(NINE_TO_TEN);
        sortedBlockedSlotList.remove(NINE_TO_TEN);
        SortedBlockedSlotList expectedSortedBlockedSlotList = new SortedBlockedSlotList();
        assertEquals(expectedSortedBlockedSlotList, sortedBlockedSlotList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> sortedBlockedSlotList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void isOverlappingWith() {
        sortedBlockedSlotList.add(EIGHT_TO_NINE);
        BlockedSlot noOverlappingDateNoOverlappingSlot = new BlockedSlotBuilder(NINE_TO_TEN)
                .withTimeSlot("0900", "1000")
                .withDate("2020-01-02")
                .build();
        BlockedSlot noOverlappingDateHasOverlappingSlot = new BlockedSlotBuilder(NINE_TO_TEN)
                .withTimeSlot("0830", "0930")
                .withDate("2020-01-02")
                .build();
        BlockedSlot overlappingDateNoOverlappingSlot = new BlockedSlotBuilder(NINE_TO_TEN)
                .withTimeSlot("0900", "1000")
                .withDate("2020-01-01")
                .build();
        BlockedSlot overlappingDateHasOverlappingSlot1 = new BlockedSlotBuilder(NINE_TO_TEN)
                .withTimeSlot("0830", "0930")
                .withDate("2020-01-01")
                .build();
        BlockedSlot overlappingDateHasOverlappingSlot2 = new BlockedSlotBuilder(NINE_TO_TEN)
                .withTimeSlot("0730", "0830")
                .withDate("2020-01-01")
                .build();
        BlockedSlot overlappingDateHasOverlappingSlot3 = new BlockedSlotBuilder(NINE_TO_TEN)
                .withTimeSlot("0830", "0845")
                .withDate("2020-01-01")
                .build();
        BlockedSlot overlappingDateHasOverlappingSlot4 = new BlockedSlotBuilder(NINE_TO_TEN)
                .withTimeSlot("0700", "1000")
                .withDate("2020-01-01")
                .build();

        assertFalse(sortedBlockedSlotList.isOverlappingWith(noOverlappingDateNoOverlappingSlot));
        assertFalse(sortedBlockedSlotList.isOverlappingWith(noOverlappingDateHasOverlappingSlot));
        assertFalse(sortedBlockedSlotList.isOverlappingWith(overlappingDateNoOverlappingSlot));
        assertTrue(sortedBlockedSlotList.isOverlappingWith(overlappingDateHasOverlappingSlot1));
        assertTrue(sortedBlockedSlotList.isOverlappingWith(overlappingDateHasOverlappingSlot2));
        assertTrue(sortedBlockedSlotList.isOverlappingWith(overlappingDateHasOverlappingSlot3));
        assertTrue(sortedBlockedSlotList.isOverlappingWith(overlappingDateHasOverlappingSlot4));
    }
}
