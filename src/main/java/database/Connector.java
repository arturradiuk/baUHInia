package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    public Connection getConnection() throws SQLException {

        Properties pomProperties = new Properties();
        try {
            pomProperties.load(Connector.class.getResourceAsStream("/pom.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Properties dbProps = new Properties();
        dbProps.setProperty("user", pomProperties.getProperty("user"));
        dbProps.setProperty("password", pomProperties.getProperty("password"));
        dbProps.setProperty("ssl", pomProperties.getProperty("ssl"));
        String url = pomProperties.getProperty("location") + "/" + pomProperties.getProperty("dbname");
        Connection connection = DriverManager.getConnection(url, dbProps);
        return connection;

    }
}
