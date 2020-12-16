import database.Connector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class demo {
    public static void main(String[] args) throws SQLException, IOException {
        Connector connector = new Connector();
        Connection connection = connector.getConnection();
        connection.close();
    }
}
