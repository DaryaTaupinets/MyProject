package service;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface Service {

    List<User> listUser() throws SQLException;

    void updateUser(int id, String name, byte age, String email, String location) throws SQLException;

    void deleteUser(int id) throws SQLException;

    User showEditForm(int id) throws SQLException;

    void insertUser(String name, byte age, String email, String location) throws SQLException;

}
