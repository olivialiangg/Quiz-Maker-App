package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// Represents a prompt having a question and answer and a state of being either hard or not hard
public class Prompt implements Writable {
    private String question;
    private String answer;
    private boolean isHard;   // true means the prompt is hard

    // EFFECTS: constructs a prompt with a question and answer
    public Prompt(String question, String answer, Boolean isHard) {
        this.question = question;
        this.answer = answer;
        this.isHard = isHard;
    }

    //getters
    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public boolean getIsHard() {
        return isHard;

    }


    // Used Java's generate equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Prompt prompt = (Prompt) o;
        return isHard == prompt.isHard && Objects.equals(question, prompt.question)
                && Objects.equals(answer, prompt.answer);
    }


    @Override
    public int hashCode() {
        return Objects.hash(question, answer, isHard);
    }

    @Override
    public JSONObject toJason() {
        JSONObject json = new JSONObject();
        json.put("question", question);
        json.put("answer", answer);
        json.put("isHard", isHard);
        return json;
    }
}

