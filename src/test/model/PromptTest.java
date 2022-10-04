package model;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PromptTest {
    private PromptList testPromptList;
    private Prompt p1;
    private Prompt p2;
    private Prompt p3;

    @Before
    public void setUp() {
        testPromptList = new PromptList();
        p1 = new Prompt("What is the powerhouse of the cell?", "Mitochondria");
        p2 = new Prompt("What colour is chloroplast?", "Green");
        p3 = new Prompt("Where is DNA stored in the cell?", "Nucleus");

    }

    @Test
    public void testConstructor() {
        assertEquals(0, testPromptList.getSize());
    }

    @Test
    public void testAddPrompt() {
        testPromptList.addPrompt(p1);
        assertEquals(1, testPromptList.getSize());

        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p3);
        assertEquals(2, testPromptList.getSize());
    }

    @Test
    public void testRemovePrompt() {
        testPromptList.addPrompt(p1);
        testPromptList.removePrompt(p2);
        assertEquals(1, testPromptList.getSize());

        testPromptList.addPrompt(p1);
        testPromptList.removePrompt(p1);
        assertEquals(0, testPromptList.getSize());

        testPromptList.addPrompt(p1);
        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p3);
        testPromptList.removePrompt(p2);
        assertEquals(2, testPromptList.getSize());
    }

    @Test
    public void testViewPrompts() {
        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p3);
        assertEquals("What colour is chloroplast? Green Where is DNA stored in the cell? Nucleus",
                testPromptList.viewPrompts());
    }

    @Test
    public void testTotalPrompts() {
        testPromptList.addPrompt(p1);
        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p3);
        assertEquals(0, testPromptList.totalPrompts());
    }

}