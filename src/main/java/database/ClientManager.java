package database;


import org.postgresql.geometric.PGpoint;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class ClientManager /*implements IClientData */{ // todo uuid can't be updated
//    private Connector connector;
//
//    public ClientManager(Connector connector) {
//        this.connector = connector;
//    }
//
//
//    @Override
//    public void addMapForTheUser(MapObject mapObject, int userId) {
//        String sqlQueryAddUserPermissions = "insert into user_map_permissions_set(user_id, mo_id, _key, _value) values (?, ?, ?, ?)";
//        String sqlQueryAddUserObjects = "insert into user_map_objects_set (user_id, mo_id, _key, _value) values (?, ?, ?, ?)";
//
//        try (Connection connection = this.connector.getConnection()) {
//            PreparedStatement ps;
//
//            for (Map.Entry<String, String> set : mapObject.getPermissionsSet().entrySet()) {
//                ps = connection.prepareStatement(sqlQueryAddUserPermissions);
//                ps.setInt(1, userId);
//                ps.setString(2, mapObject.getId().toString());
//                ps.setString(3, set.getKey());
//                ps.setString(4, set.getValue());
//                ps.execute();
//            }
//
//            for (Map.Entry<Point, UUID> set : mapObject.getObjectsSet().entrySet()) {
//                ps = connection.prepareStatement(sqlQueryAddUserObjects);
//                ps.setInt(1, userId);
//                ps.setString(2, mapObject.getId().toString());
//                ps.setObject(3, new PGpoint(set.getKey().x, set.getKey().y));
//                ps.setString(4, set.getValue().toString());
//                ps.execute();
//            }
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//
//    }
//
//    @Override
//    public List<MapObject> getAllMapsForTheUser(int userId) {
//        String sqlQueryGetAllMapsIdForTheUserFromObjects = "select mo_id from   user_map_objects_set where user_id = ? GROUP BY mo_id HAVING count(*) > 1";
//        String sqlQueryGetAllObjectsForTheId = "select * from user_map_objects_set where user_id =? and mo_id =?";
//
//        String sqlQueryGetAllPermissionsForTheId = "select * from user_map_permissions_set where user_id =? and mo_id = ?";
//        String sqlQueryGetAllMapsIdForTheUserFromPermissions = "select mo_id from   user_map_permissions_set where user_id = ? GROUP BY mo_id HAVING count(*) > 1";
//
//        String sqlQueryGetMapName = "select name from map_objects where id = ?";
//
//        List<String> mapsIdFromObjects = new ArrayList<>();
//        List<String> mapsIdFromPermissions = new ArrayList<>();
//
//        List<MapObject> maps = new ArrayList<>();
//        ResultSet resultSet = null;
//
//        try (Connection connection = this.connector.getConnection()) {
//
//            PreparedStatement ps = connection.prepareStatement(sqlQueryGetAllMapsIdForTheUserFromObjects);
//            ps.setInt(1, userId);
//            resultSet = ps.executeQuery();
//            while (resultSet.next()) {
//                mapsIdFromObjects.add(resultSet.getString("mo_id"));
//            }
//
//            ps = connection.prepareStatement(sqlQueryGetAllMapsIdForTheUserFromPermissions);
//            ps.setInt(1, userId);
//            resultSet = ps.executeQuery();
//            while (resultSet.next()) {
//                mapsIdFromPermissions.add(resultSet.getString("mo_id"));
//            }
//
//            Map<String, Map<Point, UUID>> tempObjectsMap = new HashMap<>();
//
//            for (String s : mapsIdFromObjects) {
//                ps = connection.prepareStatement(sqlQueryGetAllObjectsForTheId);
//                ps.setInt(1, userId);
//                ps.setString(2, s);
//                resultSet = ps.executeQuery();
//                Map<Point, UUID> tempOS = new HashMap<>();
//                while (resultSet.next()) {
//                    PGpoint temp = (PGpoint) resultSet.getObject("_key");
//                    tempOS.put(new Point((int) temp.x, (int) temp.y), UUID.fromString(resultSet.getString("_value")));
//                }
//                tempObjectsMap.put(s, tempOS);
//            }
//
//
//            Map<String, Map<String, String>> tempPermissionsMap = new HashMap<>();
//
//            for (String s : mapsIdFromObjects) {
//                ps = connection.prepareStatement(sqlQueryGetAllPermissionsForTheId);
//                ps.setInt(1, userId);
//                ps.setString(2, s);
//                resultSet = ps.executeQuery();
//                Map<String, String> tempPS = new HashMap<>();
//                while (resultSet.next()) {
//                    tempPS.put(resultSet.getString("_key"), resultSet.getString("_value"));
//                }
//                tempPermissionsMap.put(s, tempPS);
//            }
//
//            for (String s : mapsIdFromObjects) {
//                ps = connection.prepareStatement(sqlQueryGetMapName);
//                ps.setString(1, s);
//                resultSet = ps.executeQuery();
//                resultSet.next();
//                MapObject mapObject = new MapObject(UUID.fromString(s), resultSet.getString("name"),
//                        tempObjectsMap.get(s), tempPermissionsMap.get(s));
//                maps.add(mapObject);
//
//            }
//
//            return maps;
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return null;
//        }
//
//    }
//
//    @Override
//    public void updateMapForTheUser(MapObject mapObject, int userId) {
//        String sqlQueryDeleteUserPermissions = "delete from user_map_permissions_set where user_id = ? and mo_id = ?";
//        String sqlQueryDeleteUserObjects = "delete from user_map_objects_set where user_id = ? and mo_id = ?";
//        String sqlQueryAddUserPermissions = "insert into user_map_permissions_set(user_id, mo_id, _key, _value) values (?, ?, ?, ?)";
//        String sqlQueryAddUserObjects = "insert into user_map_objects_set (user_id, mo_id, _key, _value) values (?, ?, ?, ?)";
//
//        try (Connection connection = this.connector.getConnection()) {
//            PreparedStatement ps;
//
//            ps = connection.prepareStatement(sqlQueryDeleteUserPermissions);
//            ps.setInt(1, userId);
//            ps.setString(2, mapObject.getId().toString());
//            ps.execute();
//
//            ps = connection.prepareStatement(sqlQueryDeleteUserObjects);
//            ps.setInt(1, userId);
//            ps.setString(2, mapObject.getId().toString());
//            ps.execute();
//
//            for (Map.Entry<String, String> s : mapObject.getPermissionsSet().entrySet()) {
//                ps = connection.prepareStatement(sqlQueryAddUserPermissions);
//                ps.setInt(1, userId);
//                ps.setString(2, mapObject.getId().toString());
//                ps.setString(3, s.getKey());
//                ps.setString(4, s.getValue());
//                ps.execute();
//            }
//
//            for (Map.Entry<Point, UUID> s : mapObject.getObjectsSet().entrySet()) {
//                ps = connection.prepareStatement(sqlQueryAddUserObjects);
//                ps.setInt(1, userId);
//                ps.setString(2, mapObject.getId().toString());
//                ps.setObject(3, new PGpoint(s.getKey().x, s.getKey().y));
//                ps.setString(4, s.getValue().toString());
//                ps.execute();
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public void removeMapForTheUser(MapObject mapObject, int userId) {
//        String sqlQueryDeleteUserPermissions = "delete from user_map_permissions_set where user_id = ? and mo_id = ?";
//        String sqlQueryDeleteUserObjects = "delete from user_map_objects_set where user_id = ? and mo_id = ?";
//
//        try (Connection connection = this.connector.getConnection()) {
//            PreparedStatement ps;
//
//            ps = connection.prepareStatement(sqlQueryDeleteUserPermissions);
//            ps.setInt(1, userId);
//            ps.setString(2, mapObject.getId().toString());
//            ps.execute();
//
//            ps = connection.prepareStatement(sqlQueryDeleteUserObjects);
//            ps.setInt(1, userId);
//            ps.setString(2, mapObject.getId().toString());
//            ps.execute();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<MapObject> getAllMaps() {
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
//    }
//
//    @Override
//    public List<PlaceableObject> getAllObjects() {
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
//                placeableObjects.add(new PlaceableObject(UUID.fromString(id), name, parametersSet));
//            }
//            return placeableObjects;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return null;
//        }
//    }
}
