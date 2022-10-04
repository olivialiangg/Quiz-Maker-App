package model;

// Represents a prompt having a question and answer
public class Prompt {
    private String question;
    private String answer;

    // EFFECTS: constructs a prompt with a question and answer
    public Prompt(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }


    //getters
    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }
}

