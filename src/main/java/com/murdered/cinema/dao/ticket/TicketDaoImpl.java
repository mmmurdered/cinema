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
    public void update(Ticket ticket, String[] params) {

    }

    @Override
    public Ticket get(long id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Ticket> getAll() {
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
