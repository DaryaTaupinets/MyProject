package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void insertUser(User user) throws SQLException;

    User selectUser(int id) throws SQLException;

    List<User> selectAllUsers() throws SQLException;

    boolean updateUser(User user) throws SQLException;

    boolean deleteUserById(int id) throws SQLException;

}
