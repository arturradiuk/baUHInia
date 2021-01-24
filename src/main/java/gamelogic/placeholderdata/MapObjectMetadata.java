package gamelogic.placeholderdata;

import java.util.UUID;

public class MapObjectMetadata {
    UUID mapObjectGuid;
    int x;
    int y;
    MapObject mapObject;

    public MapObjectMetadata(MapObject mapObject) {
        this.mapObject = mapObject;
    }

    public MapObject getMapObject() {
        return mapObject;
    }
}
