package maps.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import common.enums.ObjectType;
import maps.api.services.FilesystemMapsProvider;
import maps.api.services.MapsService;
import maps.api.utils.GuidKt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class Program {
    public static void main(String[] args) throws IOException {
        var service = new MapsService(new FilesystemMapsProvider("F:\\root"));
        var map = service.generateMap();
        if(map.getState() == State.CREATED) System.out.println("Good #1");

        map.setName("Nazwa dla mapy");

        // jakaś edycja
        service.saveMap(map);
        if(map.getState() == State.UNCHANGED) System.out.println("Good #2");
        if(!GuidKt.isEmptyGuid(map.getGuid())) System.out.println("Good #3");

        var loadedMap = service.getMap(map.getGuid());
        if(loadedMap.getGuid().equals(map.getGuid())) System.out.println("Good #4");
        if(loadedMap.getSize() == map.getSize()) System.out.println("Good #5");
        if(loadedMap.getName().equals(map.getName())) System.out.println("Good #6");

        var obj = new MapObject("Monopolowy", UUID.randomUUID(), 3, 4, 100, ObjectType.CONCRETE);
        loadedMap.place(3, 4, obj);

//        var registry = new TerrainTypesRegistry();
//        registry.register(new TerrainType("Beton"));
//        registry.register(new TerrainType("Trawa"));
//
//        var map = Map.init("Mapa", 10, registry);
//        var building = new MapObject("Shop", UUID.randomUUID(), 3, 1, new TerrainType[] { registry.get("Beton") });
//        map.place(building);
//
//
//        var service = new MapsService(new FilesystemMapsProvider("F:\\root"));
//        service.saveMap(map);

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new KotlinModule());
//        objectMapper.writeValue(new File("F:\\plik.json"), map);

//        var desMap = objectMapper.readValue(new File("F:\\plik.json"), Map.class);

//        map.remove(building);
    }
}