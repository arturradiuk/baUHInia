//package logic.service;
//
//import maps.MapsService;
//import maps.api.Map;
//import maps.api.MapObject;
//import org.apache.commons.lang3.NotImplementedException;
//
//import java.util.List;
//import java.util.UUID;
//
//public class MapManager_deprecated implements IMapService {
//    private List<maps.api.Map> cachedMaps;
//    private MapsService mapsService;
//    //private IAdminData adminData;
//
//    public MapManager_deprecated() {
//        // todo adminData = x;
//
//        // todo how to create mapsServiece instance???
//        //mapsService = new MapsService();
//        cacheMaps();
//    }
//
//    @Override
//    public void addMap(int size, List<MapObject> objects) {
//        // todo update constructor with 'name' property
//        Map map = Map.init(size);
//        for (MapObject obj : objects) {
//            map.place(obj);
//        }
//        mapsService.saveMap(map);
//    }
//
//    @Override
//    public void removeMap(UUID mapID) {
//        mapsService.deleteMap(mapID);
//    }
//
//    @Override
//    public void updateMap(UUID mapID, List<MapObject> newObjects) {
//        Map oldMap = mapsService.getMap(mapID);
//        Map newMap = Map.init(oldMap.getCells().size());
//        for (MapObject obj : newObjects) {
//            newMap.place(obj);
//        }
//        mapsService.updateMap(mapID, newMap);
//
//    }
//
//    @Override
//    public void updatePermissions(UUID mapID, java.util.Map<String, String> newPermissionsSet) {
//        throw new NotImplementedException("Not implemented");
//    }
//
//    @Override
//    public String getInfo() {
//        StringBuilder stringBuilder = new StringBuilder();
//        // todo getAll() from mapsService
//        return stringBuilder.toString();
//    }
//
//    @Override
//    public void updateHeatSimulationFactor(double newValue) {
//        //todo Not implemented
//    }
//
//    private void cacheMaps() {
//        // todo cachedMaps = mapsService.getAll();
//    }
//}
