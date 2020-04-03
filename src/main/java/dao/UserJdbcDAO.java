package dao;


import model.User;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class UserJdbcDAO implements UserDAO {

    private static final Logger log = Logger.getLogger(UserJdbcDAO.class.getName());

    private Connection connection = DBHelper.getConnection();
    private boolean deleted;
    private boolean updating;

    private static final String INSERT_USERS_SQL = "INSERT INTO users (name, age, email, location) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "select id, name, age, email, location from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?";
    private static final String UPDATE_USERS_SQL = "update users set name = ?, age = ?, email = ?, location = ? where id = ?";

    @Override
    public void createUser(User user) {
        log.info(INSERT_USERS_SQL);
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setByte(2, user.getAge());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getLocation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.info("SQLException in method createUser(User user)");
        }
    }

    @Override
    public List<User> getAllUsers() {
        log.info(SELECT_ALL_USERS);
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Byte age = resultSet.getByte("age");
                String email = resultSet.getString("email");
                String location = resultSet.getString("location");
                users.add(new User(id, name, age, email, location));
            }
        } catch (SQLException e) {
            log.info("SQLException in method getAllUsers()");
        }
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        log.info(SELECT_USER_BY_ID);
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Byte age = resultSet.getByte("age");
                String email = resultSet.getString("email");
                String location = resultSet.getString("location");
                user = new User(id, name, age, email, location);
            }
        } catch (SQLException e) {
            log.info("SQLException in method getUserById(int id)");
        }
        return user;
    }


    @Override
    public boolean updateUser(User user) {
        log.info(UPDATE_USERS_SQL);
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            statement.setString(1, user.getName());
            statement.setByte(2, user.getAge());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLocation());
            statement.setInt(5, user.getId());

            updating = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.info("SQLException in method updateUser(User user)");
        }
        return updating;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        log.info(DELETE_USERS_SQL);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL)) {
            statement.setInt(1, id);
            deleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.info("SQLException in method deleteUserById(int id)");
        }
        return deleted;
    }
}
