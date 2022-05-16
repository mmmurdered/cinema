package com.murdered.cinema.dao.ticket;

import com.murdered.cinema.connection.BasicConnectionPool;
import com.murdered.cinema.dao.QUERY;
import com.murdered.cinema.model.Ticket;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {
    private static Logger logger = Logger.getLogger(TicketDaoImpl.class);

    private static TicketDaoImpl instance;
    private BasicConnectionPool basicConnectionPool;

    private TicketDaoImpl() {
        basicConnectionPool = BasicConnectionPool.getInstance();
    }

    public static TicketDaoImpl getInstance() {
        if (instance == null) {
            instance = new TicketDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Ticket> getTicketsByUserId(long userId) {
        List<Ticket> ticketList = new ArrayList<>();
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_TICKET_BY_USER.query());
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Ticket tempTicket = new Ticket();
                tempTicket.setId(resultSet.getInt(1));
                tempTicket.setUserId(resultSet.getInt(2));
                tempTicket.setSessionId(resultSet.getInt(3));
                tempTicket.setSessionFilmId(resultSet.getInt(4));

                ticketList.add(tempTicket);
            }
        } catch (SQLException e) {
            logger.info("Error: getting tickets by user id from database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }
        return ticketList;
    }

    @Override
    public Ticket save(Ticket ticket) {
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_INSERT_TICKET.query());

            statement.setInt(1, ticket.getUserId());
            statement.setInt(2, ticket.getSessionId());
            statement.setInt(3, ticket.getSessionFilmId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Error: adding tickets to database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }

        return ticket;
    }


    @Override
    public Ticket get(long id) {
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY.SQL_FIND_TICKET_BY_ID.query());
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Ticket ticket = new Ticket();
            while (resultSet.next()){
                ticket.setId(resultSet.getInt(1));
                ticket.setUserId(resultSet.getInt(2));
                ticket.setSessionId(resultSet.getInt(3));
                ticket.setSessionFilmId(resultSet.getInt(4));
            }
            return ticket;
        } catch (SQLException e) {
            logger.info("Error: getting ticket by id");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_DELETE_TICKET_BY_ID.query());
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Error: deleting ticket by id from database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Ticket> getAll() {
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY.SQL_FIND_ALL_TICKETS.query());
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Ticket> ticketList = new ArrayList<>();
            while (resultSet.next()){
                Ticket ticket = new Ticket();

                ticket.setId(resultSet.getInt(1));
                ticket.setUserId(resultSet.getInt(2));
                ticket.setSessionId(resultSet.getInt(3));
                ticket.setSessionFilmId(resultSet.getInt(4));

                ticketList.add(ticket);
            }
            return ticketList;
        } catch (SQLException e) {
            logger.info("Error: getting all tickets");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getNumOfTicketsBySessionId(long sessionId) {
        int numOfTickets = 0;
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_NUM_OF_TICKETS_ON_SESSION.query());
            statement.setLong(1, sessionId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                numOfTickets = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.info("Error: getting number of tickets by session id from database");
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }
        return numOfTickets;
    }
}
