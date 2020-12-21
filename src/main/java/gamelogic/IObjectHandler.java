package gamelogic;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IObjectHandler {
    /**
     * Returns dictionary of properties of chosen object
     * @param objectID object which properties are being returned
     * @return dictionary of properties of chosen object
     */
    List<Map<String,String>>displayObjectProperties(UUID objectID);
}
