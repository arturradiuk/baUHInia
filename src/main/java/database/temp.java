package database;

import database.model.MapObject;
import database.model.User;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class temp {
    public static void main(String[] args) {
        AdminManager adminManager = new AdminManager(new Connector());
//        System.out.println(adminManager.getMaps());

        Map<String, String> permissionsSet = new HashMap<>();
        permissionsSet.put("first_permission_key", "first_permission_value");
        permissionsSet.put("second_permission_key", "second_permission_value");
        permissionsSet.put("third_permission_key", "third_permission_value");

        Map<Point, UUID> objectsSet = new HashMap<>();
        objectsSet.put(new Point(2,2),UUID.fromString("16a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(3,3),UUID.fromString("26a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(4,4),UUID.fromString("36a3afda-6722-4dc0-aaa4-17ab9d86252f"));

        MapObject mapObject = new MapObject(UUID.randomUUID(),"new_map_object",objectsSet,permissionsSet);
        adminManager.addMap(mapObject);

        mapObject.setName("hz map");
        adminManager.updateMap(mapObject.getId(),mapObject);

        adminManager.removeMap(mapObject.getId());


    }
}
