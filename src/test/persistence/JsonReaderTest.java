package persistence;

import model.PromptList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PromptList prompts = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPromptList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            PromptList prompts = reader.read();
            assertEquals("Your quiz", prompts.getName());
            assertEquals(0, prompts.totalPrompts());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPromptList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            PromptList prompts = reader.read();
            assertEquals("My work room", prompts.getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}