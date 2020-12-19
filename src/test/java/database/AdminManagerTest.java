package database;

import database.model.PlaceableObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class AdminManagerTest {

    private AdminManager adminManager = new AdminManager(new Connector());

//    @Test
//    public void getPlaceableObjectTest() {
//        AdminManager adminManager = new AdminManager(new Connector());
//        List<PlaceableObject> expected = Arrays.asList(new PlaceableObject(UUID.randomUUID(), "boisko", new HashMap<>() {{
//            put("key1", "value1");
//        }}));
//        expected.setName("park");
//        List<PlaceableObject> actual = adminManager.getObjects();
//        Assert.assertEquals(expected, actual);
//    }
//        expected = new PlaceableObject("bolek", "bolek", "bolek@gmail.com", "56789", "General");
//        expected.setId(2);
//        actual = adminManager.getUser("bolek@gmail.com", "56789");
//        Assert.assertEquals(expected, actual);
//
//        expected = new PlaceableObject("tola", "tola", "tola@gmail.com", "qwe123", "Administrator");
//        expected.setId(3);
//        actual = adminManager.getUser("tola@gmail.com", "qwe123");
//        Assert.assertEquals(expected, actual);
//
//        actual = adminManager.getUser("tola@gmail.com", "12345"); // bad password
//        Assert.assertNull(actual);
//    }

    @Test
    public void addPlaceableObjectTest() {
        AdminManager adminManager = new AdminManager(new Connector());
        UUID id = UUID.randomUUID();
        PlaceableObject obj = new PlaceableObject(id, "boisko", new HashMap<>() {{
            put("key1", "value1");
        }});
        Assert.assertEquals(true, adminManager.addObject(obj)); // false because user with this email exists

        obj = new PlaceableObject(id, "boisko", new HashMap<>() {{
            put("key1", "value1");
            put("key2", "value2");
        }});
        Assert.assertEquals(false, adminManager.addObject(obj));
        Assert.assertEquals(true, adminManager.removeObject(id));
    }

    @Test
    public void deletePlaceableObjectTest() {
        AdminManager adminManager = new AdminManager(new Connector());
        UUID id = UUID.randomUUID();
        PlaceableObject obj = new PlaceableObject(id, "boisko", new HashMap<>() {{
            put("key1", "value1");
            put("key2", "value2");
        }});
        Assert.assertEquals(true, adminManager.addObject(obj));
        Assert.assertEquals(true, adminManager.removeObject(id));
    }

    @Test
    public void updatePlaceableObjectTest() {
        AdminManager adminManager = new AdminManager(new Connector());
        UUID id = UUID.randomUUID();
        PlaceableObject obj = new PlaceableObject(id, "boisko", new HashMap<>() {{
            put("key1", "value1");
            put("key2", "value2");
        }});
        obj.setName("park");
        Assert.assertEquals(true, adminManager.updateObject(id, obj));
    }

    @Test
    public void testGetMaps() {
        Map<String, String> permissionsSet = new HashMap<>();
        permissionsSet.put("first_permission_key", "first_permission_value");
        permissionsSet.put("second_permission_key", "second_permission_value");
        permissionsSet.put("third_permission_key", "third_permission_value");

        Map<Point, UUID> objectsSet = new HashMap<>();
        objectsSet.put(new Point(2, 2), UUID.fromString("16a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(3, 3), UUID.fromString("26a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(4, 4), UUID.fromString("36a3afda-6722-4dc0-aaa4-17ab9d86252f"));

        MapObject mapObject = new MapObject(UUID.fromString("46a3afda-6722-4dc0-aaa4-17ab9d86252f"), "new_map_object", objectsSet, permissionsSet);
        this.adminManager.addMap(mapObject);

        List<MapObject> map = this.adminManager.getMaps();
        assertEquals(3, map.size());
        this.adminManager.removeMap(mapObject.getId());
    }

    @Test
    public void testAddMap() {
        Map<String, String> permissionsSet = new HashMap<>();
        permissionsSet.put("first_permission_key", "first_permission_value");
        permissionsSet.put("second_permission_key", "second_permission_value");
        permissionsSet.put("third_permission_key", "third_permission_value");

        Map<Point, UUID> objectsSet = new HashMap<>();
        objectsSet.put(new Point(2, 2), UUID.fromString("16a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(3, 3), UUID.fromString("26a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(4, 4), UUID.fromString("36a3afda-6722-4dc0-aaa4-17ab9d86252f"));

        MapObject mapObject = new MapObject(UUID.fromString("46a3afda-6722-4dc0-aaa4-17ab9d86252f"), "new_map_object", objectsSet, permissionsSet);
        this.adminManager.addMap(mapObject);

        List<MapObject> map = this.adminManager.getMaps();
        assertEquals(3, map.size());
        MapObject actual = map.stream().filter(m -> m.getId().toString().equals("46a3afda-6722-4dc0-aaa4-17ab9d86252f")).findAny().get();
        assertEquals(mapObject, actual);
        this.adminManager.removeMap(mapObject.getId());
    }

    @Test
    public void testRemoveMap() {
        Map<String, String> permissionsSet = new HashMap<>();
        permissionsSet.put("first_permission_key", "first_permission_value");
        permissionsSet.put("second_permission_key", "second_permission_value");
        permissionsSet.put("third_permission_key", "third_permission_value");

        Map<Point, UUID> objectsSet = new HashMap<>();
        objectsSet.put(new Point(2, 2), UUID.fromString("16a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(3, 3), UUID.fromString("26a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(4, 4), UUID.fromString("36a3afda-6722-4dc0-aaa4-17ab9d86252f"));

        MapObject mapObject = new MapObject(UUID.fromString("46a3afda-6722-4dc0-aaa4-17ab9d86252f"), "new_map_object", objectsSet, permissionsSet);
        this.adminManager.addMap(mapObject);

        List<MapObject> map = this.adminManager.getMaps();
        assertEquals(3, map.size());
        MapObject actual = map.stream().filter(m -> m.getId().toString().equals("46a3afda-6722-4dc0-aaa4-17ab9d86252f")).findAny().get();
        assertEquals(mapObject, actual);
        this.adminManager.removeMap(mapObject.getId());
        map = this.adminManager.getMaps();
        assertEquals(2, map.size());

    }

    @Test
    public void testUpdateMap() {
        Map<String, String> permissionsSet = new HashMap<>();
        permissionsSet.put("first_permission_key", "first_permission_value");
        permissionsSet.put("second_permission_key", "second_permission_value");
        permissionsSet.put("third_permission_key", "third_permission_value");

        Map<Point, UUID> objectsSet = new HashMap<>();
        objectsSet.put(new Point(2, 2), UUID.fromString("16a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(3, 3), UUID.fromString("26a3afda-6722-4dc0-aaa4-17ab9d86252f"));
        objectsSet.put(new Point(4, 4), UUID.fromString("36a3afda-6722-4dc0-aaa4-17ab9d86252f"));

        MapObject mapObject = new MapObject(UUID.fromString("46a3afda-6722-4dc0-aaa4-17ab9d86252f"), "new_map_object", objectsSet, permissionsSet);
        this.adminManager.addMap(mapObject);

        List<MapObject> map = this.adminManager.getMaps();
        assertEquals(3, map.size());
        MapObject actual = map.stream().filter(m -> m.getId().toString().equals("46a3afda-6722-4dc0-aaa4-17ab9d86252f")).findAny().get();
        assertEquals(mapObject, actual);

        mapObject.setName("new name");
        adminManager.updateMap(mapObject.getId(), mapObject);
        map = this.adminManager.getMaps();
        actual = map.stream().filter(m -> m.getId().toString().equals("46a3afda-6722-4dc0-aaa4-17ab9d86252f")).findAny().get();

        assertEquals("new name", actual.getName());
        assertEquals(mapObject, actual);

        this.adminManager.removeMap(mapObject.getId());


    }
}