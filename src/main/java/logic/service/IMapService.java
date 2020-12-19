package logic.service;

import java.awt.*;
import java.util.Map;
import java.util.UUID;

public interface IMapService {
    void addMap(String name, Map<Point, UUID> objectsSet, Map<String,String> permissionsSet) throws RepositoryException;
    void removeMap(UUID mapID) throws RepositoryException;
    void updateMap(UUID mapID, Map<Point,UUID> newObjectSet) throws RepositoryException;
    void updatePermissions(UUID mapID, Map<String,String> newPermissionsSet) throws RepositoryException;
    String getInfo();
    void updateHeatSimulationFactor(double newValue);
}
