package logic.service;

import java.awt.*;
import java.util.Dictionary;
import java.util.UUID;

public interface IMapService {
    void addMap(String name, Dictionary<Point, UUID> objectsSet, Dictionary<String,String> permissionsSet) throws RepositoryException;
    void removeMap(UUID mapID) throws RepositoryException;
    void updateMap(UUID mapID, Dictionary<Point,UUID> newObjectSet) throws RepositoryException;
    void updatePermissions(UUID mapID, Dictionary<String,String> newPermissionsSet) throws RepositoryException;
    String getInfo();
    void updateHeatSimulationFactor(double newValue);
}
