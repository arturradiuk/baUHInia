package databasetest;

import database.Connector;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class ConnectorTest {
    @Test
    public void getConnectionTest() {
        Connector connector = new Connector();
        Connection connection = connector.getConnection();
        Assert.assertNotNull(connection);
        connector.close();
    }

}
