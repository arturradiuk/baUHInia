package database.managers;

import common.enums.CellType;
import common.enums.ObjectType;
import database.Connector;
import database.DataBaseException;
import database.IMapObject;
import database.model.User;
import maps.api.Map;
import maps.api.MapObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MapObjectManager implements IMapObject {
    private Connector connector;

    public MapObjectManager() {
        this.connector = new Connector();
    }

    @Override
    public List<MapObject> getMapObjectsForUuids(List<UUID> objectsUuids) {
        try (Connection connection = this.connector.getConnection()) {

            List<MapObject> mapObjects = new ArrayList<>();

            for (UUID id : objectsUuids) {
                mapObjects.add(this.getMapObjectByUuid(id, connection));
            }
            return mapObjects;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private MapObject getMapObjectByUuid(UUID uuid, Connection connection) throws SQLException {
        String sqlQuery = "select * from objects_templates where objects_templates.id=?";

        PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ps.setString(1, uuid.toString());
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {

            extractMapObjectFromResultSet(resultSet);

            return extractMapObjectFromResultSet(resultSet);
        }

        return null;
    }

    private MapObject extractMapObjectFromResultSet(ResultSet resultSet) throws SQLException {
        String guid = resultSet.getString(1);
        String name = resultSet.getString(2);
        double price = resultSet.getDouble(3);
        double heatFactor = resultSet.getDouble(4);

        Integer length = resultSet.getInt(5);
        Integer width = resultSet.getInt(6);
        Integer height = resultSet.getInt(7);

        String terrainType = resultSet.getString(8);
        String objectType = resultSet.getString(9);

        MapObject mapObject = new MapObject(name, UUID.fromString(guid), width, length, height, ObjectType.valueOf(objectType));
        mapObject.setPrice((int) price);
        mapObject.setHeatFactor((int) heatFactor);
        mapObject.setAllowedTerrainType(CellType.valueOf(terrainType));
        return mapObject;
    }


    @Override
    public List<MapObject> getAllObjects() throws DataBaseException {
        String sqlQueryGetAllObjects = "select * from objects_templates;";

        ResultSet resultSet;

        try (Connection connection = this.connector.getConnection()) {

            List<MapObject> mapObjects = new ArrayList<>();

            PreparedStatement ps = connection.prepareStatement(sqlQueryGetAllObjects);

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                extractMapObjectFromResultSet(resultSet);

                mapObjects.add(extractMapObjectFromResultSet(resultSet));
            }
            return mapObjects;

        } catch (SQLException throwables) {
            throw new DataBaseException(throwables);
        }

    }

    @Override
    public MapObject getObjectByUuid(UUID objectUuid) {
        String sqlQuery = "select * from objects_templates where id = ?";

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, objectUuid.toString());
            ResultSet rs =  ps.executeQuery();
            if(rs.next()){
                return this.extractMapObjectFromResultSet(rs);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
