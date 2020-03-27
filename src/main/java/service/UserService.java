package service;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserService implements Service {

    private static Logger log = Logger.getLogger(UserService.class.getName());
    //private UserDAO userDAO = new UserJdbcDAO();
    private UserDAO userDAO = new UserHibernateDAO();
    private static UserService service;

    private UserService() {
    }

    public static UserService getInstance() {
        UserService result = service;
        if (service != null) {
            return result;
        }
        synchronized (UserService.class) {
            if (service == null) {
                service = new UserService();
            }
            return service;
        }
    }

    @Override
    public List<User> listUser() throws SQLException {
        return userDAO.selectAllUsers();
    }

    @Override
    public void updateUser(int id, String name, byte age, String email, String location) throws SQLException {
        User user = new User(id, name, age, email, location);
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        userDAO.deleteUserById(id);
    }

    @Override
    public User showEditForm(int id) throws SQLException {
        return userDAO.selectUser(id);
    }

    @Override
    public void insertUser(String name, byte age, String email, String location) throws SQLException {
        User newUser = new User(name, age, email, location);
        userDAO.insertUser(newUser);
    }
}
