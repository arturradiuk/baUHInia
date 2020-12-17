package databasetest;

import database.AccountManager;
import database.Connector;
import database.model.User;
import org.junit.Assert;
import org.junit.Test;

public class AccountManagerTest {
    @Test
    public void getUserTest() {
        AccountManager accountManager = new AccountManager(new Connector());

        User expected = new User( "lolek", "lolek", "lolek@gmail.com", "12345", "General");
        expected.setId(1);
        User actual = accountManager.getUser("lolek@gmail.com", "12345");
        Assert.assertEquals(expected, actual);

        expected = new User("bolek", "bolek", "bolek@gmail.com", "56789", "General");
        expected.setId(2);
        actual = accountManager.getUser("bolek@gmail.com", "56789");
        Assert.assertEquals(expected, actual);

        expected = new User("tola", "tola", "tola@gmail.com", "qwe123", "Administrator");
        expected.setId(3);
        actual = accountManager.getUser("tola@gmail.com", "qwe123");
        Assert.assertEquals(expected, actual);

        actual = accountManager.getUser("tola@gmail.com", "12345"); // bad password
        Assert.assertNull(actual);
    }
}
