package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PromptListTest {
    private PromptList testPromptList;
    private Prompt p1;
    private Prompt p2;
    private Prompt p3;

    @BeforeEach
    public void setUp() {
        testPromptList = new PromptList("test");
        p1 = new Prompt("What is the powerhouse of the cell?", "Mitochondria", true);
        p2 = new Prompt("What colour is chloroplast?", "Green", true);
        p3 = new Prompt("Where is DNA stored in the cell?", "Nucleus", false);
    }

    @Test
    public void testConstructor() {
        List<Prompt> prompts = testPromptList.getPromptList();
        assertEquals(0, prompts.size());
        assertEquals("test", testPromptList.getName());
    }

    @Test
    public void testGetName() {
        assertEquals("test", testPromptList.getName());
    }

    @Test
    public void testGetPromptListEmpty() {
        List<Prompt> prompts = testPromptList.getPromptList();
        assertEquals(0, prompts.size());
    }

    @Test
    public void testGetPromptList() {
        testPromptList.addPrompt(p1);
        testPromptList.addPrompt(p2);

        List<Prompt> prompts = testPromptList.getPromptList();

        assertEquals(p1, prompts.get(0));
        assertEquals(p2, prompts.get(1));
    }

    @Test
    public void testAddOnePrompt() {
        testPromptList.addPrompt(p1);
        List<Prompt> prompts = testPromptList.getPromptList();

        assertEquals(1, prompts.size());
        assertTrue(prompts.contains(p1));
    }

    @Test
    public void testAddMultiplePrompts() {
        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p3);
        List<Prompt> prompts = testPromptList.getPromptList();

        assertEquals(2, prompts.size());
        assertTrue(prompts.contains(p2));
        assertTrue(prompts.contains(p3));
    }

    @Test
    public void testAddSamePrompts() {
        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p2);
        List<Prompt> prompts = testPromptList.getPromptList();

        assertEquals(1, prompts.size());
        assertTrue(prompts.contains(p2));
    }

    @Test
    public void testRemoveNoPrompt() {
        testPromptList.addPrompt(p1);
        testPromptList.removePrompt(p2);
        List<Prompt> prompts = testPromptList.getPromptList();

        assertTrue(prompts.contains(p1));
    }

    @Test
    public void testRemoveOnePrompt() {
        testPromptList.addPrompt(p1);
        testPromptList.removePrompt(p1);
        List<Prompt> prompts = testPromptList.getPromptList();

        assertEquals(0, prompts.size());
    }

    @Test
    public void testRemoveMultiplePrompts() {
        testPromptList.addPrompt(p1);
        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p3);
        testPromptList.removePrompt(p2);
        testPromptList.removePrompt(p1);
        List<Prompt> prompts = testPromptList.getPromptList();

        assertTrue(prompts.contains(p3));
        assertEquals(1, prompts.size());

    }

    @Test
    public void testViewPrompts() {
        assertEquals("What colour is chloroplast? Green",
                testPromptList.viewPrompts(p2));
    }

    @Test
    public void testTotalPromptsNoPrompts() {
        assertEquals(0, testPromptList.totalPrompts());
    }


    @Test
    public void testTotalPrompts() {
        testPromptList.addPrompt(p1);
        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p3);
        assertEquals(3, testPromptList.totalPrompts());
    }

    @Test
    public void testTotalPromptsWithDuplicate() {
        testPromptList.addPrompt(p1);
        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p2);
        assertEquals(2, testPromptList.totalPrompts());
    }

    @Test
    public void testTotalNoHardPrompts() {
        testPromptList.addPrompt(p3);
        assertEquals(0, testPromptList.totalHardPrompts());
    }

    @Test
    public void testTotalHardPrompts() {
        testPromptList.addPrompt(p1);
        testPromptList.addPrompt(p2);
        testPromptList.addPrompt(p3);
        assertEquals(2, testPromptList.totalHardPrompts());
    }


    @Test
    void testHashCode() {
        Prompt other = new Prompt("What is the powerhouse of the cell?", "Mitochondria", true);
        assertEquals(p1.hashCode(), other.hashCode());
    }

}