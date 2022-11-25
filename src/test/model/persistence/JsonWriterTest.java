package model.persistence;

import model.Prompt;
import model.PromptList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;


public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            PromptList wr = new PromptList("Test list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            PromptList prompts = new PromptList("Test list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPromptList.json");
            writer.open();
            writer.write(prompts);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPromptList.json");
            prompts = reader.read();
            assertEquals("Test list", prompts.getName());
            assertEquals(0, prompts.totalPrompts());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterGeneralWorkroom() {
        try {
            PromptList prompts = new PromptList("Test list");
            prompts.addPrompt(new Prompt("What colour is chloroplast?", "Green", true));
            prompts.addPrompt(new Prompt("What is the power house of the cell?", "Mitochondria",
                    false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPromptList.json");
            writer.open();
            writer.write(prompts);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPromptList.json");
            prompts = reader.read();
            assertEquals("Test list", prompts.getName());
            List<Prompt> promptList = prompts.getPromptList();
            assertEquals(2, promptList.size());
            checkPrompt("What colour is chloroplast?", "Green", true, promptList.get(0));
            checkPrompt("What is the power house of the cell?", "Mitochondria", false,
                    promptList.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
