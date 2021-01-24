package database;

import maps.api.MapObject;

import java.util.List;
import java.util.UUID;

public interface IMapObject {
    List<MapObject> getMapObjectsForUuids(List<UUID> objetsUuids);
    List<MapObject> getAllObjects();
    MapObject getObjectByUuid(UUID objectUuid);
}
