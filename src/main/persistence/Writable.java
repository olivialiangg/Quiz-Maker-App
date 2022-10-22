package persistence;

import org.json.JSONObject;

// Modeled code from provided JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJason();
}