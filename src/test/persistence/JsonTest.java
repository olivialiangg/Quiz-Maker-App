package persistence;

import model.Prompt;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPrompt(String question, String answer, Boolean isHard, Prompt p) {
        assertEquals(question, p.getQuestion());
        assertEquals(answer, p.getAnswer());
        assertEquals(isHard, p.getIsHard());
    }
}
