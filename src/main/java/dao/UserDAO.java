package dao;

import model.User;

import java.util.List;

public interface UserDAO {

    //create
    void createUser(User user);

    //read
    List<User> getAllUsers();

    User getUserById(Integer id);

    //update
    boolean updateUser(User user);

    //delete
    boolean deleteUserById(Integer id);
}
