package persistence;

import model.PromptList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


public class JsonWriterTest {

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
}
