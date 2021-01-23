package database;


import maps.api.Map;
import maps.api.MapObject;

import java.util.List;
import java.util.UUID;

public interface IAdminData {
    List<MapObject> getObjects();

    void addObject(MapObject obj);

    void removeObject(UUID objectID);

    void updateObject(UUID objectID, MapObject obj);

    List<Map> getMaps();

    void addMap(Map map);

    void removeMap(UUID mapID);

    void updateMap(UUID mapID, Map map);
}
