package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    Connection connection;

    public Connection getConnection() {
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

        try {
            this.connection = DriverManager.getConnection(url, dbProps);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return this.connection; // todo can be null
        }
    }

    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
