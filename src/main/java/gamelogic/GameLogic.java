package gamelogic;

import maps.api.Map;
import maps.api.MapObject;

import java.awt.*;
import java.util.List;
import java.util.UUID;

public class GameLogic implements IGameLogic {
    Object mapObject;

    @Override
    public void placeObject(Point point, UUID mapItemID) {

    }

    @Override
    public void moveObject(Point from, Point to) {

    }

    @Override
    public void removeObject(Point point) {

    }

    @Override
    public List<List<Double>> returnHeatMap() {
        return null;
    }

    @Override
    public Map returnMap() {
        return null;
    }

    @Override
    public MapObject returnMapItem(UUID itemID) {
        return null;
    }

    @Override
    public List<Map> returnExistingUserMaps(UUID userID) {
        return null;
    }

    @Override
    public List<Map> returnAvailableUserMaps() {
        return null;
    }

    @Override
    public void loadExistingUserMap(UUID userID, UUID mapID) {

    }

    @Override
    public void createNewUserMap(UUID mapID) {

    }
//    @Override
//    public void placeObject() {
//
//    }
//
//    @Override
//    public void moveObject() {
//
//    }
//
//    @Override
//    public void removeObject() {
//
//    }
}
