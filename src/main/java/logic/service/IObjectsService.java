package logic.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IObjectsService {
    void addObject(String name, Map<String,String> parameterSet) throws RepositoryException;
    void removeObject(UUID objectID) throws RepositoryException;
    void updateParameters(UUID objectID, Map<String,String> newParametersSet) throws RepositoryException;
    List<Map<String,String>> getAllObjectsInfo();

}
