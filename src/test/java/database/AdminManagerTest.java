package database;

public class AdminManagerTest {

 //   private AdminManager adminManager = new AdminManager(new Connector());

//    @Test
//    public void getObjectTest() {
//        PlaceableObject obj = new PlaceableObject(UUID.randomUUID(), "boisko", new HashMap<>() {{
//            put("key1", "value1");
//        }});
//        adminManager.addObject(obj);
//        List<PlaceableObject> objects = adminManager.getObjects();
//        assertEquals(4, objects.size());
//        adminManager.removeObject(obj.getId());
//    }
//
//    @Test
//    public void addObjectTest() {
//        UUID id = UUID.randomUUID();
//        PlaceableObject obj = new PlaceableObject(id, "boisko", new HashMap<>() {{
//            put("key1", "value1");
//        }});
//        adminManager.addObject(obj);
//
//        List<PlaceableObject> objects = adminManager.getObjects();
//        assertEquals(4, objects.size());
//        adminManager.removeObject(id);
//    }
//
//    @Test
//    public void deleteObjectTest() {
//        PlaceableObject obj = new PlaceableObject(UUID.randomUUID(), "boisko", new HashMap<>() {{
//            put("key1", "value1");
//            put("key2", "value2");
//        }});
//        adminManager.addObject(obj);
//        List<PlaceableObject> objects = adminManager.getObjects();
//        assertEquals(4, objects.size());
//
//        adminManager.removeObject(obj.getId());
//        objects = adminManager.getObjects();
//        assertEquals(3, objects.size());
//    }
//
//    @Test
//    public void updateObjectTest() {
//        PlaceableObject obj = new PlaceableObject(UUID.randomUUID(), "boisko", new HashMap<>() {{
//            put("key1", "value1");
//            put("key2", "value2");
//        }});
//        adminManager.addObject(obj);
//        List<PlaceableObject> objects = adminManager.getObjects();
//        assertEquals(4, objects.size());
//
//        obj.setName("park");
//        adminManager.updateObject(obj.getId(), obj);
//        objects = adminManager.getObjects();
//        PlaceableObject actual = objects.stream().filter(o -> o.getId().toString().equals(obj.getId().toString())).findAny().get();
//        assertEquals("park", actual.getName());
//
//        adminManager.removeObject(obj.getId());
//    }
//
//    @Test
//    public void testGetMaps() {
//        Map<String, String> permissionsSet = new HashMap<>();
//        permissionsSet.put("first_permission_key", "first_permission_value");
//        permissionsSet.put("second_permission_key", "second_permission_value");
//        permissionsSet.put("third_permission_key", "third_permission_value");
//
//        Map<Point, UUID> objectsSet = new HashMap<>();
//        objectsSet.put(new Point(2, 2), UUID.fromString("16a3afda-6722-4dc0-aaa4-17ab9d86252f"));
//        objectsSet.put(new Point(3, 3), UUID.fromString("26a3afda-6722-4dc0-aaa4-17ab9d86252f"));
//        objectsSet.put(new Point(4, 4), UUID.fromString("36a3afda-6722-4dc0-aaa4-17ab9d86252f"));
//
//        MapObject mapObject = new MapObject(UUID.fromString("46a3afda-6722-4dc0-aaa4-17ab9d86252f"), "new_map_object", objectsSet, permissionsSet);
//        this.adminManager.addMap(mapObject);
//
//        List<MapObject> map = this.adminManager.getMaps();
//        assertEquals(3, map.size());
//        this.adminManager.removeMap(mapObject.getId());
//    }
//
//    @Test
//    public void testAddMap() {
//        Map<String, String> permissionsSet = new HashMap<>();
//        permissionsSet.put("first_permission_key", "first_permission_value");
//        permissionsSet.put("second_permission_key", "second_permission_value");
//        permissionsSet.put("third_permission_key", "third_permission_value");
//
//        Map<Point, UUID> objectsSet = new HashMap<>();
//        objectsSet.put(new Point(2, 2), UUID.fromString("16a3afda-6722-4dc0-aaa4-17ab9d86252f"));
//        objectsSet.put(new Point(3, 3), UUID.fromString("26a3afda-6722-4dc0-aaa4-17ab9d86252f"));
//        objectsSet.put(new Point(4, 4), UUID.fromString("36a3afda-6722-4dc0-aaa4-17ab9d86252f"));
//
//        MapObject mapObject = new MapObject(UUID.fromString("46a3afda-6722-4dc0-aaa4-17ab9d86252f"), "new_map_object", objectsSet, permissionsSet);
//        this.adminManager.addMap(mapObject);
//
//        List<MapObject> map = this.adminManager.getMaps();
//        assertEquals(3, map.size());
//        MapObject actual = map.stream().filter(m -> m.getId().toString().equals("46a3afda-6722-4dc0-aaa4-17ab9d86252f")).findAny().get();
//        assertEquals(mapObject, actual);
//        this.adminManager.removeMap(mapObject.getId());
//    }
//
//    @Test
//    public void testRemoveMap() {
//        Map<String, String> permissionsSet = new HashMap<>();
//        permissionsSet.put("first_permission_key", "first_permission_value");
//        permissionsSet.put("second_permission_key", "second_permission_value");
//        permissionsSet.put("third_permission_key", "third_permission_value");
//
//        Map<Point, UUID> objectsSet = new HashMap<>();
//        objectsSet.put(new Point(2, 2), UUID.fromString("16a3afda-6722-4dc0-aaa4-17ab9d86252f"));
//        objectsSet.put(new Point(3, 3), UUID.fromString("26a3afda-6722-4dc0-aaa4-17ab9d86252f"));
//        objectsSet.put(new Point(4, 4), UUID.fromString("36a3afda-6722-4dc0-aaa4-17ab9d86252f"));
//
//        MapObject mapObject = new MapObject(UUID.fromString("46a3afda-6722-4dc0-aaa4-17ab9d86252f"), "new_map_object", objectsSet, permissionsSet);
//        this.adminManager.addMap(mapObject);
//
//        List<MapObject> map = this.adminManager.getMaps();
//        assertEquals(3, map.size());
//        MapObject actual = map.stream().filter(m -> m.getId().toString().equals("46a3afda-6722-4dc0-aaa4-17ab9d86252f")).findAny().get();
//        assertEquals(mapObject, actual);
//        this.adminManager.removeMap(mapObject.getId());
//        map = this.adminManager.getMaps();
//        assertEquals(2, map.size());
//
//    }
//
//    @Test
//    public void testUpdateMap() {
//        Map<String, String> permissionsSet = new HashMap<>();
//        permissionsSet.put("first_permission_key", "first_permission_value");
//        permissionsSet.put("second_permission_key", "second_permission_value");
//        permissionsSet.put("third_permission_key", "third_permission_value");
//
//        Map<Point, UUID> objectsSet = new HashMap<>();
//        objectsSet.put(new Point(2, 2), UUID.fromString("16a3afda-6722-4dc0-aaa4-17ab9d86252f"));
//        objectsSet.put(new Point(3, 3), UUID.fromString("26a3afda-6722-4dc0-aaa4-17ab9d86252f"));
//        objectsSet.put(new Point(4, 4), UUID.fromString("36a3afda-6722-4dc0-aaa4-17ab9d86252f"));
//
//        MapObject mapObject = new MapObject(UUID.fromString("46a3afda-6722-4dc0-aaa4-17ab9d86252f"), "new_map_object", objectsSet, permissionsSet);
//        this.adminManager.addMap(mapObject);
//
//        List<MapObject> map = this.adminManager.getMaps();
//        assertEquals(3, map.size());
//        MapObject actual = map.stream().filter(m -> m.getId().toString().equals("46a3afda-6722-4dc0-aaa4-17ab9d86252f")).findAny().get();
//        assertEquals(mapObject, actual);
//
//        mapObject.setName("new name");
//        adminManager.updateMap(mapObject.getId(), mapObject);
//        map = this.adminManager.getMaps();
//        actual = map.stream().filter(m -> m.getId().toString().equals("46a3afda-6722-4dc0-aaa4-17ab9d86252f")).findAny().get();
//
//        assertEquals("new name", actual.getName());
//        assertEquals(mapObject, actual);
//
//        this.adminManager.removeMap(mapObject.getId());
//
//
//    }
}