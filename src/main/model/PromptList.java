package model;

import java.util.ArrayList;


// Represents a list of prompts
public class PromptList {

    private ArrayList<Prompt> promptList;

    // EFFECTS: constructs a new list of prompts
    public PromptList() {
        promptList = new ArrayList<>();
    }

    // EFFECTS: returns prompt list
    public ArrayList<Prompt> getPromptList() {
        return promptList;
    }

    //EFFECTS: returns size of list
    public int getSize() {
        return promptList.size();
    }

    //EFFECTS: produces true if given prompt is in prompt list
    public boolean containsPrompt(Prompt p) {
        return promptList.contains(p);
    }

    // MODIFIES: this
    // EFFECTS: adds a new prompt to list of prompts that is not already in the list; otherwise do nothing
    public void addPrompt(Prompt p) {
        if (!promptList.contains(p)) {
            promptList.add(p);
        }
    }

    // REQUIRES: list of prompts cannot be empty
    // MODIFIES: this
    // EFFECTS: removes given prompt from list if it is in the list of prompts; otherwise do nothing
    public void removePrompt(Prompt p) {
        if (promptList.contains(p)) {
            promptList.remove(p);
        }
    }

    // EFFECTS: returns number of prompts in list
    public int totalPrompts() {
        return promptList.size();
    }

    // EFFECTS: returns question and answer of given prompt
    public String viewPrompts(Prompt p) {
        return p.getQuestion() + " " + p.getAnswer();
    }

    // EFFECTS: return number of hard prompts in list
    public int totalHardPrompts() {
        int count = 0;
        for (Prompt p : promptList) {
            if (p.getIsHard()) {
                count++;
            }
        }
        return count;
    }
}



