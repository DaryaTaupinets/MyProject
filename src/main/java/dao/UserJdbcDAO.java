package dao;


import model.User;
import util.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class UserJdbcDAO implements UserDAO {

    private static final Logger log = Logger.getLogger(UserJdbcDAO.class.getName());

    private static final String INSERT_USERS_SQL = "INSERT INTO users (name, age, email, location) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "select id, name, age, email, location from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?";
    private static final String UPDATE_USERS_SQL = "update users set name = ?, age = ?, email = ?, location = ? where id = ?";

    @Override
    public void insertUser(User user) throws SQLException {
        log.info(INSERT_USERS_SQL);
        try (PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setByte(2, user.getAge());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getLocation());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<User> selectAllUsers() throws SQLException {
        log.info(SELECT_ALL_USERS);
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                byte age = resultSet.getByte("age");
                String email = resultSet.getString("email");
                String location = resultSet.getString("location");
                users.add(new User(id, name, age, email, location));
            }
        }
        return users;
    }

    @Override
    public User selectUser(int id) throws SQLException {
        log.info(SELECT_USER_BY_ID);
        User user = null;
        try (PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    byte age = resultSet.getByte("age");
                    String email = resultSet.getString("email");
                    String location = resultSet.getString("location");
                    user = new User(id, name, age, email, location);
                }
            }
            return user;
        }
    }


    @Override
    public boolean updateUser(User user) throws SQLException {
        log.info(UPDATE_USERS_SQL);
        boolean updating;
        try (PreparedStatement statement = DBHelper.getConnection().prepareStatement(UPDATE_USERS_SQL)) {
            statement.setString(1, user.getName());
            statement.setByte(2, user.getAge());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLocation());
            statement.setInt(5, user.getId());

            updating = statement.executeUpdate() > 0;
        }
        return updating;
    }

    @Override
    public boolean deleteUserById(int id) throws SQLException {
        log.info(DELETE_USERS_SQL);
        boolean deleted;
        try (PreparedStatement statement = DBHelper.getConnection().prepareStatement(DELETE_USERS_SQL)) {
            statement.setInt(1, id);
            deleted = statement.executeUpdate() > 0;
        }
        return deleted;
    }
}
