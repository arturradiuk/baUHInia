package database;

import maps.api.MapObject;

import java.util.List;
import java.util.UUID;

public interface IMapObject {
    List<MapObject> getMapObjectsForUuids(List<UUID> objetsUuids);
    List<MapObject> getAllObjects() throws DataBaseException;
    MapObject getObjectByUuid(UUID objectUuid);
}
