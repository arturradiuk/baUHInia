package gameGUI.clientControllers;

import database.DataBaseException;
import gamelogic.GameLogic;
import gamelogic.IGameLogic;
import maps.api.Map;
import maps.api.MapObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientManager implements IGameLogic {

    private final GameLogic gameLogic;

    public ClientManager() {
        this.gameLogic = new GameLogic(3);
    }

    public Map getMap() {
        return gameLogic.getMap();
    }

    @Override
    public void placeObject(Point point, UUID mapItemID) throws DataBaseException {
        gameLogic.placeObject(point, mapItemID);
    }

    @Override
    public void moveObject(Point from, Point to) {

    }

    @Override
    public void removeObject(Point point) {
        gameLogic.removeObject(point);
    }

    @Override
    public ArrayList<ArrayList<Double>> returnHeatMap() {
        return gameLogic.returnHeatMap();
    }

    @Override
    public Map returnMap() {
        return null;
    }

    @Override
    public List<MapObject> returnAvailableObjects() throws DataBaseException {
        return gameLogic.returnAvailableObjects();
    }

    @Override
    public List<Map> returnExistingUserMaps() {
        return gameLogic.returnExistingUserMaps();
    }

    @Override
    public List<Map> returnAvailableUserMaps() {
        return gameLogic.returnAvailableUserMaps();
    }

    @Override
    public void loadExistingUserMap(UUID mapID) {
    }

    @Override
    public void createNewUserMap(UUID mapID) {
        gameLogic.createNewUserMap(mapID);
    }

    @Override
    public void saveMap() {
        gameLogic.saveMap();
    }

    @Override
    public void removeUserMap(UUID mapID) {

    }

    @Override
    public int getTotalPrice() {
        return 0;
    }
}
