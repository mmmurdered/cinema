package com.murdered.cinema.dao;

import com.murdered.cinema.model.Film;
import com.murdered.cinema.model.Session;
import com.murdered.cinema.model.user.User;

import javax.xml.transform.Result;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static  DBManager instance;

    private String SQL_URL = "jdbc:mysql://localhost:3306/cinema";
    private String SQL_LOGIN = "root";
    private String SQL_PASSWORD = "root";

    private String SQL_FIND_ALL_FILMS = "SELECT * FROM film";
    private String SQL_FIND_ALL_SESSIONS_ORDER_BY_DATE = "SELECT * FROM session ORDER BY time";
    private String SQL_INSERT_USER = "INSERT INTO user(email, password, login, role_id) VALUES(?, ?, ?, ?)";

    public static DBManager getInstance(){
        if(instance == null){
            return new DBManager();
        }
        return instance;
    }

    public Connection getConnection(){
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            con = DriverManager.getConnection(SQL_URL, SQL_LOGIN, SQL_PASSWORD);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | SQLException e){
            e.printStackTrace();
        }
        return con;
    }

    public List<Film> getFilms() throws SQLException {
        List<Film> filmList = new ArrayList<>();

        PreparedStatement statement = getConnection().prepareStatement(SQL_FIND_ALL_FILMS);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            Film tempFilm = new Film();

            tempFilm.setId(resultSet.getInt(1));
            tempFilm.setTitle(resultSet.getString(2));
            tempFilm.setDescription(resultSet.getString(3));
            tempFilm.setGenre(resultSet.getString(4));
            tempFilm.setDuration(resultSet.getInt(5));
            tempFilm.setImdbRating(resultSet.getDouble(6));

            filmList.add(tempFilm);
        }

        return filmList;
    }

    public static void main(String[] args) throws SQLException {
        DBManager dbManager = DBManager.getInstance();
        List<Session> sessions = dbManager.getSchedule();

        for(Session session : sessions){
            System.out.println(session);
        }
    }

    public List<Session> getSchedule() throws SQLException {
        List<Session> schedule = new ArrayList<>();

        PreparedStatement statement = getConnection().prepareStatement(SQL_FIND_ALL_SESSIONS_ORDER_BY_DATE);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            Session tempSession = new Session();

            tempSession.setId(resultSet.getInt(1));
            tempSession.setSessionFilm(resultSet.getInt(2));
            tempSession.setTime(resultSet.getTime(3));
            tempSession.setPrice(resultSet.getDouble(4));

            schedule.add(tempSession);
        }

        return schedule;
    }

    public List<Session> getActualSchedule(Time time){
        return null;
    }

    public int insertUser(User newUser) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(SQL_INSERT_USER);
        statement.setString(1, newUser.getEmail());
        statement.setString(2, newUser.getPassword());
        statement.setString(3, newUser.getLogin());
        statement.setInt(4, newUser.getRole().ordinal() + 1);

        int changedRows = statement.executeUpdate();

        return changedRows;
    }
}
