package persistence;

import model.Prompt;
import model.PromptList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Modeled code from provided JsonSerializationDemo
// Represents a reader that reads a list of prompts from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads prompt list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PromptList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePromptList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses prompt list from JSON object and returns it
    private PromptList parsePromptList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        PromptList prompts = new PromptList(name);
        addPrompts(prompts, jsonObject);
        return prompts;
    }

    // MODIFIES: this
    // EFFECTS: parses prompts from JSON object and adds them to file
    private void addPrompts(PromptList prompts, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("promptList");
        for (Object json : jsonArray) {
            JSONObject nextPrompt = (JSONObject) json;
            addPrompt(prompts, nextPrompt);
        }
    }

    // MODIFIES: this
    // EFFECTS: parses prompt from JSON object and adds it to file
    private void addPrompt(PromptList prompts, JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        String answer = jsonObject.getString("answer");
        Boolean isHard = jsonObject.getBoolean("isHard");
        Prompt prompt = new Prompt(question, answer, isHard);
        prompts.addPrompt(prompt);
    }
}