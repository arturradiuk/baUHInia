package logic.service.admin;

import common.enums.ObjectType;
import database.IAdminData;
import database.DataBaseException;
import database.managers.AdminManager;
import maps.api.services.FilesystemMapsProvider;
import maps.api.services.MapsService;
import maps.api.Cell;
import common.enums.CellType;
import maps.api.Map;
import maps.api.MapObject;
import org.joda.time.DateTime;

import java.sql.SQLException;
import java.util.*;


public class AdminService implements IAdminLogic {

    // todo Check Admin authorization?

    // Hardcoded static MapSize
    private final static int MAP_SIZE = 100;

    // Database Access
    private IAdminData adminDataBase;

    // Maps Component Access
    private MapsService mapsService;

    public AdminService() {
        this.adminDataBase = new AdminManager();
        this.mapsService = new MapsService(new FilesystemMapsProvider("file"));
    }

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
    public List<Map> getMapTemplates() throws AdminException {
        List<Map> cachedMaps = getAllMaps();
        cachedMaps.removeIf(m -> null != m.getUserId());
        return cachedMaps;
    }

    @Override
    public List<Map> getAllMaps() throws AdminException {
        try {
            return adminDataBase.getMaps();
        } catch (DataBaseException ex) {
            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        }
        return null;
    }

    @Override
    public List<MapObject> getAllObjects() throws AdminException {
        List<MapObject> objects = null;
        try {
            objects = adminDataBase.getObjects();
        } catch (DataBaseException ex) {
            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
                throw new AdminException(ex);
        } catch (Exception ex) {
            throw new AdminException(ex);
        }
        return objects;
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
    public void createMap(UUID mapTemplateID, int userID,
                          List<MapObjectInfo> objectInfos) throws AdminException {
        try {
            Map newMap = adminDataBase.getMap(mapTemplateID);
            newMap.setGuid(UUID.randomUUID());
            newMap.setUserId(userID);
            DateTime creationTime = DateTime.now();
            newMap.setCreated(creationTime);
            newMap.setModified(creationTime);
            List<MapObject> cachedObjects = adminDataBase.getObjects();
            for (MapObjectInfo mapObjectInfo : objectInfos) {
                newMap.place(mapObjectInfo.getX(), mapObjectInfo.getY(),
                        cachedObjects.stream().filter(
                                (x) -> x.getGuid().equals(
                                        mapObjectInfo.getMapObjectID()))
                                .findFirst().get()
                );
            }
            adminDataBase.addMap(newMap);
            mapsService.saveMap(newMap);
        } catch (DataBaseException ex) {
            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        } catch (NoSuchElementException ex) {
            throw new AdminException(ex.getMessage() + AdminException.NOT_EXIST);
        }
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
        System.out.println("Saving");
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
            MapObject updatedMapObject = adminDataBase.getObject(mapObjectID);
            updatedMapObject.setName(name);
            updatedMapObject.setHeight(height);
            updatedMapObject.setType(type);
            updatedMapObject.setAllowedTerrainType(allowedTerrainType);
            updatedMapObject.setPrice(price);
            updatedMapObject.setHeatFactor(heatFactor);
            adminDataBase.updateObject(mapObjectID, updatedMapObject);
        } catch (DataBaseException ex) {
            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        }

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
            throw new AdminException("Cell [" + cellX + ';' + cellY + "] " + AdminException.NOT_EXIST);
        }

    }

    @Override
    public void updateCellType(UUID mapID, int cellX, int cellY, CellType cellType) throws AdminException {
        Map updatedMap = mapsService.getMap(mapID);
        if (null != updatedMap.getUserId()) {
            throw new AdminException(AdminException.NOT_ALLOWED);
        }
        try {
            Cell modifiedCell = updatedMap.get(cellX, cellY);
            modifiedCell.setType(cellType);
        } catch (NullPointerException ex) {
            throw new AdminException("Cell [" + cellX + ';' + cellY + "] " + AdminException.NOT_EXIST);
        }
    }

}
