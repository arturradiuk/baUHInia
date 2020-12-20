package database;

import database.model.MapObject;
import database.model.PlaceableObject;

import java.util.List;
import java.util.UUID;

public interface IAdminData {
    List<PlaceableObject> getObjects();

    void addObject(PlaceableObject obj);

    void removeObject(UUID objectID);

    void updateObject(UUID objectID, PlaceableObject obj);

    List<MapObject> getMaps();

    void addMap(MapObject mapObject);

    void removeMap(UUID mapID);

    void updateMap(UUID mapID, MapObject map);
}
