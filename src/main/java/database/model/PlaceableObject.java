package database.model;

import netscape.javascript.JSObject;

import java.util.*;

/**
 * This is temporary PlaceableObject class and should be replaced
 */
public class PlaceableObject {
    private final UUID id;
    private String name;
    private Map<String, String> parametersSet;

    public PlaceableObject(UUID id, String name, Map<String, String> parametersSet) {
        this.id = id;
        this.name = name;
        this.parametersSet = parametersSet;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParametersSet(Map<String, String> parametersSet) {
        this.parametersSet = parametersSet;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getParametersSet() {
        return parametersSet;
    }

    public Map<String, String> getInfo() {
        Map<String, String> output = new HashMap<>();
        output.put("id", id.toString());
        output.put("name", name);
        output.putAll(parametersSet);
        return output;
    }

    public PlaceableObject copyObject(UUID newID) { //copying current object with new ID
        return new PlaceableObject(newID, this.name, this.parametersSet);
    }
}