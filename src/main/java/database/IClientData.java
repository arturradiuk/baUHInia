package database;


import maps.api.Map;
import maps.api.MapObject;

import java.util.List;

public interface IClientData {
    void addMapForTheUser(Map map, int userId); // user can have only the one instance of this particular map
    List<Map> getAllMapsForTheUser(int userId);
    void updateMapForTheUser(Map mapObject, int userId);
    void removeMapForTheUser(Map mapObject, int userId);
    List<Map> getAllMaps();
    List<MapObject> getAllObjects();
}
