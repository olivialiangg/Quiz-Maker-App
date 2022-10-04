package model;

import java.util.ArrayList;

// Represents a list of prompts
public class PromptList {

    private ArrayList<Prompt> promptList;


    // EFFECTS: constructs a new list of prompts
    public PromptList() {
        promptList = new ArrayList<>();
    }

    //getters
    public int getSize() {
        return promptList.size();
    }




    // MODIFIES: this
    // EFFECTS: adds a new prompt to list of prompts
    public void addPrompt(Prompt p) {
    }


    // MODIFIES: this
    // EFFECTS: removes p from list; otherwise do nothing
    public void removePrompt(Prompt p) {
    }


    // EFFECTS: returns String of all prompts in the list
    public String viewPrompts() {
        return "";
    }

    // REQUIRES: list cannot be empty
    // EFFECTS: returns number of prompts in list
    public int totalPrompts() {
        return 0;
    }
}
