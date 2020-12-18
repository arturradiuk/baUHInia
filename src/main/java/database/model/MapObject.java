package database.model;

import java.awt.*;
import java.util.Dictionary;
import java.util.UUID;

public class MapObject {
    private Dictionary<String, String> permissionsSet;
    private Dictionary<Point, UUID> objectsSet;
    private String name;
    UUID ID;

    public void setObjectsSet(Dictionary<Point, UUID> objectsSet) {
        this.objectsSet = objectsSet;
    }

    public MapObject(UUID mapID, String name, Dictionary<Point, UUID> objectsSet, Dictionary<String, String> permissionsSet) {
        this.ID = mapID;
        this.name = name;
        this.objectsSet = objectsSet;
        this.permissionsSet = permissionsSet;
    }

    public Dictionary<String, String> getPermissionsSet() {
        return permissionsSet;
    }

    public Dictionary<Point, UUID> getObjectsSet() {
        return objectsSet;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return ID;
    }

    public void setPermissionsSet(Dictionary<String, String> newPermissionsSet) {
    }
}
