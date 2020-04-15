package service;

import model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    List<User> getListUser();

    User getUserById(Integer id);

    User getUserByName(String name);

    void updateUser(User user);

    void deleteUser(Integer id);

    User getUserByNameAndPassword (String name, String password);
}
