package gameGUI.adminControllers;

import common.enums.CellType;
import common.enums.ObjectType;
import logic.service.admin.AdminException;
import logic.service.admin.AdminService;
import logic.service.admin.IAdminLogic;
import logic.service.admin.MapObjectInfo;
import maps.api.Map;
import maps.api.MapObject;

import java.util.List;
import java.util.UUID;

public class AdminManager implements IAdminLogic {

    private final AdminService adminService;

    public AdminManager() {
        this.adminService = new AdminService();
    }

    @Override
    public void createNewMapTemplate() {
        adminService.createNewMapTemplate();
    }

    @Override
    public List<Map> getMapTemplates() throws AdminException {
        return adminService.getMapTemplates();
    }

    @Override
    public List<Map> getAllMaps() throws AdminException {
        return adminService.getAllMaps();
    }

    @Override
    public List<MapObject> getAllObjects() throws AdminException {
        return adminService.getAllObjects();
    }

    @Override
    public void removeMap(UUID mapID) throws AdminException {
        adminService.removeMap(mapID);

    }

    @Override
    public void createMap(UUID mapTemplateID, int userID, List<MapObjectInfo> objectInfos) throws AdminException {

    }

    @Override
    public void addNewMapObject(String name, int width, int length, int height, ObjectType type, CellType allowedTerrainType, int price, double heatFactor) throws AdminException {
        adminService.addNewMapObject(name, width, length, height, type, allowedTerrainType, price, heatFactor);
    }

    @Override
    public void removeMapObject(UUID mapObjectID) throws AdminException {
        adminService.removeMapObject(mapObjectID);
    }

    @Override
    public void updateMapObject(UUID mapObjectID, String name, int height, ObjectType type, CellType allowedTerrainType, int price, double heatFactor) throws AdminException {
        adminService.updateMapObject(mapObjectID, name, height, type, allowedTerrainType, price, heatFactor);
    }

    @Override
    public void switchLockCell(UUID mapID, int cellX, int cellY) throws AdminException {
        adminService.switchLockCell(mapID, cellX, cellY);
    }

    @Override
    public void updateCellType(UUID mapID, int cellX, int cellY, CellType cellType) throws AdminException {
        adminService.updateCellType(mapID, cellX, cellY, cellType);
    }
}
