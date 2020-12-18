package logic.service;

import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

public interface IObslugaObiektow {
    void addObject(String name, Dictionary<String,String> parameterSet) throws RepositoryException;
    void removeObject(UUID objectID);
    void updateParameters(UUID objectID, Dictionary<String,String> newParametersSet);
    List<Dictionary<String,String>> getInfo();

}
