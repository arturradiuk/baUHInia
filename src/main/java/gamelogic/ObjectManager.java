package gamelogic;

import database.IClientData;
import database.model.PlaceableObject;

import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ObjectManager implements IObjectHandler{
    private IClientData clientData;

    @Override
    public List<Dictionary<String, String>> displayObjectProperties(UUID objectID) {
       List<PlaceableObject> placeableObjects = clientData.getObjects();
        List<Dictionary<String, String>> properties = new LinkedList<Dictionary<String, String>>();
       for(PlaceableObject object : placeableObjects)
       {
           properties.add(object.getParametersSet());
       }
       return properties;
    }
}
