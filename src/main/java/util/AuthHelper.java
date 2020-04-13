package util;

import model.User;
import org.apache.commons.lang3.StringUtils;
import service.UserService;
import service.UserServiceImpl;

public class AuthHelper {

    private static UserService userService = UserServiceImpl.getInstance();

    private boolean logged;
    private String userName;
    private String userPassword;

    private static AuthHelper authHelper;

    public static AuthHelper getInstance() {
        AuthHelper result = authHelper;
        if (authHelper != null) {
            return result;
        }
        synchronized (AuthHelper.class) {
            if (authHelper == null) {
                authHelper = new AuthHelper();
            }
            return authHelper;
        }
    }

    private AuthHelper() {
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public static boolean userIsLogged(String userName, String userPassword) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPassword)) {
            return false;
        }

        User user = userService.getUserByName(userName);

        if (user == null) {
            return false;
        }
        return userPassword.equals(user.getPassword());
    }
}
