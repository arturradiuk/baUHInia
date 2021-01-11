package maps.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import maps.api.services.FilesystemMapsProvider;
import maps.api.services.MapsService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class Program {
    public static void main(String[] args) throws IOException {
        var registry = new TerrainTypesRegistry();
        registry.register(new TerrainType("Beton"));
        registry.register(new TerrainType("Trawa"));

        var map = Map.init("Mapa", 10, registry);
        var building = new MapObject("Shop", UUID.randomUUID(), 3, 1, new TerrainType[] { registry.get("Beton") });
        map.place(building);

        var service = new MapsService(new FilesystemMapsProvider("F:\\root"));
        service.saveMap(map);

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new KotlinModule());
//        objectMapper.writeValue(new File("F:\\plik.json"), map);

//        var desMap = objectMapper.readValue(new File("F:\\plik.json"), Map.class);

//        map.remove(building);
    }
}