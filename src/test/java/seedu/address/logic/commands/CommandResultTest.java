package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {

    @Test
    public void constructor_feedbackToUserNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CommandResult(null));
    }

    @Test
    public void isShowHelp() {
        CommandResult commandResultFalse = new CommandResult("validFeedback", false, false, false);
        CommandResult commandResultTrue = new CommandResult("valicFeedback", true, true, true);

        assertFalse(commandResultFalse.isShowHelp());
        assertTrue(commandResultTrue.isShowHelp());
    }

    @Test
    public void isShowCommands() {
        CommandResult commandResultFalse = new CommandResult("validFeedback", false, false, false);
        CommandResult commandResultTrue = new CommandResult("valicFeedback", true, true, true);

        assertFalse(commandResultFalse.isShowCommands());
        assertTrue(commandResultTrue.isShowCommands());
    }

    @Test
    public void isExit() {
        CommandResult commandResultFalse = new CommandResult("validFeedback", false, false, false);
        CommandResult commandResultTrue = new CommandResult("valicFeedback", true, true, true);

        assertFalse(commandResultFalse.isExit());
        assertTrue(commandResultTrue.isExit());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, true).hashCode());
    }
}
