package database;


import maps.api.CellType;
import maps.api.Map;
import maps.api.MapObject;
import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

public interface IAdminData {
    List<MapObject> getObjects();

    void addObject(String name, CellType type,int length,int width,int height);

    void removeObject(UUID objectID) throws DataBaseException;

    void updateObject(UUID objectID, MapObject obj);


    List<Map> getMaps();

    // in DB map table looks like:
    // Map (id, modify_datetime, creationDT, user_id?)
    // new Class MapInfo?
    void getMap(UUID mapID) throws DataBaseException;

    void addMap(DateTime modificationDateTime, DateTime creationDateTime, UUID userID);

    void removeMap(UUID mapID) throws DataBaseException;;

    void updateMap(UUID mapID, Map map);

    MapObject getObject(UUID mapObjectID) throws DataBaseException;
}
