package logic.service.admin;

import logic.service.admin.AdminException;
import maps.api.Cell;
import maps.api.CellType;

import java.util.Dictionary;
import java.util.UUID;

public interface IAdminLogic {

    void addNewMap();
    void removeMap(UUID mapID) throws AdminException;
    void updateMap(UUID mapID, Dictionary<Cell, UUID> cellMapObjectID) throws AdminException;
    void addNewMapObject(String name, CellType cellType, int length, int width, int height) throws AdminException;
    void removeMapObject(UUID mapObjectID) throws AdminException;
    void updateMapObject(UUID mapObjectID, String name, CellType type,int length,  int width, int height) throws AdminException;
}
