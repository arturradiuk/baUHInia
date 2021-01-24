package database;

import org.postgresql.geometric.PGpoint;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminManager /*implements IAdminData*/ { // todo uuid can't be updated
//    private Connector connector;
//
//    public AdminManager(Connector connector) {
//        this.connector = connector;
//    }
//
//    @Override
//    public List<PlaceableObject> getObjects() {
//        List<PlaceableObject> placeableObjects = new ArrayList<>();
//        String sqlQueryPlaceableObject = "select * from placeable_objects";
//        String sqlQueryParametersSet = "select * from parameters_set where po_id = ?";
//
//        ResultSet resultSet;
//
//        try (Connection connection = this.connector.getConnection()) {
//            PreparedStatement ps = connection.prepareStatement(sqlQueryPlaceableObject);
//
//            resultSet = ps.executeQuery();
//            while (resultSet.next()) {
//                String id = resultSet.getString("id");
//                String name = resultSet.getString("name");
//
//                Map<String, String> parametersSet = new HashMap<>();
//
//                ps = connection.prepareStatement(sqlQueryParametersSet);
//                ps.setString(1, id);
//
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    parametersSet.put(rs.getString("_key"), rs.getString("_value"));
//                }
//
//                placeableObjects.add( new PlaceableObject(UUID.fromString(id), name, parametersSet));
//            }
//            return placeableObjects;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public void addObject(PlaceableObject obj) {
//        String sqlQueryPlaceableObjects = "insert into placeable_objects(id, name) values (?, ?);";
//        String sqlQueryParametersSet = "insert into parameters_set(_key, _value, po_id) values (?, ?, ?);";
//
//        try (Connection connection = this.connector.getConnection()) {
//
//            PreparedStatement ps = connection.prepareStatement(sqlQueryPlaceableObjects);
//            ps.setString(1, obj.getId().toString());
//            ps.setString(2, obj.getName());
//            ps.execute();
//
//            for (Map.Entry<String, String> set : obj.getParametersSet().entrySet()) {
//                ps = connection.prepareStatement(sqlQueryParametersSet);
//                ps.setString(1, set.getKey());
//                ps.setString(2, set.getValue());
//                ps.setString(3, obj.getId().toString());
//                ps.execute();
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public void removeObject(UUID objectID) {
//        String sqlQueryPlaceableObjects = "delete from placeable_objects where id = ?;";
//        String sqlQueryParametersSet = "delete from parameters_set where po_id = ?";
//
//        try (Connection connection = this.connector.getConnection()) {
//
//            PreparedStatement ps = connection.prepareStatement(sqlQueryParametersSet);
//            ps.setString(1, objectID.toString());
//            ps.execute();
//
//            ps = connection.prepareStatement(sqlQueryPlaceableObjects);
//            ps.setString(1, objectID.toString());
//            ps.execute();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public void updateObject(UUID objectID, PlaceableObject obj) {
//        String sqlQueryUpdateName = "update placeable_objects set name = ? where id = ?";
//        String sqlQueryDeleteParameters = "delete from parameters_set where po_id = ?";
//        String sqlQueryAddParametersSet = "insert into parameters_set(_key, _value, po_id) values (?, ?, ?);";
//
//        try (Connection connection = this.connector.getConnection()) {
//            PreparedStatement ps = connection.prepareStatement(sqlQueryUpdateName);
//            ps.setString(1, obj.getName());
//            ps.setString(2, objectID.toString());
//            ps.execute();
//
//            ps = connection.prepareStatement(sqlQueryDeleteParameters);
//            ps.setString(1, objectID.toString());
//            ps.execute();
//
//            for (Map.Entry<String, String> set : obj.getParametersSet().entrySet()) {
//                ps = connection.prepareStatement(sqlQueryAddParametersSet);
//                ps.setString(1, set.getKey());
//                ps.setString(2, set.getValue());
//                ps.setString(3, obj.getId().toString());
//                ps.execute();
//            };
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<MapObject> getMaps() {
//        List<MapObject> maps = new ArrayList<>();
//        String sqlQuery_name_id = "select * from map_objects"; // todo what happens when there is no such user in db??
//        String sqlQuery_permissions = "select * from permissions_set where mo_id = ?"; // todo what happens when there is no such user in db??
//        String sqlQuery_objects = "select * from objects_set where mo_id = ?"; // todo what happens when there is no such user in db??
//
//        ResultSet resultSet = null;
//
//        try (Connection connection = this.connector.getConnection()) {
//
//            PreparedStatement ps = connection.prepareStatement(sqlQuery_name_id);
//            resultSet = ps.executeQuery();
//
//            while (resultSet.next()) {
//
//                String id = resultSet.getString("id");
//                String name = resultSet.getString("name");
//
//                Map<String, String> permissionsSet = new HashMap<>();
//                ps = connection.prepareStatement(sqlQuery_permissions);
//                ps.setString(1, id);
//                ResultSet rs = ps.executeQuery();
//
//                while (rs.next()) {
//                    permissionsSet.put(rs.getString("_key"), rs.getString("_value"));
//                }
//
//                Map<Point, UUID> objectsSet = new HashMap<>();
//                ps = connection.prepareStatement(sqlQuery_objects);
//                ps.setString(1, id);
//                rs = ps.executeQuery();
//
//                while (rs.next()) {
//                    PGpoint temp = (PGpoint) rs.getObject("_key");
//                    objectsSet.put(new Point((int) temp.x, (int) temp.y), UUID.fromString(rs.getString("_value")));
//                }
//
//                maps.add(new MapObject(UUID.fromString(id), name, objectsSet, permissionsSet));
//            }
//
//
//            return maps; //todo could return null value
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return null;
//        }
//
//    }
//
//    @Override
//    public void addMap(MapObject mapObject) {
//        String sqlQuery_name_id = "insert into map_objects (id, name) values (?, ?)"; // todo what happens when there is no such user in db??
//        String sqlQuery_permissions = "insert into permissions_set(mo_id, _key, _value) values (?, ?, ?)"; // todo what happens when there is no such user in db??
//        String sqlQuery_objects = "insert into objects_set (mo_id, _key, _value) values (?, ?, ?)"; // todo what happens when there is no such user in db??
//
//
//        try (Connection connection = this.connector.getConnection()) {
//
//            PreparedStatement ps = connection.prepareStatement(sqlQuery_name_id);
//            ps.setString(1, mapObject.getId().toString());
//            ps.setString(2, mapObject.getName());
//            ps.execute();
//
//            for (Map.Entry<String, String> set : mapObject.getPermissionsSet().entrySet()) {
//                ps = connection.prepareStatement(sqlQuery_permissions);
//                ps.setString(1, mapObject.getId().toString());
//                ps.setString(2, set.getKey());
//                ps.setString(3, set.getValue());
//                ps.execute();
//            }
//
//            for (Map.Entry<Point, UUID> set : mapObject.getObjectsSet().entrySet()) {
//                ps = connection.prepareStatement(sqlQuery_objects);
//                ps.setString(1, mapObject.getId().toString());
//                ps.setObject(2, new PGpoint(set.getKey().x, set.getKey().y));
//                ps.setString(3, set.getValue().toString());
//                ps.execute();
//            }
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public void removeMap(UUID mapID) {
//        String sqlQuery_name_id = "delete from map_objects where id =?"; // todo what happens when there is no such user in db??
//        String sqlQuery_permissions = "delete from permissions_set where mo_id = ?"; // todo what happens when there is no such user in db??
//        String sqlQuery_objects = "delete from objects_set where mo_id = ?"; // todo what happens when there is no such user in db??
//
//
//        try (Connection connection = this.connector.getConnection()) {
//
//            PreparedStatement ps = connection.prepareStatement(sqlQuery_permissions);
//            ps.setString(1, mapID.toString());
//            ps.execute();
//
//            ps = connection.prepareStatement(sqlQuery_objects);
//            ps.setString(1, mapID.toString());
//            ps.execute();
//
//            ps = connection.prepareStatement(sqlQuery_name_id);
//            ps.setString(1, mapID.toString());
//            ps.execute();
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public void updateMap(UUID mapID, MapObject mapObject) {
//
//        String sqlQuery_name_id = "update map_objects set name = ?, id = ? where id = ?"; // todo what happens when there is no such user in db??
//
//        String sqlQuery_permissions_insert = "insert into permissions_set(mo_id, _key, _value) values (?, ?, ?)"; // todo what happens when there is no such user in db??
//        String sqlQuery_objects_insert = "insert into objects_set (mo_id, _key, _value) values (?, ?, ?)"; // todo what happens when there is no such user in db??
//        String sqlQuery_permissions_delete = "delete from permissions_set where mo_id = ?"; // todo what happens when there is no such user in db??
//        String sqlQuery_objects_delete = "delete from objects_set where mo_id = ?"; // todo what happens when there is no such user in db??
//
//
//        try (Connection connection = this.connector.getConnection()) {
//
//            PreparedStatement ps = connection.prepareStatement(sqlQuery_name_id);
//            ps.setString(1, mapObject.getName());
//            ps.setString(2, mapObject.getId().toString());
//            ps.setString(3, mapID.toString());
//            ps.execute();
//
//            ps = connection.prepareStatement(sqlQuery_permissions_delete);
//            ps.setString(1,mapID.toString());
//            ps.execute();
//
//            ps = connection.prepareStatement(sqlQuery_objects_delete);
//            ps.setString(1,mapID.toString());
//            ps.execute();
//
//            for (Map.Entry<String, String> set : mapObject.getPermissionsSet().entrySet()) {
//                ps = connection.prepareStatement(sqlQuery_permissions_insert);
//                ps.setString(1, mapObject.getId().toString());
//                ps.setString(2, set.getKey());
//                ps.setString(3, set.getValue());
//                ps.execute();
//            }
//
//            for (Map.Entry<Point, UUID> set : mapObject.getObjectsSet().entrySet()) {
//                ps = connection.prepareStatement(sqlQuery_objects_insert);
//                ps.setString(1, mapObject.getId().toString());
//                ps.setObject(2, new PGpoint(set.getKey().x, set.getKey().y));
//                ps.setString(3, set.getValue().toString());
//                ps.execute();
//            }
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
}
