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




    // EFFECTS: gets promptList; allows my QuizApp to have access to the promptList
    public ArrayList<Prompt> getPromptList() {
        return promptList;
    }





    //EFFECTS: return size of list
    public int getSize() {
        return promptList.size();
    }


    // MODIFIES: this
    // EFFECTS: adds a new prompt to list of prompts
    public void addPrompt(Prompt p) {
        promptList.add(p);
    }


    // EFFECTS: produces true if promptList contains p
    public boolean containsPrompt(Prompt p) {
        return promptList.contains(p);
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

    // EFFECTS: returns String of all prompts in the list
    public String viewPrompts(Prompt p) {
        return p.getQuestion() + " " + p.getAnswer();
    }
}



