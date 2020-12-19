package database.model;

import java.awt.*;
import java.util.Map;
import java.util.UUID;

public class MapObject {
    private Map<String, String> permissionsSet;
    private Map<Point, UUID> objectsSet;
    private String name;
    private final UUID ID;

    public void setObjectsSet(Map<Point, UUID> objectsSet) {
        this.objectsSet = objectsSet;
    }

    public MapObject(UUID mapID, String name, Map<Point, UUID> objectsSet, Map<String, String> permissionsSet) {
        this.ID = mapID;
        this.name = name;
        this.objectsSet = objectsSet;
        this.permissionsSet = permissionsSet;
    }

    public Map<String, String> getPermissionsSet() {
        return permissionsSet;
    }

    public Map<Point, UUID> getObjectsSet() {
        return objectsSet;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return ID;
    }

    public void setPermissionsSet(Map<String, String> newPermissionsSet) {
    }

    public void addObject(UUID objectID, Point point) {
        if(containsPoint(point)) throw new RuntimeException("Point is already occupied in map");
        objectsSet.put(point, objectID);
    }

    public void removeObject(Point point) {
        if(!containsPoint(point)) throw new RuntimeException("Point does not exist in map");
        objectsSet.remove(point);
    }

    public void updateObject(Point point, UUID newObjectID) {
        if(!containsPoint(point)) throw new RuntimeException("Point does not exist in map");
        objectsSet.put(point, newObjectID);
    }

    public boolean containsPoint(Point point) {
        return objectsSet.containsKey(point);
    }
}
