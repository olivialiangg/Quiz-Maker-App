package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;


// Represents a list of prompts
public class PromptList implements Writable {

    private ArrayList<Prompt> promptList;
    private String name;

    // EFFECTS: constructs a new list of prompts
    public PromptList(String name) {
        promptList = new ArrayList<>();
        this.name = name;
    }

    // EFFECTS: returns prompt list
    public ArrayList<Prompt> getPromptList() {
        return promptList;
    }

    // EFFECTS: returns prompt list name
    public String getName() {
        return name;
    }


    // MODIFIES: this
    // EFFECTS: adds a new prompt to list of prompts that is not already in the list; otherwise do nothing
    public void addPrompt(Prompt p) {

        if (!promptList.contains(p)) {
            promptList.add(p);
            EventLog.getInstance().logEvent(new Event("Added prompt: "
                    + p.getQuestion() + " " + p.getAnswer() + " " + p.getIsHard() + " "));
        }
    }
//
//    public void filterPrompts() {
//        EventLog.getInstance().logEvent(new Event("Filtered list of prompts. "));
//    }

    // REQUIRES: list of prompts cannot be empty
    // MODIFIES: this
    // EFFECTS: removes given prompt from list if it is in the list of prompts; otherwise do nothing
    public void removePrompt(Prompt p) {
        EventLog.getInstance().logEvent(new Event("Filtered list of prompts. "));
        if (promptList.contains(p)) {
            promptList.remove(p);
        }
    }

//    public void removeGivenPrompt(Prompt p) {
//        EventLog.getInstance().logEvent(new Event("Filtered list of prompts. "));
//        promptList.remove(p);
//    }

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

    @Override
    public JSONObject toJason() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("promptList", promptListToJson());
        return json;
    }

    // EFFECTS: returns prompts in this workroom as a JSON array
    public JSONArray promptListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Prompt p: promptList) {
            jsonArray.put(p.toJason());
        }
        return jsonArray;
    }
}



