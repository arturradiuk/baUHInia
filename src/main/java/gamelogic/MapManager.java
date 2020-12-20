package gamelogic;

import database.ClientManager;
import database.IClientData;
import database.model.MapObject;
import database.model.PlaceableObject;
import jdk.jshell.spi.ExecutionControl;
import simulationlogic.ISimulation;

import java.awt.*;
import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

public class MapManager implements IMapHandler {
    private MapObject map;
    private IClientData clientData;
    private ISimulation simulation;
    private int userID;
    private boolean newMap;

    public MapManager( ISimulation simulation,int userID) {
        this.clientData = new ClientManager();
        this.simulation = simulation;
        this.userID=userID;
    }

    @Override
    public void placeObjectOnMap(UUID objectID, Point point) throws Exception {
        if(isFieldAvailable(point)){
            map.getObjectsSet().put(point,objectID);
            return;
        }
        throw new Exception("cannot move object to this field");

    }

    @Override
    public void deleteObjectFromMap(Point point) {
        map.getObjectsSet().put(point,null);
    }

    @Override
    public void moveObjectOnMap(Point from, Point to) throws Exception {
        UUID object = map.getObjectsSet().get(from);
        if(object==null)
        {
            throw new Exception("no object to be moved");
        }
        placeObjectOnMap(object,to);
        deleteObjectFromMap(from);

    }

    @Override
    public void saveMap() throws Exception {
        if(map==null)
        {
            throw new Exception("No map loaded");
        }
        if(newMap)
        {
            clientData.addMapForTheUser(map,userID);
            return;
        }
        clientData.updateMapForTheUser(map,userID);
    }

    @Override
    public Dictionary<Point,UUID> getObjectsFromMap() throws Exception {
        if(map==null)
        {
            throw new Exception("No map loaded");
        }
        return map.getObjectsSet();
    }

    @Override
    public void loadMap(UUID mapID) throws Exception {
        List<MapObject> maps=clientData.getAllMapsForTheUser(userID);
        map=null;
        for(MapObject mapObject : maps)
        {
            if(mapObject.getId()==mapID)
            {
                map=mapObject;
                newMap=false;
                return;
            }
        }
         maps=clientData.getAllMaps();
        for(MapObject mapObject : maps)
        {
            if(mapObject.getId()==mapID)
            {
                map=mapObject;
                newMap=true;
                return;
            }
        }

        throw new Exception("map not found");
    }

    @Override
    public MapObject getMap() {
        return map;
    }

    @Override
    public void startSimulation() {
        simulation.runSimulation();
    }

    @Override
    public List<MapObject> getAllAvailableMaps() {
        return clientData.getAllMaps();
    }


    /**
     * Checks if objects can be placed on the field of map
     * @param point coordinates of field
     * @return true if can be placed, false otherwise
     */
    private boolean isFieldAvailable(Point point) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("method not implemented");
    }
}
