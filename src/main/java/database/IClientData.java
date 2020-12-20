package database;

import database.model.MapObject;
import database.model.PlaceableObject;

import java.util.List;
import java.util.UUID;

/**
 * This is temporary IClientData interface and should be replaced
 */
public interface IClientData {

    List<MapObject> getMaps();

    void addMap();

    void updateMap(UUID mapID, MapObject map);

    List<PlaceableObject> getObjects();

}
