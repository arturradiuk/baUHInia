package gamelogic;

import database.DataBaseException;
import database.IClientData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.managers.ClientManager;
import maps.api.Map;
import maps.api.MapObject;
import maps.api.services.FilesystemMapsProvider;
import maps.api.services.IMapsService;
import maps.api.services.MapsService;
import simulation_logic.ISimulation;
import simulation_logic.Simulation;

public class GameLogic implements IGameLogic {
    Map map;
    int userID;
    IMapsService mapsService;
    ISimulation simulation; // Needs to be imported from simulation component
    IClientData clientData;

    public GameLogic(int userID) {
        this.userID = userID;
        mapsService = new MapsService(new FilesystemMapsProvider(".\\resources\\maps"));
        simulation = new Simulation(UUID.randomUUID());
        clientData = new ClientManager();

    }

    @Override
    public void placeObject(Point point, UUID mapItemID) throws DataBaseException {
        List<MapObject> mapObjects = clientData.getAllObjects();
        for(MapObject mapObject: mapObjects)
        {
            if(mapObject.getGuid().equals(mapItemID))
            {
                map.place(point.x,point.y,mapObject);
                return;
            }
        }
    }

    @Override
    public void moveObject(Point from, Point to) {
        MapObject objectToMove = map.get(from.x,from.y).getPlacedObjectMetadata().getMapObject();
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
        MapObject objectToRemove = map.get(point.x,point.y).getPlacedObjectMetadata().getMapObject();
        if(objectToRemove == null){
            return;
        }
        map.remove(objectToRemove);
    }

    @Override
    public ArrayList<ArrayList<Double>> returnHeatMap() {
        return simulation.runSimulation(map.getGuid());
    }

    @Override
    public Map returnMap() {
        return map;
    }

    @Override
    public java.util.List<MapObject> returnAvailableObjects() throws DataBaseException {
        return clientData.getAllObjects();
    }

    @Override
    public java.util.List<Map> returnExistingUserMaps() {
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

    @Override
    public void removeUserMap(UUID mapID) {
        clientData.removeMapForTheUser(map, userID);
    }

    @Override
    public int getTotalPrice(){
        int totalPrice = 0;
        for (MapObject i :map.getObjects()){
            totalPrice += i.getPrice();
        }
        return totalPrice;
    }
}
