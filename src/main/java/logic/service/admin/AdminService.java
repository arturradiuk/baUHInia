package logic.service.admin;

import common.enums.ObjectType;
import database.IAdminData;
import database.DataBaseException;
import maps.api.services.MapsService;
import maps.api.Cell;
import common.enums.CellType;
import maps.api.Map;
import maps.api.MapObject;
import org.joda.time.DateTime;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.UUID;


public class AdminService implements IAdminLogic {

    // todo Check Admin authorization?

    // Hardcoded static MapSize
    private final static int MAP_SIZE = 100;

    // Database Access
    private IAdminData adminDataBase;

    // Maps Component Access
    private MapsService mapsService;

    @Override
    public void addNewMap() {
        Map newMap = mapsService.generateMap();
        // todo Catch exception from DataBase?
        DateTime currentDateTime = DateTime.now();
        adminDataBase.addMap(currentDateTime, currentDateTime, null);
        mapsService.saveMap(newMap);
    }

    @Override
    public void removeMap(UUID mapID) throws AdminException {
        try {
            adminDataBase.removeMap(mapID);
        } catch (DataBaseException ex) {
            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        }
        mapsService.deleteMap(mapID);
    }

    @Override
    public void updateMap(UUID mapID, Dictionary<Cell, UUID> cellMapObjectID) throws AdminException {
        try {
            adminDataBase.getMap(mapID);
        } catch (DataBaseException ex) {
            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        }
        Map updatedMap = Map.init(MAP_SIZE);
        Enumeration<Cell> cells = cellMapObjectID.keys();

        // todo check that loop logic !!! for uniqueness of objects in updatedMap.objects
        while (cells.hasMoreElements()) {
            Cell currentCell = cells.nextElement();
            if (currentCell.getObjects() != null) {
                updatedMap.place(currentCell.getObjects());
            }
        }
    }

    @Override
    public void addNewMapObject(String name, ObjectType type, int length, int width, int height) throws AdminException {
        // todo change MapObject constructor
        MapObject newMapObject = new MapObject(name, UUID.randomUUID(), width, length, height, type);
        // todo can it throw an exception?
        adminDataBase.addObject(newMapObject);
    }

    @Override
    public void removeMapObject(UUID mapObjectID) throws AdminException {
        try {
            adminDataBase.removeObject(mapObjectID);
        }
        catch (DataBaseException ex) {
            if(ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        }
        // todo Notify map component??
    }

    @Override
    public void updateMapObject(UUID mapObjectID, String name, CellType type, int length, int width, int height) throws AdminException {
        try {
            adminDataBase.getObject(mapObjectID);
        }
        catch (DataBaseException ex) {
            if(ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        }
        MapObject updatedMapObject = new MapObject(name, type, length, width, height);
        adminDataBase.updateObject(mapObjectID, updatedMapObject);
        // todo Notify map component??
    }
}
