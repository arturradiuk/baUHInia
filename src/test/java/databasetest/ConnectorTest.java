package databasetest;

import database.Connector;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectorTest {
    @Test
    public void getConnectionTest() {
        Connector connector = new Connector();
        Connection connection = connector.getConnection();
        Assert.assertNotNull(connection);
        connector.close();
    }

    @Test
    public void selectTest() {
        Connector conn = new Connector();
        Statement stmt = null;
        try {
            stmt = conn.getConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "select * from test where _name = 'lolek'";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            rs.next();
            Assert.assertEquals("lolek", rs.getString("_name"));
            Assert.assertEquals("0000", rs.getString("phone"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
