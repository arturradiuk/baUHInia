package database;

import database.model.User;

public interface IAccountData {
    public User getUser(String login, String password);
    public boolean addUser(String login, String password);
}
