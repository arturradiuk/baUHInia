package logic.service;

import database.IAdminData;
import kotlin.Pair;
import maps.api.Cell;
import maps.api.CellType;
import maps.api.MapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ObjectsManager_deprecated implements IObjectsService_deprecated {
    private List<MapObject> cachedObjects;
    private IAdminData adminData;

    public ObjectsManager_deprecated() {
        // todo adminData = x
        cacheObjects();
    }

    @Override
    public void addObject(String name, CellType type, int width, int height) throws RepositoryException {
        MapObject object = new MapObject(name, type, width, height, UUID.randomUUID());
        if (cachedObjects.contains(object)) {
            throw new RepositoryException(object.toString(), RepositoryException.EXIST);
        }
        adminData.addObject(object);
        cacheObjects();
    }

    @Override
    public void removeObject(MapObject object) throws RepositoryException {
        if (cachedObjects.contains(object)) {
            adminData.removeObject(object.getID());
            cacheObjects();
        } else
            throw new RepositoryException(object.toString(), RepositoryException.NOT_EXIST);

    }

    @Override
    public void removeObject(UUID ID) throws RepositoryException {
        boolean objectExists = false;
        for (MapObject cachedObject : cachedObjects) {
            if (cachedObject.getID() == ID) {
                objectExists = true;
                break;
            }
        }
        if (objectExists) {
            adminData.removeObject(ID);
            cacheObjects();
        } else
            throw new RepositoryException(ID.toString(), RepositoryException.NOT_EXIST);
    }

    @Override
    public void updateObject(MapObject object, List<Pair<String, String>> newParameters) throws RepositoryException {
        for (Pair<String, String> param : newParameters) {
            switch (param.getFirst().toLowerCase()) {
                case "name":
                    object.setName(param.getSecond());
                    break;
                case "type":
                    object.setType(CellType.valueOf(param.getSecond()));
                    break;
                case "width":
                    object.setWidth(Integer.parseInt(param.getSecond()));
                    break;
                case "height":
                    object.setHeight(Integer.parseInt(param.getSecond()));
                    break;
                default:
                    throw new RuntimeException("This parameter does not exist");
            }
        }

        adminData.updateObject(object.getID(), object);
        cacheObjects();
    }

    @Override
    public void updateObject(MapObject object, List<Pair<String, String>> newParameters, List<Cell> newCells) throws RepositoryException {
        object.setCells((ArrayList<Cell>) newCells);
        updateObject(object, newParameters);
    }

    @Override
    public List<String> getAllObjectsInfo() {
        List<String> output = new ArrayList<>();
        for (MapObject o : cachedObjects) {
            output.add(o.toString());
        }
        return output;
    }

    public MapObject getClonedObject(MapObject originalObject) {
        return new MapObject(originalObject.getName(), originalObject.getType(),
                originalObject.getWidth(), originalObject.getHeight(), UUID.randomUUID());
    }

    void cacheObjects() {
        cachedObjects = adminData.getObjects();
    }

    public MapObject getObjectByID(UUID objectID) throws RepositoryException {
        MapObject obj = null;
        for (MapObject o : cachedObjects) {
            if (o.getID() == objectID) {
                obj = o;
                break;
            }
        }
        if (obj == null)
            throw new RepositoryException(objectID.toString(), RepositoryException.NOT_EXIST);
        return obj;
    }
}
