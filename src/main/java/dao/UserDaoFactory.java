package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class UserDaoFactory {

    private static Logger log = Logger.getLogger(UserDaoFactory.class.getName());

    private static final String PROPERTIES_FILE = "config.properties";

    public static UserDAO getDao() {
        Properties property = new Properties();
        UserDAO userDAO = null;
        try (InputStream input =
                     Thread.currentThread().getContextClassLoader()
                             .getResourceAsStream(PROPERTIES_FILE)) {
            property.load(input);
            String dao = property.getProperty("daoType");
            if (dao.equals("hibernate")) {
                log.info("Created UserDao - UserHibernateDAO");
                userDAO = new UserHibernateDAO();
            }

            if (dao.equals("jdbc")) {
                log.info("Created UserDao - UserHibernateDAO");
                userDAO = new UserJdbcDAO();
            }
        } catch (IOException e) {
            log.info("Properties file isn't exists!");
        }
        return userDAO;
    }
}
