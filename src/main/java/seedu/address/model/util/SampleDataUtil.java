package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.Schedule;
import seedu.address.model.event.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.Location;
import seedu.address.model.event.Name;
import seedu.address.model.event.Remark;
import seedu.address.model.event.TimeSlot;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Schedule} with sample data.
 */
public class SampleDataUtil {
    public static Event[] getSampleEvents() {
        return new Event[] {
            new Event(new Name("Jacob Ng"), new Date("2021-11-12"), new TimeSlot("1300", "1400"),
                new Location("The Deck"),
                getTagSet("CS1231S", "URGENT"), new Remark("Cool student")),
            new Event(new Name("Ruth Poh"), new Date("2021-11-14"), new TimeSlot("1600", "1700"),
                new Location("Central Library"),
                getTagSet("supplementary"), new Remark("Coool student")),
            new Event(new Name("Teng Foong"), new Date("2021-11-18"), new TimeSlot("1000", "1100"),
                new Location("COM1 Basement"),
                getTagSet("CS1231S"), new Remark("Cooool student")),
            new Event(new Name("Galvin C"), new Date("2021-11-19"), new TimeSlot("1400", "1500"),
                new Location("Office"),
                getTagSet("CS2100"), new Remark("Student. Very cool.")),
            new Event(new Name("Lulu"), new Date("2021-11-20"), new TimeSlot("1400", "1500"),
                new Location("Office"),
                getTagSet("supplementary"), new Remark(""))
        };
    }

    public static ReadOnlySchedule getSampleSchedule() {
        Schedule sampleSc = new Schedule();
        for (Event sampleEvent : getSampleEvents()) {
            sampleSc.addEvent(sampleEvent);
        }
        return sampleSc;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
