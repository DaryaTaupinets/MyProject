package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBHelper {

    private static DBHelper instance;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        DBHelper result = instance;
        if (result != null) {
            return result;
        }
        synchronized (DBHelper.class) {
            if (instance == null) {
                instance = new DBHelper();
            }
            return instance;
        }
    }

    private static final String URL = "jdbc:mysql://localhost:3306/userdatabase?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
