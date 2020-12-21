package gamelogic;

import database.IClientData;
import database.model.PlaceableObject;

import java.util.*;

public class ObjectManager implements IObjectHandler{
    private IClientData clientData;

    @Override
    public List<Map<String, String>> displayObjectProperties(UUID objectID) {
       List<PlaceableObject> placeableObjects = clientData.getAllObjects();
        List<Map<String, String>> properties = new LinkedList<Map<String, String>>();
       for(PlaceableObject object : placeableObjects)
       {
           properties.add(object.getParametersSet());
       }
       return properties;
    }
}
