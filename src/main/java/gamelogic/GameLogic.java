package gamelogic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import gamelogic.placeholderdata.*;

public class GameLogic implements IGameLogic {
    Map mapObject;
    UUID userID;
    IMapsService mapsService;
    IUserData userData;
    ISimulation simulation;

    public GameLogic(UUID userID) {
        this.userID = userID;
        mapsService = new MapsService();
    }

    @Override
    public void placeObject(Point point, UUID mapItemID) {
    }

    @Override
    public void moveObject(Point from, Point to) {
        MapObject objectToMove = mapObject.get(from.x,from.y).placedObjectMetadata.getMapObject();
        if(objectToMove == null)
        {
            return;
        }
        if(mapObject.validate(to.x,to.y,objectToMove)){
            mapObject.remove(objectToMove);
            mapObject.place(to.x,to.y,objectToMove);
        }
    }

    @Override
    public void removeObject(Point point) {
        MapObject objectToRemove = mapObject.get(point.x,point.y).placedObjectMetadata.getMapObject();
        if(objectToRemove == null){
            return;
        }
        mapObject.remove(objectToRemove);
    }

    @Override
    public ArrayList<ArrayList<Integer>> returnHeatMap() {
        return simulation.runSimulation();
    }

    @Override
    public Map returnMap() {
        return mapObject;
    }


    //TODO Database connection required for methods below
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
}
