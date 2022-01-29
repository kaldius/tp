package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE;
import static seedu.address.testutil.TypicalEvents.HOON;
import static seedu.address.testutil.TypicalEvents.IDA;
import static seedu.address.testutil.TypicalEvents.getTypicalSchedule;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.Schedule;

public class JsonScheduleStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonScheduleStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSchedule_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSchedule(null));
    }

    private java.util.Optional<ReadOnlySchedule> readSchedule(String filePath) throws Exception {
        return new JsonScheduleStorage(Paths.get(filePath)).readSchedule(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSchedule("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSchedule("notJsonFormatSchedule.json"));
    }

    @Test
    public void readSchedule_invalidEventSchedule_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSchedule("invalidEventSchedule.json"));
    }

    @Test
    public void readSchedule_invalidAndValidEventSchedule_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSchedule("invalidAndValidEventSchedule.json"));
    }

    @Test
    public void readSchedule_validCollidingEventsSchedule_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSchedule("validCollidingEventsSchedule.json"));
    }

    @Test
    public void readSchedule_eventCollidingWithBlockedSlotSchedule_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSchedule("eventCollidingWithBlockedSlotSchedule.json"));
    }

    @Test
    public void readAndSaveSchedule_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSchedule.json");
        Schedule original = getTypicalSchedule();
        JsonScheduleStorage jsonScheduleStorage = new JsonScheduleStorage(filePath);

        // Save in new file and read back
        jsonScheduleStorage.saveSchedule(original, filePath);
        ReadOnlySchedule readBack = jsonScheduleStorage.readSchedule(filePath).get();
        assertEquals(original, new Schedule(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEvent(HOON);
        original.removeEvent(ALICE);
        jsonScheduleStorage.saveSchedule(original, filePath);
        readBack = jsonScheduleStorage.readSchedule(filePath).get();
        assertEquals(original, new Schedule(readBack));

        // Save and read without specifying file path
        original.addEvent(IDA);
        jsonScheduleStorage.saveSchedule(original); // file path not specified
        readBack = jsonScheduleStorage.readSchedule().get(); // file path not specified
        assertEquals(original, new Schedule(readBack));

    }

    @Test
    public void saveSchedule_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSchedule(null, "SomeFile.json"));
    }

    /**
     * Saves {@code schedule} at the specified {@code filePath}.
     */
    private void saveSchedule(ReadOnlySchedule schedule, String filePath) {
        try {
            new JsonScheduleStorage(Paths.get(filePath))
                    .saveSchedule(schedule, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSchedule_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSchedule(new Schedule(), null));
    }
}
