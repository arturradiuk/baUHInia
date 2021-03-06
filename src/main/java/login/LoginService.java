package login;

import database.managers.AccountManager;
import database.Connector;
import database.IAccountData;
import database.model.User;
import login.exceptions.IncorrectDataException;
import login.exceptions.UserNotFoundException;

public class LoginService implements IAuthentication {
    private IAccountData dataBase;
    private static LoginService instance;
    private UserData loggedUser;


    private LoginService() {
        this.dataBase = new AccountManager(new Connector());
    }

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    @Override
    public UserType getCurrentUserType() {
        return loggedUser == null ? UserType.Unauthorized : loggedUser.getType() ;
    }

    @Override
    public UserType login(String email, String password) throws UserNotFoundException, IncorrectDataException {
        checkDataCorrectness(email, password);

        User user = dataBase.getUser(email, password);
        if (user == null) {
            throw new UserNotFoundException();
        }
        loggedUser = new UserData(user.getFirstName(),
                                  user.getLastName(),
                                  user.getEmail(),
                                  user.getPassword(),
                                  UserType.fromString(user.getUserType()));

        return loggedUser.getType();
    }



    @Override
    public boolean register(UserData userData) throws IncorrectDataException {
        checkDataCorrectness(userData.getEmail(), userData.getPassword());
        return dataBase.addUser(new User(userData.getFirstName(), userData.getLastName(), userData.getEmail(), userData.getPassword(), "General"));
    }

    @Override
    public boolean registerAsAdmin(String adminEmail, String adminPassword, UserData userData) throws IncorrectDataException, UserNotFoundException {
        UserType type = login(adminEmail, adminPassword);
        if (type == null || type.equals(UserType.General)) {
            return false;
        }
        checkDataCorrectness(userData.getEmail(), userData.getPassword());
        return dataBase.addUser(new User(userData.getFirstName(), userData.getLastName(), userData.getEmail(), userData.getPassword(), "Administrator"));
    }

    private static void checkDataCorrectness(String email, String password) throws IncorrectDataException {
        if (!email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")) {
            throw new IncorrectDataException("Wrong email!");
        }
        if (password.length() <= 5) {
            throw new IncorrectDataException("Password length must be greater than 5");
        }
    }
}