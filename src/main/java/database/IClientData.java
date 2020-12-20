package database;

import database.model.MapObject;
import database.model.PlaceableObject;

import java.util.List;
import java.util.Map;

/**
 * This is temporary IClientData interface and should be replaced
 */
public interface IClientData {
    void addMapForTheUser(MapObject mapObject, int userId); // user can have only the one instance of this particular map
    List<MapObject> getAllMapsForTheUser(int userId);
    void updateMapForTheUser(MapObject mapObject,int userId);
    List<MapObject> getAllMaps();
    List<PlaceableObject> getAllObjects();
}