package model.persistence;

import model.Prompt;
import model.PromptList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

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
        JsonReader reader = new JsonReader("./data/testReaderEmptyPromptList.json");
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
        JsonReader reader = new JsonReader("./data/testReaderGeneralPromptList.json");
        try {
            PromptList prompts = reader.read();
            assertEquals("Your quiz", prompts.getName());
            List<Prompt> promptList = prompts.getPromptList();
            assertEquals(2, promptList.size());
            checkPrompt("What colour is chloroplast?", "Green", true, promptList.get(0));
            checkPrompt("What is the power house of the cell?", "Mitochondria", false,
                    promptList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}