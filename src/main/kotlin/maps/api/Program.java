package maps.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import common.enums.ObjectType;
import kotlin.Unit;
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

        // kotlin lambdas must return Unit.INSTANCE in java
        // iterating over map
        loadedMap.iterate((cell, x, y) -> Unit.INSTANCE);

        // uncommented because it throws sometimes
//        var obj = new MapObject("Monopolowy", UUID.randomUUID(), 3, 4, 100, ObjectType.CONCRETE);
//        loadedMap.place(3, 4, obj);

    }
}