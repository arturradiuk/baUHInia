package database.model;

import netscape.javascript.JSObject;

import java.util.*;

public class PlacableObject {
    UUID id;
    String name;
    Dictionary<String ,String> parametersSet;

    public PlacableObject(UUID id, String name, Dictionary<String, String> parametersSet) {
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

    public void setParametersSet(Dictionary<String, String> parametersSet) {
        this.parametersSet = parametersSet;
    }

    public String getName() {
        return name;
    }

    public Dictionary<String, String> getParametersSet() {
        return parametersSet;
    }

    public Dictionary<String,String> getInfo(){
        Dictionary<String,String> output = new Hashtable<>();
        output.put("id",id.toString());
        output.put("name",name);
        Enumeration<String> keys = parametersSet.keys();
        while (keys.hasMoreElements())
        {
            String key = keys.nextElement();
            output.put(key,parametersSet.get(key));
        }
        return output;
    }
}
