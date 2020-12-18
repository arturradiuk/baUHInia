package logic.service;

import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

public interface IObjectsService {
    void addObject(String name, Dictionary<String,String> parameterSet) throws RepositoryException;
    void removeObject(UUID objectID) throws RepositoryException;
    void updateParameters(UUID objectID, Dictionary<String,String> newParametersSet) throws RepositoryException;
    List<Dictionary<String,String>> getInfo();

}
