package database;

import database.model.User;

import java.sql.*;

public class AccountManager implements IAccountData {
    private Connector connector;

    public AccountManager(Connector connector) {
        this.connector = connector;
    }

    @Override
    public User getUser(String login, String password) {
        String sql = "select * from users\n" +
                "where email = ? and password = ?"; // todo what happens when there is no such user in db??

        ResultSet resultSet = null;

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, login);
            ps.setString(2, password);

            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId((resultSet.getInt("id")));
                user.setFirstName((resultSet.getString("firstName")));
                user.setLastName((resultSet.getString("lastName")));
                user.setEmail((resultSet.getString("email")));
                user.setPassword((resultSet.getString("password")));
                user.setUserType((resultSet.getString("userType")));
                return user;
            }
            return null; //todo could return null value


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addUser(String login, String password) { // todo need to be implemented
        return false;
    }
}
