package database;

import database.model.MapObject;
import database.model.User;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class temp {
    public static void main(String[] args) {
        ClientManager clientManager = new ClientManager(new Connector());
//        clientManager.getAllMapsForTheUser(1);

        Map<String, String> permissionsSet = new HashMap<>();
        permissionsSet.put("user_first_permission_key", "user_first_permission_value");
        permissionsSet.put("user_second_permission_key", "user_second_permission_value");
        permissionsSet.put("user_third_permission_key", "user_third_permission_value");

        Map<Point, UUID> objectsSet = new HashMap<>();
        objectsSet.put(new Point(34, 234), UUID.fromString("16a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(344, 344), UUID.fromString("26a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(444, 444), UUID.fromString("36a3afda-6722-4dc0-aaa4-17ab9d86252f"));

        MapObject mapObject = new MapObject(UUID.fromString("84dc976c-6a04-4616-9629-72f8ddbef514"), "third_map", objectsSet, permissionsSet);


        clientManager.addMapForTheUser(mapObject,2);

    }
}
