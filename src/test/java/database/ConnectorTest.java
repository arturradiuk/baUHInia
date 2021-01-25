package database;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectorTest {
    @Test
    public void getConnectionTest() {
        Connector connector = new Connector();
        try (Connection connection = connector.getConnection()) {
            assertNotNull(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Test
    public void selectTest() {
        String sql = "select * from test where _name = 'lolek'";

        ResultSet rs = null;
        Connector connector = new Connector();
        Statement stmt = null;
        try (Connection connection = connector.getConnection()) {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            assertEquals("lolek", rs.getString("_name"));
            assertEquals("0000", rs.getString("phone"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}