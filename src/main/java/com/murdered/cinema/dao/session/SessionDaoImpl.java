package com.murdered.cinema.dao.session;

import com.murdered.cinema.connection.BasicConnectionPool;
import com.murdered.cinema.dao.QUERY;
import com.murdered.cinema.dao.film.FilmDaoImpl;
import com.murdered.cinema.model.Session;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDaoImpl implements SessionDao {
    private static Logger logger = Logger.getLogger(SessionDaoImpl.class);

    private static SessionDaoImpl instance;
    private BasicConnectionPool basicConnectionPool;

    private SessionDaoImpl() {
        basicConnectionPool = BasicConnectionPool.getInstance();
    }

    public static SessionDaoImpl getInstance() {
        if (instance == null) {
            instance = new SessionDaoImpl();
        }
        return instance;
    }

    @Override
    public Session save(Session session) {
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_INSERT_SESSION.query());
            statement.setInt(1, session.getSessionFilmId());
            statement.setTimestamp(2, session.getTime());
            statement.setDouble(3, session.getPrice());
            statement.setInt(4, session.getAvailablePlaces());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Error: adding session to database");
            //e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }
        return session;
    }

    @Override
    public void update(Session session, String[] params) {

    }

    @Override
    public Session get(long id) {
        Session session = new Session();
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_SESSION_BY_ID.query());
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                session.setId(resultSet.getInt(1));
                session.setSessionFilm(resultSet.getInt(2));
                session.setTime(resultSet.getTimestamp(3));
                session.setPrice(resultSet.getDouble(4));
                session.setAvailablePlaces(resultSet.getInt(5));
            }
        } catch (SQLException e) {
            logger.info("Error: getting session by id from database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }

        return session;
    }

    @Override
    public void delete(int id) {
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_DELETE_SESSION_BY_ID.query());

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Error: deleting session by id from database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Session> getAll() {
        List<Session> schedule = new ArrayList<>();
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_ALL_SESSIONS_ORDER_BY_DATE.query());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Session tempSession = new Session();

                tempSession.setId(resultSet.getInt(1));
                tempSession.setSessionFilm(resultSet.getInt(2));
                tempSession.setTime(resultSet.getTimestamp(3));
                tempSession.setPrice(resultSet.getDouble(4));
                tempSession.setAvailablePlaces(resultSet.getInt(5));

                schedule.add(tempSession);
            }
        } catch (SQLException e) {
            logger.info("Error: getting all sessions from database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }

        return schedule;
    }

    @Override
    public List<Session> getSessionByName() {
        List<Session> schedule = new ArrayList<>();
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_SESSION_ORDER_BY_NAME.query());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Session tempSession = new Session();

                tempSession.setId(resultSet.getInt(2));
                tempSession.setSessionFilm(resultSet.getInt(1));
                tempSession.setTime(resultSet.getTimestamp(4));
                tempSession.setPrice(resultSet.getDouble(3));
                tempSession.setAvailablePlaces(resultSet.getInt(5));

                schedule.add(tempSession);
            }
        } catch (SQLException e) {
            logger.info("Error: getting session by name from database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
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
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_ALL_SESSIONS_ORDER_BY_PLACES.query());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Session tempSession = new Session();

                tempSession.setId(resultSet.getInt(1));
                tempSession.setSessionFilm(resultSet.getInt(2));
                tempSession.setTime(resultSet.getTimestamp(3));
                tempSession.setPrice(resultSet.getDouble(4));
                tempSession.setAvailablePlaces(resultSet.getInt(5));

                schedule.add(tempSession);
            }
        } catch (SQLException e) {
            logger.info("Error: getting session by places from database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }

        return schedule;
    }

    @Override
    public List<Session> getSessionAfterTime(Timestamp time) {
        List<Session> schedule = new ArrayList<>();
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_SESSION_AFTER.query());
            statement.setTimestamp(1, time);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Session tempSession = new Session();

                tempSession.setId(resultSet.getInt(1));
                tempSession.setSessionFilm(resultSet.getInt(2));
                tempSession.setTime(resultSet.getTimestamp(3));
                tempSession.setPrice(resultSet.getDouble(4));
                tempSession.setAvailablePlaces(resultSet.getInt(5));

                schedule.add(tempSession);
            }
        } catch (SQLException e) {
            logger.info("Error: getting session after time from database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }

        return schedule;
    }
}
