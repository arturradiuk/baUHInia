package logic.service;

public class MapException extends Throwable{
    public static final String NOT_EXIST = "Point does not exist in map";
    public static final String OCCUPIED = "Point is already occupied in map";

    public MapException() {
    }

    public MapException(String objInfo, String message) {
        super(objInfo + " " + message);
    }
}
