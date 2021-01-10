package database.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class PlaceableObject_deprecated {
    private final UUID id;
    private String name;
    private Map<String, String> parametersSet;

    public PlaceableObject_deprecated(UUID id, String name, Map<String, String> parametersSet) {
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

    public PlaceableObject_deprecated copyObject(UUID newID) { //copying current object with new ID
        return new PlaceableObject_deprecated(newID, this.name, this.parametersSet);
    }
}
