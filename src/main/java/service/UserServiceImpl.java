package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = UserDaoFactory.getDao();
    private static UserServiceImpl service;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        UserServiceImpl result = service;
        if (service != null) {
            return result;
        }
        synchronized (UserServiceImpl.class) {
            if (service == null) {
                service = new UserServiceImpl();
            }
            return service;
        }
    }

    @Override
    public List<User> getListUser() {
        return userDAO.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userDAO.deleteUserById(id);
    }

    @Override
    public User getUserById(Integer id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void createUser(User user) {
        userDAO.createUser(user);
    }
}
