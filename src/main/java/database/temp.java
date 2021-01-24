package database;

import common.enums.CellType;
import common.enums.ObjectType;
import database.managers.AdminManager;
import database.managers.MapObjectManager;
import database.model.User;
import maps.api.Map;
import maps.api.MapObject;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class temp {
    public static void main(String[] args) {


//        IMapObject iMapObject = new MapObjectManager();
//        List<MapObject> mapObjectList = iMapObject.getAllObjects();
//        List<UUID> objectsUuids = new ArrayList<>();
//        objectsUuids.add(UUID.fromString("3b1d0275-d75e-4982-b687-83d3506aa19c"));
//        objectsUuids.add(UUID.fromString("cd9c7753-08f9-4449-aaf1-cedda17aa841"));
//        List<MapObject> mapObjectList = iMapObject.getMapObjectsForUuids(objectsUuids);

//        Map map = new Map();
//        map.setGuid(UUID.fromString("cc3a8647-6872-45e4-95ff-4d4e8958c463"));
//        map.setCreated(DateTime.now());
//        map.setModified(DateTime.now());
//        map.setUserId(1);
//        map.setName("first map");

//        AdminManager adminManager = new AdminManager();
//        List<Map> maps = adminManager.getMaps();
        IMapObject imo = new MapObjectManager();
        MapObject mp = imo.getObjectByUuid(UUID.fromString("3b1d0275-d75e-4982-b687-83d3506aa19c"));
        System.out.println(mp);
//        for (int i = 0; i < mapObjectList.size(); i++) {
//            mapObjectList.get(i).setGuid(UUID.randomUUID());
//            adminManager.addObject(mapObjectList.get(i));
//        }


    }
}
