package gamelogic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import gamelogic.placeholderdata.Map;
import gamelogic.placeholderdata.MapObject;
import gamelogic.placeholderdata.Cell;

public interface IGameLogic {

    public void placeObject(Point point, UUID mapItemID);
    public void moveObject(Point from, Point to);
    public void removeObject(Point point);
    ArrayList<ArrayList<Integer>> returnHeatMap();
    Map returnMap();
    List<MapObject> returnAvailableObjects(UUID itemID);
    List<Map> returnExistingUserMaps();
    List<Map> returnAvailableUserMaps();
    void loadExistingUserMap(UUID mapID);
    void createNewUserMap(UUID mapID);





}