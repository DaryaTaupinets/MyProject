package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    //create
    void insertUser(User user) throws SQLException;

    //read
    List<User> selectAllUsers() throws SQLException;

    User selectUser(int id) throws SQLException;

    //update
    boolean updateUser(User user) throws SQLException;

    //delete
    boolean deleteUserById(int id) throws SQLException;
}
