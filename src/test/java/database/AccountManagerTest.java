package database;

import database.managers.AccountManager;
import database.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountManagerTest {
    @Test
    public void getUserTest() {
        AccountManager accountManager = new AccountManager(new Connector());

        User expected = new User("lolek", "lolek", "lolek@gmail.com", "12345", "General");
        expected.setId(1);
        User actual = accountManager.getUser("lolek@gmail.com", "12345");
        assertEquals(expected, actual);

        expected = new User("bolek", "bolek", "bolek@gmail.com", "56789", "General");
        expected.setId(2);
        actual = accountManager.getUser("bolek@gmail.com", "56789");
        assertEquals(expected, actual);

        expected = new User("tola", "tola", "tola@gmail.com", "qwe123", "Administrator");
        expected.setId(3);
        actual = accountManager.getUser("tola@gmail.com", "qwe123");
        assertEquals(expected, actual);

        actual = accountManager.getUser("tola@gmail.com", "12345"); // bad password
        assertNull(actual);
    }

    @Test
    public void addUserTest() {
        AccountManager accountManager = new AccountManager(new Connector());
        User user = new User("lolek", "lolek", "lolek@gmail.com", "12345", "General");
        assertEquals(false, accountManager.addUser(user)); // false because user with this email exists

        user = new User("lolek", "lolek", "1lolek@gmail.com", "12345", "General");

        assertEquals(true, accountManager.addUser(user));
        assertEquals(true, accountManager.deleteUser(user));
    }

    @Test
    public void deleteUserTest() {
        AccountManager accountManager = new AccountManager(new Connector());
        User user = new User("lolek", "lolek", "1lolek@gmail.com", "12345", "General");

        assertEquals(true, accountManager.addUser(user));
        assertEquals(true, accountManager.deleteUser(user));
    }

    @Test
    public void updateUserTest() {
        AccountManager accountManager = new AccountManager(new Connector());
        User user = accountManager.getUser("lolek@gmail.com", "12345");
        user.setEmail("lol@gmail.com");
        accountManager.updateUser(user);
        assertEquals(user, accountManager.getUser("lol@gmail.com", "12345"));
        user.setEmail("lolek@gmail.com");
        accountManager.updateUser(user);
        assertEquals(user, accountManager.getUser("lolek@gmail.com", "12345"));
    }
}
