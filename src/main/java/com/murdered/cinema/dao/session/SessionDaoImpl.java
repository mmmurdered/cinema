package com.murdered.cinema.dao.session;

import com.murdered.cinema.connection.BasicConnectionPool;
import com.murdered.cinema.dao.QUERY;
import com.murdered.cinema.dao.film.FilmDaoImpl;
import com.murdered.cinema.model.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionDaoImpl implements SessionDao{
    private static SessionDaoImpl instance;
    private Connection connection;
    private BasicConnectionPool basicConnectionPool;

    private SessionDaoImpl(){
        try {
            basicConnectionPool = BasicConnectionPool.create();
            connection = basicConnectionPool.getConnection();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static SessionDaoImpl getInstance(){
        if(instance == null){
            instance = new SessionDaoImpl();
        }
        return instance;
    }

    @Override
    public Session save(Session session) {
        try{
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_INSERT_SESSION.query());
            statement.setInt(1, session.getSessionFilmId());
            statement.setTimestamp(2, session.getTime());
            statement.setDouble(3, session.getPrice());
            statement.setInt(4, session.getAvailablePlaces());

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        //todo handling exceptions (affected rows)
        return session;
    }

    @Override
    public void update(Session session, String[] params) {

    }

    @Override
    public Session get(long id) {
        return null;
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_DELETE_SESSION_BY_ID.query());

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Session> getAll() {
        List<Session> schedule = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_ALL_SESSIONS_ORDER_BY_DATE.query());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Session tempSession = new Session();

                tempSession.setId(resultSet.getInt(1));
                tempSession.setSessionFilm(resultSet.getInt(2));
                tempSession.setTime(resultSet.getTimestamp(3));
                tempSession.setPrice(resultSet.getDouble(4));
                tempSession.setAvailablePlaces(resultSet.getInt(5));

                schedule.add(tempSession);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return schedule;
    }

    @Override
    public List<Session> getSessionByName() {
        List<Session> schedule = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_SESSION_ORDER_BY_NAME.query());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Session tempSession = new Session();

                tempSession.setId(resultSet.getInt(2));
                tempSession.setSessionFilm(resultSet.getInt(1));
                tempSession.setTime(resultSet.getTimestamp(4));
                tempSession.setPrice(resultSet.getDouble(3));
                tempSession.setAvailablePlaces(resultSet.getInt(5));

                schedule.add(tempSession);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return schedule;
    }

    @Override
    public List<Session> getSessionByDate() {
        return null;
    }

    @Override
    public List<Session> getSessionByAvailablePlaces() {
        List<Session> schedule = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_ALL_SESSIONS_ORDER_BY_PLACES.query());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Session tempSession = new Session();

                tempSession.setId(resultSet.getInt(1));
                tempSession.setSessionFilm(resultSet.getInt(2));
                tempSession.setTime(resultSet.getTimestamp(3));
                tempSession.setPrice(resultSet.getDouble(4));
                tempSession.setAvailablePlaces(resultSet.getInt(5));

                schedule.add(tempSession);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return schedule;
    }
}
