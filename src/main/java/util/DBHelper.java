package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBHelper {

    private static final Logger log = Logger.getLogger(DBHelper.class.getName());
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

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/userdatabase?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            log.info("Connection OK");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.info("Connection ERROR");
        }
        return connection;
    }
}
