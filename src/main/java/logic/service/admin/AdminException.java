package logic.service.admin;

public class AdminException extends Throwable{
    public static final String NOT_EXIST = "Not exist";
    public static final String OCCUPIED = "Point is already occupied in map";
    public static final String NOT_ALLOWED = "Operation not allowed";
    public static final String DATABASE_CONNECTION = "DataBase connection error";

    public AdminException(Throwable cause) {
        super(cause);
    }

    public AdminException() {
    }

    public AdminException(String objInfo, String message) {
        super(objInfo + " " + message);
    }

    public AdminException(String message) {
        super(message);
    }
}
