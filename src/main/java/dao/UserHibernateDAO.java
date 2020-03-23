package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {


    @Override
    public void insertUser(User user) throws SQLException {

    }

    @Override
    public User selectUser(int id) throws SQLException {
        return null;
    }

    @Override
    public List<User> selectAllUsers() throws SQLException {
        return null;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteUserById(int id) throws SQLException {
        return false;
    }
}
