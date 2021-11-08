package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.getTypicalSchedule;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.FreeSlot;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Schedule;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Date;

public class ListFreeSlotsCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ListFreeSlotsCommand(null, LocalTime.of(0, 0)));
    }

    @Test
    public void execute_emptySchedule_emptyList() {
        Model emptymodel = new ModelManager(new Schedule(), new UserPrefs());
        Command command = new ListFreeSlotsCommand(new Date("2020-01-01"), LocalTime.of(0, 0));
        assertCommandSuccess(command, emptymodel, ListFreeSlotsCommand.MESSAGE_SUCCESS, emptymodel);
    }

    @Test
    public void execute_typicalModel_nonEmptyList() {
        Schedule schedule = getTypicalSchedule();
        Model model = new ModelManager(schedule, new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSchedule(), new UserPrefs());
        ArrayList<FreeSlot> freeSlots = schedule.getFreeSlots(new Date("2020-01-01"), LocalTime.of(0, 0));
        StringBuilder expectedString = new StringBuilder(ListFreeSlotsCommand.MESSAGE_SUCCESS);
        for (FreeSlot f: freeSlots) {
            expectedString.append("\n" + f.toString());
        }
        Command command = new ListFreeSlotsCommand(new Date("2020-01-01"), LocalTime.of(0, 0));
        assertCommandSuccess(command, model, expectedString.toString(), expectedModel);
    }
}
