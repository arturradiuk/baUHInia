package database;

import database.model.User;

import java.sql.*;

public class AccountManager implements IAccountData { // todo uuid can't be updated
    private Connector connector;

    public AccountManager(Connector connector) {
        this.connector = connector;
    }

    @Override
    public User getUser(String login, String password) {
        String sqlQuery = "select * from users\n" +
                "where email = ? and password = ?"; // todo what happens when there is no such user in db??

        ResultSet resultSet = null;

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery);

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
            return null;
        }

    }

    @Override
    public boolean addUser(User user) { // todo return false if there is such user with particular login
        String sqlQuery = "insert into users(firstName, lastName, email, password, userType)\n" +
                "values (?,?,?,?,?);";

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUserType());
            ps.execute();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user) { // todo before delete all user's maps
        String sqlQuery = "delete from users where email = ?";

        try (Connection connection = this.connector.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, user.getEmail());
            ps.execute();
            return true;  // even if there is no such user in db return true

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        String sqlQuery = "update users set firstName = ?, lastName = ?, email = ?, password = ?, userType = ? where id =?";

        try (Connection connection = this.connector.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUserType());
            ps.setInt(6, user.getId());
            ps.execute();
            return true;  // even if there is no such user in db return true

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
