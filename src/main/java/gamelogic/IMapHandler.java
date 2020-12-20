package gamelogic;

import database.model.MapObject;
import database.model.PlaceableObject;
import database.model.PlaceableObject;

import java.awt.*;
import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

public interface IMapHandler {

    /**
     * places object on  specified field of map
     * @param objectID Id of object to be placed
     * @param point coordinates of field to place the object on
     */
    void placeObjectOnMap(UUID objectID, Point point) throws Exception;

    /**
     * removes object from the specified field of map
     * @param point coordinates of field to place the object on
     */
    void deleteObjectFromMap(Point point);

    /**
     * moves object from one field to another
     * @param from coordinates of field to move the object from
     * @param to  coordinates of field to place the object on
     */
    void moveObjectOnMap(Point from, Point to) throws Exception;

    /**
     * saves modified map
     */
    void saveMap() throws Exception;

    /**
     * returns all objects that are currently placed on map
     * @return all objects that are currently placed on map
     */
    Dictionary<Point,UUID> getObjectsFromMap() throws Exception;
    void loadMap(UUID mapID) throws Exception;
    MapObject getMap();
    void startSimulation();
    List<MapObject> getAllAvailableMaps();
}
