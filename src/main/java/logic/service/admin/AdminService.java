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
import java.util.List;
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
    public void createNewMapTemplate() {
        Map newMap = mapsService.generateMap();
        DateTime creationTime = DateTime.now();
        newMap.setCreated(creationTime);
        newMap.setModified(creationTime);
        // todo Catch exception from DataBase?
        adminDataBase.addMap(newMap);
        mapsService.saveMap(newMap);
    }

    @Override
    public List<Map> getMapTemplates() {
        return null;
    }

    @Override
    public List<Map> getAllMaps() {
        return null;
    }

    @Override
    public List<MapObject> getAllObjects() {
        return null;
    }

    @Override
    public void removeMap(UUID mapID) throws AdminException {
        try {
            Map deletedMap = adminDataBase.getMap(mapID);
            adminDataBase.removeMap(mapID);
            mapsService.deleteMap(deletedMap);
        } catch (DataBaseException ex) {
            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        }
    }

    @Override
    public void createMap(UUID mapTemplateID, UUID userID,
                          List<MapObjectInfo> objectInfos) throws AdminException {
//        try {
//            adminDataBase.getMap(mapID);
//        } catch (DataBaseException ex) {
//            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
//                throw new AdminException(ex);
//            }
//        }
//        Map updatedMap = Map.init(MAP_SIZE);
//        Enumeration<Cell> cells = cellMapObjectID.keys();
//
//        // todo check that loop logic !!! for uniqueness of objects in updatedMap.objects
//        while (cells.hasMoreElements()) {
//            Cell currentCell = cells.nextElement();
//            if (currentCell.getObjects() != null) {
//                updatedMap.place(currentCell.getObjects());
//            }
//        }
    }

    @Override
    public void addNewMapObject(String name, int width, int length, int height,
                                ObjectType type, CellType allowedTerrainType,
                                int price, double heatFactor) throws AdminException {
        // todo change MapObject constructor
        MapObject newMapObject =
                new MapObject(name, UUID.randomUUID(), width, length, height, type);
        newMapObject.setAllowedTerrainType(allowedTerrainType);
        newMapObject.setPrice(price);
        newMapObject.setHeatFactor(heatFactor);
        // todo can it throw an exception?
        adminDataBase.addObject(newMapObject);
    }

    @Override
    public void removeMapObject(UUID mapObjectID) throws AdminException {
        try {
            adminDataBase.removeObject(mapObjectID);
        } catch (DataBaseException ex) {
            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        }
        // todo Notify map component??
    }

    @Override
    public void updateMapObject(UUID mapObjectID, String name, int height,
                                ObjectType type, CellType allowedTerrainType,
                                int price, double heatFactor) throws AdminException {
        try {
            MapObject updatedMapObject =  adminDataBase.getObject(mapObjectID);
            updatedMapObject.setName(name);
            updatedMapObject.setHeight(height);
            updatedMapObject.setType(type);
            updatedMapObject.setAllowedTerrainType(allowedTerrainType);
            updatedMapObject.setPrice(price);
            updatedMapObject.setHeatFactor(heatFactor);

        } catch (DataBaseException ex) {
            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        }
        adminDataBase.updateObject(mapObjectID, updatedMapObject);
        // todo Notify map component??
    }

    @Override
    public void switchLockCell(UUID mapID, int cellX, int cellY) throws AdminException {
        Map updatedMap = mapsService.getMap(mapID);
        try {
            Cell modifiedCell = updatedMap.get(cellX, cellY);
            modifiedCell.setLockedByAdmin(!modifiedCell.getLockedByAdmin());
            updatedMap.setModified(DateTime.now());
            adminDataBase.updateMap(mapID, updatedMap);
            mapsService.saveMap(updatedMap);
        } catch (NullPointerException ex) {
            throw new AdminException(AdminException.NOT_EXIST);
        }

    }

    @Override
    public void updateCellType(UUID mapID, int cellX, int cellY, CellType cellType) throws AdminException {

    }

}
