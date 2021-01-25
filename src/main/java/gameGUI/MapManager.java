package gameGUI;

import maps.api.Cell;
import maps.api.Map;
import maps.api.State;
import maps.api.services.FilesystemMapsProvider;
import maps.api.services.IMapsService;
import maps.api.services.MapsService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class MapManager implements IMapsService{

    private IMapsService mapsService;

    public MapManager() {
        this.mapsService = new MapsService(new FilesystemMapsProvider("D:\\root"));
    }


    @Override
    public void saveMap(@NotNull Map map) {

    }

    @Nullable
    @Override
    public Map getMap(@NotNull UUID id) {
        return null;
    }

    @Override
    public void deleteMap(@NotNull Map map) {

    }

    @NotNull
    @Override
    public Map generateMap() {
        return null;
    }

    @NotNull
    @Override
    public Map emptyMap() {
        return null;
    }
}
