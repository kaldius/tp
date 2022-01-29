package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setScheduleFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setScheduleFilePath(null));
    }

    @Test
    public void equalsMethod() {
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs sameAsUserPrefs = new UserPrefs();

        assertFalse(userPrefs.equals(null));
        assertFalse(userPrefs.equals(5));
        assertTrue(userPrefs.equals(userPrefs));
        assertTrue(userPrefs.equals(sameAsUserPrefs));
    }

    @Test
    public void hashCodeMethod() {
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs sameAsUserPrefs = new UserPrefs();

        assertEquals(userPrefs.hashCode(), userPrefs.hashCode());
        assertEquals(userPrefs.hashCode(), sameAsUserPrefs.hashCode());
    }
}
