package service;

import model.User;

import java.util.List;

public interface UserService {

    List<User> getListUser();

    void updateUser(User user);

    void deleteUser(Integer id);

    User getUserById(Integer id);

    User getUserByName(String name);

    void createUser(User user);

}
