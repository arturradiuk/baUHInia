package database;

import database.managers.AdminManager;
import database.managers.MapObjectManager;
import database.model.User;
import maps.api.MapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class temp {
    public static void main(String[] args) {
        IMapObject iMapObject = new MapObjectManager();
//        List<MapObject> mapObjectList = iMapObject.getAllObjects();
        List<UUID> objectsUuids = new ArrayList<>();
        objectsUuids.add(UUID.fromString("3b1d0275-d75e-4982-b687-83d3506aa19c"));
        objectsUuids.add(UUID.fromString("cd9c7753-08f9-4449-aaf1-cedda17aa841"));
        List<MapObject> mapObjectList = iMapObject.getMapObjectsForUuids(objectsUuids);

        AdminManager adminManager =new AdminManager();

        for (int i = 0; i < mapObjectList.size(); i++) {
            mapObjectList.get(i).setGuid(UUID.randomUUID());
            adminManager.addObject(mapObjectList.get(i));
        }

    }
}
