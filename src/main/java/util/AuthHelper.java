package util;

public class AuthHelper {

    public static boolean isLogin(String name, String password) {
        if (name != null && name.length() > 0 && !name.isEmpty()) {
            if (password != null && password.length() > 0 && !name.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
