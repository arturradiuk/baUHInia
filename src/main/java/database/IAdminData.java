package database;

import database.model.PlacableObject;

import java.util.List;
import java.util.UUID;

public interface IAdminData {
    List<PlacableObject> getObjects();

    void addObject(PlacableObject obj);

    void removeObject(UUID objectID);

    void updateObject(UUID objectID, PlacableObject obj);
}
