package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.jupiter.api.Assertions.*;

class PromptListTest {
    private PromptList testPromptList;
    private Prompt p1;
    private Prompt p2;
    private Prompt p3;

    @BeforeEach
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
        assertTrue(testPromptList.containsPrompt(p1));
    }

    @Test
    public void testAddMultiplePrompt() {
        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p3);
        assertEquals(2, testPromptList.getSize());
        assertTrue(testPromptList.containsPrompt(p2));
        assertTrue(testPromptList.containsPrompt(p3));
    }

    @Test
    public void testRemoveNoPrompt() {
        testPromptList.addPrompt(p1);
        testPromptList.removePrompt(p2);
        assertTrue(testPromptList.containsPrompt(p1));
    }

    @Test
    public void testRemoveOnePrompt() {
        testPromptList.addPrompt(p1);
        testPromptList.removePrompt(p1);
        assertEquals(0, testPromptList.getSize());
    }

    @Test
    public void testRemoveMultiplePrompt() {
        testPromptList.addPrompt(p1);
        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p3);
        testPromptList.removePrompt(p2);
        testPromptList.removePrompt(p1);

        assertTrue(testPromptList.containsPrompt(p3));
        assertEquals(1, testPromptList.getSize());

    }

    /*
    @Test
    public void testViewPromptsNoPrompt() {
        assertEquals("", testPromptList.viewPrompts());
    }

     */


    @Test
    public void testViewPromptsMultiplePrompt() {
        assertEquals("What colour is chloroplast? Green",
                testPromptList.viewPrompts(p2));
    }

    @Test
    public void testTotalPrompts() {
        testPromptList.addPrompt(p1);
        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p3);
        assertEquals(3, testPromptList.totalPrompts());
    }

}