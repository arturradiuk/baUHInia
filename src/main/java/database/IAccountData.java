package database;

import database.model.User;

public interface IAccountData {
    public User getUser(String login, String password);

    public boolean addUser(User user);

    public boolean deleteUser(User user);

    public boolean updateUser(User user);
}
