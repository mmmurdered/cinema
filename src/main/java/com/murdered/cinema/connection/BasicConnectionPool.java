package com.murdered.cinema.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool{
    private static final int INITIAL_POOL_SIZE = 15;
    private static final int MAX_POOL_SIZE = 100;
    private static final int MAX_TIMEOUT = 1337; //TODO

    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();

    public BasicConnectionPool(List<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }

    public static BasicConnectionPool create() throws SQLException {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                pool.add(createConnection());
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return new BasicConnectionPool(pool);
    }

    private static Connection createConnection() throws NamingException, SQLException {
        Context initialContext = new InitialContext();
        DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/cinema");

        return dataSource.getConnection();
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);

        return connection;
    }

    @Override
    public List<Connection> getConnectionPool() {
        return connectionPool;
    }

    @Override
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public void shutdown() throws SQLException {
        usedConnections.forEach(this::releaseConnection);
        for(Connection connection : connectionPool){
            connection.close();
        }
        connectionPool.clear();
    }

    public static void main(String[] args) throws SQLException, NamingException {
        BasicConnectionPool basicConnectionPool = BasicConnectionPool.create();
        System.out.println(basicConnectionPool.getConnection());
    }
}
