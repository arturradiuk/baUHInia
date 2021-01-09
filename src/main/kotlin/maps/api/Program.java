package maps.api;

public class Program {
    public static void main(String[] args) {
        var map = Map.init(10);
        var building = new MapObject("Shop", CellType.Building, 3, 1);
        map.place(building);
        map.remove(building);
    }
}