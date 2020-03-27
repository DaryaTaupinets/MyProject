package service;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserHibernateService implements Service {

    private static Logger log = Logger.getLogger(UserHibernateService.class.getName());
    private UserDAO userHibernateDAO = new UserHibernateDAO();
    private static UserHibernateService service;

    private UserHibernateService() {
    }

    public static UserHibernateService getInstance() {
        UserHibernateService result = service;
        if (service != null) {
            return result;
        }
        synchronized (UserJdbcService.class) {
            if (service == null) {
                service = new UserHibernateService();
            }
            return service;
        }
    }

    @Override
    public List<User> listUser() throws SQLException {
        return userHibernateDAO.selectAllUsers();
    }

    @Override
    public void updateUser(int id, String name, byte age, String email, String location) throws SQLException {
        User user = new User(id, name, age, email, location);
        userHibernateDAO.updateUser(user);
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        userHibernateDAO.deleteUserById(id);
    }

    @Override
    public User showEditForm(int id) throws SQLException {
        return userHibernateDAO.selectUser(id);
    }

    @Override
    public void insertUser(String name, byte age, String email, String location) throws SQLException {
        User newUser = new User(name, age, email, location);
        userHibernateDAO.insertUser(newUser);
    }
}
