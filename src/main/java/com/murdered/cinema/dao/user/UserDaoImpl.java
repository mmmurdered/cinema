package com.murdered.cinema.dao.user;

import com.murdered.cinema.connection.BasicConnectionPool;
import com.murdered.cinema.dao.QUERY;
import com.murdered.cinema.dao.film.FilmDaoImpl;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static com.murdered.cinema.dao.QUERY.*;

public class UserDaoImpl implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);

    private static UserDaoImpl instance;
    private BasicConnectionPool basicConnectionPool;

    private UserDaoImpl() {
        basicConnectionPool = BasicConnectionPool.getInstance();
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        User tempUser = new User();
        try {
            Connection connection = basicConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_PASS.query());
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tempUser.setId(resultSet.getInt("id"));
                tempUser.setLogin(resultSet.getString("login"));
                tempUser.setPassword(resultSet.getString("password"));
                tempUser.setEmail(resultSet.getString("email"));
                tempUser.setRole(UserRole.values()[resultSet.getInt("role_id") - 1]);
            }

            return tempUser;
        } catch (SQLException e) {
            logger.info("Error: getting user by credentials from database");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User save(User user) {
        try {
            Connection connection = basicConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_INSERT_USER.query());

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getLogin());
            statement.setInt(4, user.getRole().ordinal() + 1);

            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            logger.info("Error: adding user to database");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public User get(long id) {
        User user = new User();
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_USER_BY_ID.query());

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setLogin(resultSet.getString(4));
                user.setRole(UserRole.values()[resultSet.getInt("role_id") - 1]);
            }
        } catch (SQLException e) {
            logger.info("Error: getting user by id from database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        User tempUser = new User();
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN.query());
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tempUser.setId(resultSet.getInt("id"));
                tempUser.setLogin(resultSet.getString("login"));
                tempUser.setPassword(resultSet.getString("password"));
                tempUser.setEmail(resultSet.getString("email"));
                tempUser.setRole(UserRole.values()[resultSet.getInt("role_id") - 1]);
            }

            return tempUser;
        } catch (SQLException e) {
            logger.info("Error: getting user by login from database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }

        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        User tempUser = new User();
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL.query());
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tempUser.setId(resultSet.getInt("id"));
                tempUser.setLogin(resultSet.getString("login"));
                tempUser.setPassword(resultSet.getString("password"));
                tempUser.setEmail(resultSet.getString("email"));
                tempUser.setRole(UserRole.values()[resultSet.getInt("role_id") - 1]);
            }

            return tempUser;
        } catch (SQLException e) {
            logger.info("Error: getting user by credentials from database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }

        return null;
    }
}
