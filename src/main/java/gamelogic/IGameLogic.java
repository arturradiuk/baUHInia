package gamelogic;

import database.DataBaseException;
import maps.api.Map;
import maps.api.MapObject;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IGameLogic {

    void placeObject(Point point, UUID mapItemID);

    void moveObject(Point from, Point to);

    void removeObject(Point point);

    ArrayList<ArrayList<Integer>> returnHeatMap();

    Map returnMap();  // Needs to be imported from maps component

    List<MapObject> returnAvailableObjects() throws DataBaseException;  // Needs to be imported from maps component

    List<Map> returnExistingUserMaps();  // Needs to be imported from maps component

    List<Map> returnAvailableUserMaps();  // Needs to be imported from maps component

    void loadExistingUserMap(UUID mapID);

    void createNewUserMap(UUID mapID);

    void saveMap();

    void removeUserMap(UUID mapID);

}
