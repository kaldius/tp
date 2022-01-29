package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void getLogLevel() {
        Config defaultConfig = new Config();
        assertTrue(defaultConfig.getLogLevel().equals(Level.INFO));
    }

    @Test
    public void gtUserPrefsFilePath() {
        Config defaultConfig = new Config();
        assertTrue(defaultConfig.getUserPrefsFilePath().equals(Paths.get("preferences.json")));
    }

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig));
    }

    @Test
    public void equals_null_returnsFalse() {
        Config defaultConfig = new Config();
        assertFalse(defaultConfig.equals(null));
    }

    @Test
    public void hashCodeMethod() {
        Config configA = new Config();
        Config configB = new Config();
        assertEquals(configA.hashCode(), configB.hashCode());
    }

}
