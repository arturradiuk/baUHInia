package logic.service;

import database.IAdminData;
import database.model.MapObject;
import database.model.PlacableObject;

import java.awt.*;
import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

public class MapManager implements IMapService {
    private List<MapObject> cachedMaps;
    private IAdminData adminData;

    public MapManager() {
        //todo adminData = x;
        cacheMaps();
    }

    @Override
    public void addMap(String name, Dictionary<Point, UUID> objectsSet, Dictionary<String, String> permissionsSet) throws RepositoryException {
        MapObject map = new MapObject(UUID.randomUUID() ,name,objectsSet,permissionsSet);
        if(cachedMaps.contains(map)){
            throw new RepositoryException(map.toString(), RepositoryException.EXIST);
        }
        adminData.addMap();
        cacheMaps();
    }

    @Override
    public void removeMap(UUID mapID) throws RepositoryException {
        MapObject map = new MapObject(mapID,null,null,null);
        if(cachedMaps.contains(map)){
            adminData.removeMap(mapID);
            cacheMaps();
        }
        else
            throw new RepositoryException(mapID.toString(), RepositoryException.NOT_EXIST);
    }

    @Override
    public void updateMap(UUID mapID, Dictionary<Point, UUID> newObjectSet) throws RepositoryException {
        MapObject map = null;
        for(MapObject m : cachedMaps){
            if(m.getId() == mapID){
                map = m;
                break;
            }
        }
        if(map == null)
            throw new RepositoryException(mapID.toString(), RepositoryException.NOT_EXIST);
         map.setObjectsSet(newObjectSet);
         adminData.updateMap(mapID,map);
         cacheMaps();
    }

    @Override
    public void updatePermissions(UUID mapID, Dictionary<String, String> newPermissionsSet) throws RepositoryException {
        MapObject map = null;
        for(MapObject m : cachedMaps){
            if(m.getId() == mapID){
                map = m;
                break;
            }
        }
        if(map == null)
            throw new RepositoryException(mapID.toString(), RepositoryException.NOT_EXIST);
        map.setPermissionsSet(newPermissionsSet);
        adminData.updateMap(mapID,map);
        cacheMaps();
    }

    @Override
    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        for(MapObject m : cachedMaps) {
            stringBuilder.append(m.toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public void updateHeatSimulationFactor(double newValue) {
        //todo Not implemented
    }

    private void cacheMaps() {
        cachedMaps = adminData.getMaps();
    }
}
