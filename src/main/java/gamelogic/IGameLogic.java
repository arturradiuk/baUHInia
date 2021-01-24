package gamelogic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import gamelogic.placeholderdata.Map;
import gamelogic.placeholderdata.MapObject;
import gamelogic.placeholderdata.Cell;

public interface IGameLogic {

    void placeObject(Point point, UUID mapItemID);

    void moveObject(Point from, Point to);

    void removeObject(Point point);

    ArrayList<ArrayList<Integer>> returnHeatMap();

    Map returnMap();

    List<MapObject> returnAvailableObjects(UUID itemID);

    List<Map> returnExistingUserMaps();

    List<Map> returnAvailableUserMaps();

    void loadExistingUserMap(UUID mapID);

    void createNewUserMap(UUID mapID);

    void saveMap();

}
