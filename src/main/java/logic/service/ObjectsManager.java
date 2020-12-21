package logic.service;

import database.IAdminData;
import database.model.PlaceableObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ObjectsManager implements IObjectsService {
    private List<PlaceableObject> cachedObjects;
    private IAdminData adminData;

    public ObjectsManager() {
        // todo adminData = x
        //cacheObjects();
    }

    @Override
    public void addObject(String name, Map<String, String> parameterSet) throws RepositoryException {
        PlaceableObject obj = new PlaceableObject(UUID.randomUUID(), name, parameterSet);
        if (cachedObjects.contains(obj)) {
            throw new RepositoryException(obj.toString(), RepositoryException.EXIST);
        }
        adminData.addObject(obj);
        cacheObjects();
    }

    @Override
    public void removeObject(UUID objectID) throws RepositoryException {
        PlaceableObject obj = new PlaceableObject(objectID, null, null);
        if (cachedObjects.contains(obj)) {
            adminData.removeObject(objectID);
            cacheObjects();
        } else
            throw new RepositoryException(objectID.toString(), RepositoryException.NOT_EXIST);

    }

    @Override
    public void updateParameters(UUID objectID, Map<String, String> newParametersSet) throws RepositoryException {
        PlaceableObject obj = getObjectByID(objectID);
        obj.setParametersSet(newParametersSet);
        adminData.updateObject(objectID, obj);
        cacheObjects();
    }

    @Override
    public List<Map<String, String>> getAllObjectsInfo() {
        List<Map<String, String>> output = new ArrayList<>();
        //for (PlaceableObject o : cachedObjects) {
         //   output.add(o.getInfo());
        //}
        return output;
    }

    public PlaceableObject createClonedObject(UUID originalObjectID, UUID newID) throws RepositoryException {
        PlaceableObject originalObject = getObjectByID(originalObjectID);
        PlaceableObject clonedObject = originalObject.copyObject(newID);
        cachedObjects.add(clonedObject);
        return clonedObject;
    }

    void cacheObjects() {
        cachedObjects = adminData.getObjects();
    }

    public PlaceableObject getObjectByID(UUID objectID) throws RepositoryException {
        PlaceableObject obj = null;
        for (PlaceableObject o : cachedObjects) {
            if (o.getId() == objectID) {
                obj = o;
                break;
            }
        }
        if (obj == null)
            throw new RepositoryException(objectID.toString(), RepositoryException.NOT_EXIST);
        return obj;
    }
}
