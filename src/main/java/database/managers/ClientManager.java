package database.managers;

import database.Connector;
import database.DataBaseException;
import database.IClientData;
import maps.api.Map;
import maps.api.MapObject;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientManager implements IClientData  {
    private Connector connector;

    public ClientManager(Connector connector) { this.connector = connector; }

    public ClientManager() { this.connector = new Connector(); }

    @Override
    public void addMapForTheUser(Map map, int userId) {
        String sqlQuery = "insert into maps_metadata (id, name, last_modified, created, user_id)\n" +
                "values (?, ?, ?, ?, ?)";

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            ps.setString(1, map.getGuid().toString());
            ps.setString(2, map.getName());

            ps.setTimestamp(3, new Timestamp(map.getModified().toDate().getTime()));

            ps.setTimestamp(4, new Timestamp(map.getCreated().toDate().getTime()));

            ps.setInt(5, userId);

            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Map> getAllMapsForTheUser(int userId) {
        String sqlQuery = "select * from maps_metadata where user_id = ?";

        ResultSet resultSet;

        try (Connection connection = this.connector.getConnection()) {

            List<Map> maps = new ArrayList<>();

            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            ps.setInt(1, userId);

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Map map = new Map();
                map.setGuid(UUID.fromString(resultSet.getString(1)));
                map.setName(resultSet.getString(2));
                map.setModified(new DateTime(resultSet.getTimestamp(3).getTime()));
                map.setCreated(new DateTime(resultSet.getTimestamp(4).getTime()));
                map.setUserId(resultSet.getInt(5));
                maps.add(map);
            }
            return maps;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }


    @Override
    public void updateMapForTheUser(Map map, int userId) {
        String sqlQuery = "update maps_metadata\n" +
                "set id = ?,\n" +
                "    name = ?,\n" +
                "    last_modified = ?,\n" +
                "    created = ?,\n" +
                "    user_id = ?\n" +
                "where id = ?";

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            ps.setString(1, map.getGuid().toString());
            ps.setString(2, map.getName());
            ps.setTimestamp(3, new Timestamp(map.getModified().toDate().getTime()));
            ps.setTimestamp(4, new Timestamp(map.getCreated().toDate().getTime()));
            ps.setInt(5, userId);
            ps.setString(6, map.getGuid().toString());

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeMapForTheUser(Map map, int userId) {
        String sqlQuery = "delete from maps_metadata where id = ? and user_id = ?";

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            ps.setString(1, map.getGuid().toString());
            ps.setInt(2, userId);

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Map> getAllMaps() {
        String sqlQuery = "select * from maps_metadata where user_id is null";

        ResultSet resultSet;

        try (Connection connection = this.connector.getConnection()) {

            List<Map> maps = new ArrayList<>();

            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Map map = new Map();
                map.setGuid(UUID.fromString(resultSet.getString(1)));
                map.setName(resultSet.getString(2));
                map.setModified(new DateTime(resultSet.getTimestamp(3).getTime()));
                map.setCreated(new DateTime(resultSet.getTimestamp(4).getTime()));
                map.setUserId(resultSet.getInt(5));
                maps.add(map);
            }
            return maps;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    @Override
    public List<MapObject> getAllObjects() throws DataBaseException {
        return new MapObjectManager().getAllObjects();
    }
}
