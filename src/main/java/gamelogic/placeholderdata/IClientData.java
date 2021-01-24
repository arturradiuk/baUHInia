package gamelogic.placeholderdata;

import java.util.List;

public interface IClientData {
    void addMapForTheUser(Map map, int userId);
    List<Map> getAllMapsForTheUser(int userId);
    void updateMapForTheUser(Map mapObject, int userId);
    void removeMapForTheUser(Map mapObject, int userId);
    List<Map> getAllMaps();
    List<MapObject> getAllObjects();
}