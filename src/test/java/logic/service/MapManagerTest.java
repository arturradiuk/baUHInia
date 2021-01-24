package logic.service;

import logic.service.admin.AdminService;
import maps.api.MapObject;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MapManagerTest {


//    AdminService mapManager = new AdminService();
//    List<MapObject> mapObjectList = new ArrayList<>();
//    UUID tmpUUID;
//
//    @Test
//    @BeforeAll
//    public void init() {
//        // TEST DATA
//        mapObjectList.add(new MapObject("Test Object1", CellType.Green, 1, 1, UUID.randomUUID()));
//        mapObjectList.add(new MapObject("Test Object2", CellType.Green, 1, 2, UUID.randomUUID()));
//        mapObjectList.add(new MapObject("Test Object3", CellType.Road, 2, 1, UUID.randomUUID()));
//        Assertions.assertEquals(3, mapObjectList.size());
//    }
//
//    @Test
//    public void addingMap() {
//
//        //mapManager.addMap(10, mapObjectList);
//    }
//
//    @Test
//    public void removingMap() {
//       // mapManager.removeMap(tmpUUID);
//    }
//
//    @Test
//    public void updatingMap() {
////        mapObjectList.add(new MapObject("Test Object4", CellType.Building, 2, 2, UUID.randomUUID()));
////        mapManager.updateMap(tmpUUID, mapObjectList);
//    }
}
