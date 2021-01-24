package database;

import java.sql.SQLException;

public class DataBaseException extends Throwable {
    public static final String NOT_FOUND = "Not found";
    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(Throwable cause) {
        super(cause);
    }
}
