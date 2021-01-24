package gamelogic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import gamelogic.placeholderdata.*;

public class GameLogic implements IGameLogic {
    Map map;
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
        MapObject objectToMove = map.get(from.x,from.y).placedObjectMetadata.getMapObject();
        if(objectToMove == null)
        {
            return;
        }
        if(map.validate(to.x,to.y,objectToMove)){
            map.remove(objectToMove);
            map.place(to.x,to.y,objectToMove);
        }
    }

    @Override
    public void removeObject(Point point) {
        MapObject objectToRemove = map.get(point.x,point.y).placedObjectMetadata.getMapObject();
        if(objectToRemove == null){
            return;
        }
        map.remove(objectToRemove);
    }

    @Override
    public ArrayList<ArrayList<Integer>> returnHeatMap() {
        return simulation.runSimulation();
    }

    @Override
    public Map returnMap() {
        return map;
    }

    @Override
    public List<MapObject> returnAvailableObjects(UUID itemID) {
        return clientData.getAllObjects();
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
        map = mapsService.getMap(mapID);

    }


    @Override
    public void createNewUserMap(UUID templateMapID) {
        map = mapsService.fromTemplate(templateMapID);
        clientData.addMapForTheUser(map,userID);
        mapsService.saveMap(map);


    }
    @Override
    public void saveMap(){
        mapsService.saveMap(map);
    }
}
