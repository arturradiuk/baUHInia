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

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class temp {
    public static void main(String[] args) {
        ClientManager clientManager = new ClientManager(new Connector());
//        clientManager.getAllMapsForTheUser(1);

        Map<String, String> permissionsSet = new HashMap<>();
        permissionsSet.put("key1", "value1");
        permissionsSet.put("key2", "value2");
        permissionsSet.put("key3", "value3");

        Map<Point, UUID> objectsSet = new HashMap<>();
        objectsSet.put(new Point(11, 11), UUID.fromString("16a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(22, 22), UUID.fromString("26a3afda-6722-4dc0-aaa4-17ab9d86252f"));


        MapObject mapObject = new MapObject(UUID.fromString("84dc976c-6a04-4616-9629-72f8ddbef514"), "third_map", objectsSet, permissionsSet);

        clientManager.addMapForTheUser(mapObject,39);

        objectsSet.put(new Point(33, 33), UUID.fromString("36a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        clientManager.updateMapForTheUser(mapObject,39);


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
