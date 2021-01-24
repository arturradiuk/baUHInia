package gamelogic.placeholderdata;

import java.util.UUID;

public interface IMapsService {
    public Map getMap(UUID id);
    public void saveMap(Map map);
    public Map fromTemplate(UUID id);
}
