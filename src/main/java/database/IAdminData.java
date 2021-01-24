package database;


import common.enums.CellType;
import maps.api.Map;
import maps.api.MapObject;
import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

public interface IAdminData {
    List<MapObject> getObjects() throws DataBaseException;

    public void addObject(MapObject obj);

    void removeObject(UUID objectID) throws DataBaseException;

    void updateObject(UUID objectID, MapObject obj);


    List<Map> getMaps() throws DataBaseException;

    // in DB map table looks like:
    // Map (id, modify_datetime, creationDT, user_id?)
    // new Class MapInfo?
    Map getMap(UUID mapID) throws DataBaseException;

    void addMap(Map map);

    void removeMap(UUID mapID) throws DataBaseException;;

    void updateMap(UUID mapID, Map map);

    MapObject getObject(UUID mapObjectID) throws DataBaseException;
}
