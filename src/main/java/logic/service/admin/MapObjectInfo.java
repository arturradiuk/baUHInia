package logic.service.admin;

import java.util.UUID;

public class MapObjectInfo {
    private final int x;
    private final int y;
    private final UUID mapObjectID;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public UUID getMapObjectID() {
        return mapObjectID;
    }

    public MapObjectInfo(int x, int y, UUID mapObjectID) {
        this.x = x;
        this.y = y;
        this.mapObjectID = mapObjectID;
    }
}
