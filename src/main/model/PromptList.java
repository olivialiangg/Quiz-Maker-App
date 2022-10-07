package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Represents a list of prompts
public class PromptList {

    private ArrayList<Prompt> promptList;


    // EFFECTS: constructs a new list of prompts
    public PromptList() {
        promptList = new ArrayList<>();
    }


    //EFFECTS: return size of list
    public int getSize() {
        return promptList.size();
    }

    // EFFECTS: produces true if promptList contain give p
    public boolean containsPrompt(Prompt p) {
        return promptList.contains(p);
    }


    // MODIFIES: this
    // EFFECTS: adds a new prompt to list of prompts
    public void addPrompt(Prompt p) {
        promptList.add(p);
    }


    // MODIFIES: this
    // EFFECTS: removes p from list; otherwise do nothing
    public void removePrompt(Prompt p) {
        if (promptList.contains(p)) {
            promptList.remove(p);
        }
    }

    // EFFECTS: returns number of prompts in list
    public int totalPrompts() {
        return promptList.size();
    }

    // REQUIRES: for the list to not be empty
    // EFFECTS: returns String of all prompts in the list
    public String viewPrompts(Prompt p) {
        return p.getQuestion() + " " + p.getAnswer();
    }
}



