package service;

import dao.UserDAO;
import dao.UserJdbcDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserJdbcService implements Service {

    private static Logger log = Logger.getLogger(UserJdbcService.class.getName());
    private UserDAO userJdbcDAO = new UserJdbcDAO();
    private static UserJdbcService service;

    private UserJdbcService() {
    }

    public static UserJdbcService getInstance() {
        UserJdbcService result = service;
        if (service != null) {
            return result;
        }
        synchronized (UserJdbcService.class) {
            if (service == null) {
                service = new UserJdbcService();
            }
            return service;
        }
    }

    @Override
    public List<User> listUser() throws SQLException {
        return userJdbcDAO.selectAllUsers();
    }

    @Override
    public void updateUser(int id, String name, byte age, String email, String location) throws SQLException {
        User user = new User(id, name, age, email, location);
        userJdbcDAO.updateUser(user);
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        userJdbcDAO.deleteUserById(id);
    }

    @Override
    public User showEditForm(int id) throws SQLException {
        return userJdbcDAO.selectUser(id);
    }

    @Override
    public void insertUser(String name, byte age, String email, String location) throws SQLException {
        User newUser = new User(name, age, email, location);
        userJdbcDAO.insertUser(newUser);
    }
}
