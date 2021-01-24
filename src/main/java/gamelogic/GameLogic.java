package gamelogic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import gamelogic.placeholderdata.*;

public class GameLogic implements IGameLogic {
    Map mapObject;
    int userID;
    IMapsService mapsService;
    IUserData userData;
    ISimulation simulation;
    IClientData clientData;

    public GameLogic(int userID) {
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

    @Override
    public MapObject returnMapItem(UUID itemID) {
        return null;
    }

    @Override
    public List<Map> returnExistingUserMaps() {
        return clientData.getAllMapsForTheUser(userID);
    }

    @Override
    public List<Map> returnAvailableUserMaps() {
        return clientData.getAllMaps();
    }

    @Override
    public void loadExistingUserMap(UUID mapID) {
        mapsService.getMap(mapID);

    }

    //TODO No mapservice map generation function implemented?
    @Override
    public void createNewUserMap(UUID templateMapID) {


    }
}
