package com.murdered.cinema.dao.user;

import com.murdered.cinema.connection.BasicConnectionPool;
import com.murdered.cinema.dao.QUERY;
import com.murdered.cinema.dao.film.FilmDaoImpl;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.murdered.cinema.dao.QUERY.SQL_FIND_USER_BY_LOGIN_PASS;

public class UserDaoImpl implements UserDao{
    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        User tempUser = new User();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_PASS.query());
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                tempUser.setId(resultSet.getInt("id"));
                tempUser.setLogin(resultSet.getString("login"));
                tempUser.setPassword(resultSet.getString("password"));
                tempUser.setEmail(resultSet.getString("email"));
                tempUser.setRole(UserRole.values()[resultSet.getInt("role_id") - 1]);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return tempUser;
    }

    private static UserDaoImpl instance;
    private Connection connection;
    private BasicConnectionPool basicConnectionPool;

    private UserDaoImpl(){
        try {
            basicConnectionPool = BasicConnectionPool.create();
            connection = basicConnectionPool.getConnection();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static UserDaoImpl getInstance(){
        if(instance == null){
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public User save(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_INSERT_USER.query());

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getLogin());
            statement.setInt(4, user.getRole().ordinal() + 1);

            int changedRows = statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            //TODO logger
        }
        return user;
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
