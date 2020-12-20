package database;

import database.model.MapObject;
import database.model.PlaceableObject;

import java.util.List;

public class ClientManager implements IClientData {
    @Override
    public void addMapForTheUser(MapObject mapObject, int userId) {

    }

    @Override
    public List<MapObject> getAllMapsForTheUser(int userId) {
        return null;
    }

    @Override
    public void updateMapForTheUser(MapObject mapObject, int userId) {

    }

    @Override
    public List<MapObject> getAllMaps() {
        return null;
    }

    @Override
    public List<PlaceableObject> getAllObjects() {
        return null;
    }
}
