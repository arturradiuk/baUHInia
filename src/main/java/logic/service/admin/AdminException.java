package logic.service.admin;

public class AdminException extends Throwable{
    public static final String NOT_EXIST = "Point does not exist in map";
    public static final String OCCUPIED = "Point is already occupied in map";

    public AdminException(Throwable cause) {
        super(cause);
    }

    public AdminException() {
    }

    public AdminException(String objInfo, String message) {
        super(objInfo + " " + message);
    }
}
