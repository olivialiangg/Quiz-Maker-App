package model;

import java.util.Objects;

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
    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    // Used Java's generate equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prompt prompt = (Prompt) o;
        return Objects.equals(question, prompt.question) && Objects.equals(answer, prompt.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

}

