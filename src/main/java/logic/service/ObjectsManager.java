package logic.service;

import database.IAdminData;
import database.model.PlacableObject;

import java.util.*;

public class ObjectsManager implements IObjectsService {
    private List<PlacableObject> cachedObjects;
    private IAdminData adminData;

    public ObjectsManager() {
        // todo adminData = x
        cacheObjects();
    }

    @Override
    public void addObject(String name, Dictionary<String, String> parameterSet) throws RepositoryException {
        PlacableObject obj = new PlacableObject(UUID.randomUUID(), name, parameterSet);
        if(cachedObjects.contains(obj)){
            throw new RepositoryException(obj.toString(), RepositoryException.EXIST);
        }
        adminData.addObject(obj);
        cacheObjects();
    }

    @Override
    public void removeObject(UUID objectID) throws RepositoryException {
        PlacableObject obj = new PlacableObject(objectID,null,null);
        if(cachedObjects.contains(obj)) {
            adminData.removeObject(objectID);
            cacheObjects();
        }
        else
            throw new RepositoryException(objectID.toString(), RepositoryException.NOT_EXIST);

    }

    @Override
    public void updateParameters(UUID objectID, Dictionary<String, String> newParametersSet) throws RepositoryException {
        PlacableObject obj = null;
        for(PlacableObject o : cachedObjects)
        {
            if(o.getId() == objectID){
                obj = o;
                break;
            }
        }
        if(obj == null)
            throw new RepositoryException(objectID.toString(), RepositoryException.NOT_EXIST);
        obj.setParametersSet(newParametersSet);
        adminData.updateObject(objectID, obj);
        cacheObjects();
    }

    @Override
    public List<Dictionary<String, String>> getInfo() {
        List<Dictionary<String,String>> output = new ArrayList<>();
        for(PlacableObject o : cachedObjects){
            output.add(o.getInfo());
        }
        return output;
    }
    void cacheObjects(){
        cachedObjects = adminData.getObjects();
    }
}
