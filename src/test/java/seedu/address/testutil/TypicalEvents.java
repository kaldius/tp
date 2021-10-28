package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Schedule;
import seedu.address.model.blockedslot.BlockedSlot;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event ALICE = new EventBuilder().withName("Alice Pauline")
            .withLocation("123, Jurong West Ave 6, #08-111").withTimeSlot("0800", "0900")
            .withDate("2020-01-01")
            .withTags("friends")
            .withRemark("A nice girl.").build();
    public static final Event BENSON = new EventBuilder().withName("Benson Meier")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withTimeSlot("0830", "0930").withDate("2020-01-02")
            .withTags("owesMoney", "friends")
            .withRemark("A weird boy.").build();
    public static final Event CARL = new EventBuilder().withName("Carl Kurz").withDate("2020-01-03")
            .withTimeSlot("0930", "1030").withLocation("wall street").withRemark("May change schedule.").build();
    public static final Event DANIEL = new EventBuilder().withName("Daniel Meier").withDate("2020-01-04")
            .withTimeSlot("1000", "1100").withLocation("10th street").withTags("friends").build();
    public static final Event ELLE = new EventBuilder().withName("Elle Meyer").withDate("2020-01-05")
            .withTimeSlot("1030", "1130").withLocation("michegan ave").build();
    public static final Event FIONA = new EventBuilder().withName("Fiona Kunz").withDate("2020-01-06")
            .withTimeSlot("1100", "1200").withLocation("little tokyo").build();
    public static final Event GEORGE = new EventBuilder().withName("George Best").withDate("2020-01-07")
            .withTimeSlot("1130", "1230").withLocation("4th street").build();

    // Manually added
    public static final Event HOON = new EventBuilder().withName("Hoon Meier").withDate("2020-01-08")
            .withTimeSlot("1300", "1400").withLocation("little india").build();
    public static final Event IDA = new EventBuilder().withName("Ida Mueller").withDate("2020-01-09")
            .withTimeSlot("1330", "1430").withLocation("chicago ave").build();

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event AMY = new EventBuilder().withName(VALID_NAME_AMY).withDate(VALID_DATE_AMY)
            .withTimeSlot(VALID_STARTTIME_AMY, VALID_ENDTIME_AMY).withLocation(VALID_LOCATION_AMY)
            .withTags(VALID_TAG_FRIEND).withRemark(VALID_REMARK_AMY).build();
    public static final Event BOB = new EventBuilder().withName(VALID_NAME_BOB).withDate(VALID_DATE_BOB)
            .withTimeSlot(VALID_STARTTIME_BOB, VALID_ENDTIME_BOB).withLocation(VALID_LOCATION_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withRemark(VALID_REMARK_BOB)
            .build();

    public static final BlockedSlot ONE = new BlockedSlotBuilder().withDate("2020-02-06").withTimeSlot(
            "1100", "1200").build();
    public static final BlockedSlot TWO = new BlockedSlotBuilder().withDate("2020-02-06").withTimeSlot(
            "1200", "1300").build();
    public static final BlockedSlot THREE = new BlockedSlotBuilder().withDate("2020-02-06").withTimeSlot(
            "1300", "1400").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns a {@code Schedule} with all the typical events.
     */
    public static Schedule getTypicalSchedule() {
        Schedule ab = new Schedule();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        for (BlockedSlot blockedSlot : getTypicalBlockedSlots()) {
            ab.addBlockedSlot(blockedSlot);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<BlockedSlot> getTypicalBlockedSlots() {
        return new ArrayList<>(Arrays.asList(ONE, TWO, THREE));
    }

}
