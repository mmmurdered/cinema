package com.murdered.cinema.dao.ticket;

import com.murdered.cinema.connection.BasicConnectionPool;
import com.murdered.cinema.dao.QUERY;
import com.murdered.cinema.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketDaoImpl implements TicketDao{
    private static TicketDaoImpl instance;
    private Connection connection;
    private BasicConnectionPool basicConnectionPool;

    private TicketDaoImpl(){
        try {
            basicConnectionPool = BasicConnectionPool.create();
            connection = basicConnectionPool.getConnection();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static TicketDaoImpl getInstance(){
        if(instance == null){
            instance = new TicketDaoImpl();
        }
        return instance;
    }

    @Override
    public Ticket save(Ticket ticket) {
        try{
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_INSERT_TICKET.query());

            statement.setInt(1, ticket.getUserId());
            statement.setInt(2, ticket.getSessionId());
            statement.setInt(3, ticket.getSessionFilmId());
            statement.setInt(4, ticket.getSeatId());

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
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
        try{
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_NUM_OF_TICKETS_ON_SESSION.query());
            statement.setLong(1, sessionId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                numOfTickets = resultSet.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return numOfTickets;
    }
}
