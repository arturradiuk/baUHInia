package login;

import login.exceptions.IncorrectDataException;
import login.exceptions.UserNotFoundException;

public interface IAuthentication {
    UserType login(String email, String password) throws UserNotFoundException, IncorrectDataException;

    boolean register(UserData userData) throws IncorrectDataException;

    boolean registerAsAdmin(String adminEmail, String adminPassword, UserData userData) throws IncorrectDataException, UserNotFoundException;

    UserType getCurrentUserType();
}
