package logic.service;

import maps.api.MapObject;

import java.awt.*;
import java.util.Map;
import java.util.UUID;
import java.util.List;

public interface IMapService {
    //void addMap(String name, Map<Point, UUID> objectsSet, Map<String,String> permissionsSet) throws RepositoryException;
    void addMap(int size, List<MapObject> objects);
    void removeMap(UUID mapID) throws RepositoryException;
    //void updateMap(UUID mapID, Map<Point,UUID> newObjectSet) throws RepositoryException;
    void updateMap(UUID mapID, List<MapObject> newObjects);
    void updatePermissions(UUID mapID, Map<String,String> newPermissionsSet) throws RepositoryException;
    String getInfo();
    void updateHeatSimulationFactor(double newValue);
}
