package database;

import database.model.MapObject;
import database.model.PlaceableObject;
import database.model.User;
import org.postgresql.geometric.PGpoint;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class AdminManager implements IAdminData {
    @Override
    public List<PlaceableObject> getObjects() {
        return null;
    }

    @Override
    public void addObject(PlaceableObject obj) {

    }

    @Override
    public void removeObject(UUID objectID) {

    }

    @Override
    public void updateObject(UUID objectID, PlaceableObject obj) {

    }

    public AdminManager(Connector connector) {
        this.connector = connector;
    }

    private Connector connector;

    @Override
    public List<MapObject> getMaps() {
        List<MapObject> maps = new ArrayList<>();
        String sqlQuery_name_id = "select * from map_objects"; // todo what happens when there is no such user in db??
        String sqlQuery_permissions = "select * from permissions_set where mo_id = ?"; // todo what happens when there is no such user in db??
        String sqlQuery_objects = "select * from objects_set where mo_id = ?"; // todo what happens when there is no such user in db??

        ResultSet resultSet = null;

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery_name_id);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                String id = resultSet.getString("id");
                String name = resultSet.getString("name");

                Map<String, String> permissionsSet = new HashMap<>();
                ps = connection.prepareStatement(sqlQuery_permissions);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    permissionsSet.put(rs.getString("_key"), rs.getString("_value"));
                }

                Map<Point, UUID> objectsSet = new HashMap<>();
                ps = connection.prepareStatement(sqlQuery_objects);
                ps.setString(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    PGpoint temp = (PGpoint) rs.getObject("_key");
                    objectsSet.put(new Point((int) temp.x, (int) temp.y), UUID.fromString(rs.getString("_value")));
                }

                maps.add(new MapObject(UUID.fromString(id), name, objectsSet, permissionsSet));
            }


            return maps; //todo could return null value


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    @Override
    public void addMap(MapObject mapObject) {
        List<MapObject> maps = new ArrayList<>();
        String sqlQuery_name_id = "select * from map_objects"; // todo what happens when there is no such user in db??
        String sqlQuery_permissions = "select * from permissions_set where mo_id = ?"; // todo what happens when there is no such user in db??
        String sqlQuery_objects = "select * from objects_set where mo_id = ?"; // todo what happens when there is no such user in db??

        ResultSet resultSet = null;

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery_name_id);
            resultSet = ps.executeQuery();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeMap(UUID mapID) {

    }

    @Override
    public void updateMap(UUID mapID, MapObject map) {

    }
}
