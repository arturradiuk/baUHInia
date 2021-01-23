package database.model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MapObjectTest {

    @Test
    public void MapInit(){
        Map<Point, UUID> objectset = new HashMap<>();
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        PlaceableObject object1 = new PlaceableObject(uuid1,"Test Object1", null);
        PlaceableObject object2 = new PlaceableObject(uuid1,"Test Object2", null);
        objectset.put(new Point(0,0),uuid1);
        objectset.put(new Point(0,1),uuid2);
        MapObject mapObject = new MapObject(UUID.randomUUID(),"Test map", objectset, null);
        mapObject.toString();
    }
}
