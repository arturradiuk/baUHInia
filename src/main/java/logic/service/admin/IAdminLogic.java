package logic.service.admin;

import common.enums.CellType;
import common.enums.ObjectType;
import maps.api.Cell;
import maps.api.Map;
import maps.api.MapObject;

import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

public interface IAdminLogic {

    void createNewMapTemplate();
    List<Map> getMapTemplates() throws AdminException;
    List<Map> getAllMaps() throws AdminException;
    List<MapObject> getAllObjects() throws AdminException;
    void removeMap(UUID mapID) throws AdminException;
    // bierze template, naklada obiekty, przypisuje user
    void createMap(UUID mapTemplateID, int userID, List<MapObjectInfo> objectInfos) throws AdminException;
    void addNewMapObject(String name, int width, int length, int height,
                         ObjectType type, CellType allowedTerrainType,
                         int price, double heatFactor) throws AdminException;
    void removeMapObject(UUID mapObjectID) throws AdminException;
    void updateMapObject(UUID mapObjectID, String name, int height,
                         ObjectType type, CellType allowedTerrainType,
                         int price, double heatFactor) throws AdminException;
    void switchLockCell(UUID mapID, int cellX, int cellY) throws AdminException;
    void updateCellType(UUID mapID, int cellX, int cellY, CellType cellType) throws AdminException;
}
