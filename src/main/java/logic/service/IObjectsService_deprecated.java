package logic.service;

import kotlin.Pair;
import maps.api.Cell;
import maps.api.CellType;
import maps.api.MapObject;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IObjectsService_deprecated {
    void addObject(String name, CellType type, int width, int height) throws RepositoryException;

    void removeObject(MapObject object) throws RepositoryException;

    void removeObject(UUID ID) throws RepositoryException;

    public void updateObject(MapObject object, List<Pair<String, String>> newParameters) throws RepositoryException;

    public void updateObject(MapObject object, List<Pair<String, String>> newParameters, List<Cell> newCells) throws RepositoryException;
    List<String> getAllObjectsInfo();

}
