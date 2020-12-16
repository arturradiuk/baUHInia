import database.Connector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class demo {
    public static void main(String[] args) throws SQLException {
        Connector conn = new Connector();
        Statement stmt = conn.getConnection().createStatement();
        String sql = "select * from test where _name = 'lolek'";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        System.out.println(rs.getString("_name"));
        System.out.println(rs.getString("phone"));
    }
}
