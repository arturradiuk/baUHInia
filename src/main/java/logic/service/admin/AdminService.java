package logic.service.admin;

import common.enums.ObjectType;
import database.IAdminData;
import database.DataBaseException;
import database.managers.AdminManager;
import login.LoginService;
import login.UserType;
import maps.api.services.FilesystemMapsProvider;
import maps.api.services.MapsService;
import maps.api.Cell;
import common.enums.CellType;
import maps.api.Map;
import maps.api.MapObject;
import org.joda.time.DateTime;

import java.sql.SQLException;
import java.util.*;

/**
 * Implementuje interfejs IAdminLogic jako serwis dostępu do metod administracyjnych
 */
public class AdminService implements IAdminLogic {

    // todo Check Admin authorization?


    // Hardcoded static MapSize
    private final static int MAP_SIZE = 100;

    // Database Access
    private IAdminData adminDataBase;

    // Maps Component Access
    private MapsService mapsService;

    //Login component Access
    private LoginService loginService;

    public AdminService() {
        this.adminDataBase = new AdminManager();
        this.mapsService = MapsService.Companion.getInstance();
        this.loginService = LoginService.getInstance();
    }

    /**
     * Generuje pusty szablon mapy i zapisuje go w bazie danych oraz pamięci
     * @throws AdminException W przypadku braku autoryzacji administratora
     */
    @Override
    public void createNewMapTemplate() throws AdminException {
        if (loginService.getCurrentUserType() != UserType.Administrator)
            throw new AdminException(AdminException.NOT_ALLOWED);
        Map newMap = mapsService.generateMap();
        DateTime creationTime = DateTime.now();
        newMap.setCreated(creationTime);
        newMap.setModified(creationTime);
        newMap.setName("test");
        // todo Catch exception from DataBase?
        adminDataBase.addMap(newMap);
        mapsService.saveMap(newMap);
    }

    /**
     * Metoda dostępowa do szablonów map
     * @return Szablony map, bez przypisanych użytkowników
     * @throws AdminException W przypadku braku autoryzacji administratora
     */
    @Override
    public List<Map> getMapTemplates() throws AdminException {
        List<Map> cachedMaps = getAllMaps();
        cachedMaps.removeIf(m -> null != m.getUserId());
        return cachedMaps;
    }

    /**
     * Metoda dostępowa do map
     * @return Wszystkie mapy zapisane w bazie danych
     * @throws AdminException W przypadku braku autoryzacji administratora
     */
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

    /**
     * Metoda dostępowa do obiektów
     * @return Obiekty, które można umieścić na mapie
     * @throws AdminException Przekazuje błąd pochodzący z bazy danych
     */
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

    /**
     * Usuwa mapę o podanym id
     * @param mapID id mapy
     * @throws AdminException W przypadku braku autoryzacji administratora lub niewłaściwego parametru
     */
    @Override
    public void removeMap(UUID mapID) throws AdminException {
        if (loginService.getCurrentUserType() != UserType.Administrator)
            throw new AdminException(AdminException.NOT_ALLOWED);
        try {
            Map deletedMap = mapsService.getMap(mapID);
            adminDataBase.removeMap(mapID);
            mapsService.deleteMap(deletedMap);
        } catch (DataBaseException ex) {
            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        }
    }

    /**
     * Tworzy mapę użytkownika
     * @param mapTemplateID szablon mapy
     * @param userID id użytkownika tworzącego mapę
     * @param objectInfos obiekty umieszczone na mapie
     * @throws AdminException Przekazuje błąd z bazy danych
     */
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

    /**
     * Tworzy nowy obiekt
     * @param name nazwa obiektu
     * @param width szerokość
     * @param length długość
     * @param height wysokość
     * @param type typ obiektu
     * @param allowedTerrainType typ podłoża
     * @param price cena
     * @param heatFactor współczynnik ciepła
     * @throws AdminException W przypadku braku autoryzacji administratora
     */
    @Override
    public void addNewMapObject(String name, int width, int length, int height,
                                ObjectType type, CellType allowedTerrainType,
                                int price, double heatFactor) throws AdminException {

        if (loginService.getCurrentUserType() != UserType.Administrator)
            throw new AdminException(AdminException.NOT_ALLOWED);
        MapObject newMapObject =
                new MapObject(name, UUID.randomUUID(), width, length, height, type);
        newMapObject.setAllowedTerrainType(allowedTerrainType);
        newMapObject.setPrice(price);
        newMapObject.setHeatFactor(heatFactor);
        //System.out.println("Saving");
        // todo can it throw an exception?
        adminDataBase.addObject(newMapObject);
    }

