package database.managers;


import database.Connector;
import database.IAdminData;
import maps.api.Map;
import maps.api.MapObject;
import org.joda.time.DateTime;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.awt.*;

public class AdminManager implements IAdminData {
    private Connector connector;

    public AdminManager(Connector connector) {
        this.connector = connector;
    }

    public AdminManager() {
        this.connector = new Connector();
    }

    @Override
    public List<MapObject> getObjects() {
        return new MapObjectManager().getAllObjects();
    }

    @Override
    public void addObject(MapObject obj) {
        String sqlQuery = "insert into objects_templates (id, name, price, heat_factor, length, width, height, terrain_type, object_type)\n" +
                "values (?,?,?,?,?,?,?,?,?)";
        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, obj.getGuid().toString());
            ps.setString(2, obj.getName());
            ps.setDouble(3, obj.getPrice());
            ps.setDouble(4, obj.getHeatFactor());
            ps.setInt(5, obj.getLength());
            ps.setInt(6, obj.getWidth());
            ps.setInt(7, obj.getHeight());
            ps.setString(8, obj.getAllowedTerrainType().toString());
            ps.setString(9, obj.getAllowedTerrainType().toString());
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void removeObject(UUID objectID) {
        String sqlQuery = "delete from objects_templates where id = ?";

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, objectID.toString());
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void updateObject(UUID objectID, MapObject obj) {
        String sqlQuery = "UPDATE objects_templates\n" +
                "SET id = ?,\n" +
                "    name = ?,\n" +
                "    price = ?,\n" +
                "    heat_factor = ?,\n" +
                "    length = ?,\n" +
                "    width = ?,\n" +
                "    height = ?,\n" +
                "    terrain_type = ?,\n" +
                "    object_type = ? \n" +
                "WHERE id = ?;";

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            ps.setString(1, obj.getGuid().toString());
            ps.setString(2, obj.getName());
            ps.setDouble(3, obj.getPrice());
            ps.setDouble(4, obj.getHeatFactor());
            ps.setInt(5, obj.getLength());
            ps.setInt(6, obj.getWidth());
            ps.setInt(7, obj.getHeight());
            ps.setString(8, obj.getAllowedTerrainType().toString());
            ps.setString(9, obj.getType().toString());
            ps.setString(10, objectID.toString());

            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void addMap(Map map) {
        String sqlQuery = "insert into maps_metadata (id, name, last_modified, created, user_id)\n" +
                "values (?, ?, ?, ?, ?)";

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            ps.setString(1, map.getGuid().toString());
            ps.setString(2, map.getName());

            ps.setTimestamp(3, new Timestamp(map.getModified().toDate().getTime()));

            ps.setTimestamp(4, new Timestamp(map.getCreated().toDate().getTime()));

            ps.setInt(5, map.getUserId());

            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void removeMap(UUID mapID) {
        String sqlQuery = "delete from maps_metadata where id = ?";

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            ps.setString(1, mapID.toString());

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void updateMap(UUID mapID, Map map) {
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
            ps.setInt(5, map.getUserId());
            ps.setString(6, mapID.toString());

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public List<Map> getMaps() {
        String sqlQuery = "select * from maps_metadata";

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

}