    /**
     * Usuwa obiekt o podanym id
     * @param mapObjectID id obiektu
     * @throws AdminException W przypadku braku autoryzacji administratora
     */
    @Override
    public void removeMapObject(UUID mapObjectID) throws AdminException {
        if (loginService.getCurrentUserType() != UserType.Administrator)
            throw new AdminException(AdminException.NOT_ALLOWED);
        try {
            adminDataBase.removeObject(mapObjectID);
        } catch (DataBaseException ex) {
            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        }
        // todo Notify map component??
    }

    /**
     * Edytuje obiekt o podanym id
     * @param mapObjectID id obiektu
     * @param name nowa nazwa
     * @param height nowa wysokość
     * @param type nowy typ obiektu
     * @param allowedTerrainType nowy typ podłoża
     * @param price nowa cena
     * @param heatFactor nowy współczynnik ciepła
     * @throws AdminException W przypadku braku autoryzacji administratora
     */
    @Override
    public void updateMapObject(UUID mapObjectID, String name, int height,
                                ObjectType type, CellType allowedTerrainType,
                                int price, double heatFactor) throws AdminException {
        if (loginService.getCurrentUserType() != UserType.Administrator)
            throw new AdminException(AdminException.NOT_ALLOWED);
        try {
            MapObject updatedMapObject = adminDataBase.getObject(mapObjectID);
            if(null != name)
                updatedMapObject.setName(name);
            if(0 != height)
                updatedMapObject.setHeight(height);
            if(null != type)
                updatedMapObject.setType(type);
            if(null != allowedTerrainType)
                updatedMapObject.setAllowedTerrainType(allowedTerrainType);
            if(0 != price)
                updatedMapObject.setPrice(price);
            if(0 > heatFactor)
                updatedMapObject.setHeatFactor(heatFactor);
            adminDataBase.updateObject(mapObjectID, updatedMapObject);
        } catch (DataBaseException ex) {
            if (ex.getMessage().equals(DataBaseException.NOT_FOUND)) {
                throw new AdminException(ex);
            }
        }

        // todo Notify map component??
    }

    /**
     * Nakłada/Zdejmuje blokadę z komurki
     * @param mapID id mapy
     * @param cellX współrzędna X komórki
     * @param cellY współrzędna Y komórki
     * @throws AdminException W przypadku braku autoryzacji administratora
     */
    @Override
    public void switchLockCell(UUID mapID, int cellX, int cellY) throws AdminException {
        if (loginService.getCurrentUserType() != UserType.Administrator)
            throw new AdminException(AdminException.NOT_ALLOWED);
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

    /**
     * Edytuje typ komórki
     * @param mapID id mapy
     * @param cellX współrzędna X komórki
     * @param cellY współrzędna Y koórki
     * @param cellType nowy Typ komórki
     * @throws AdminException W przypadku braku autoryzacji administratora
     */
    @Override
    public void updateCellType(UUID mapID, int cellX, int cellY, CellType cellType) throws AdminException {
        if (loginService.getCurrentUserType() != UserType.Administrator)
            throw new AdminException(AdminException.NOT_ALLOWED);
        Map updatedMap = mapsService.getMap(mapID);
        if (null != updatedMap.getUserId()) {
            throw new AdminException(AdminException.NOT_ALLOWED);
        }
        try {
            Cell modifiedCell = updatedMap.get(cellX, cellY);
            modifiedCell.setType(cellType);
            updatedMap.setModified(DateTime.now());
            adminDataBase.updateMap(mapID, updatedMap);
            mapsService.saveMap(updatedMap);
        } catch (NullPointerException ex) {
            throw new AdminException("Cell [" + cellX + ';' + cellY + "] " + AdminException.NOT_EXIST);
        }
    }

}
