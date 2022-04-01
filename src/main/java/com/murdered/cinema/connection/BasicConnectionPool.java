package com.murdered.cinema.connection;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool{
    private static final Logger logger = Logger.getLogger(BasicConnectionPool.class);
    private static BasicConnectionPool instance;

    private static final int INITIAL_POOL_SIZE = 15;
    private static final int MAX_POOL_SIZE = 100;
    private static final int MAX_TIMEOUT = 5;

    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();

    public static synchronized BasicConnectionPool getInstance(){
        if (instance == null) {
            instance = create();
        }
        return instance;
    }

    private BasicConnectionPool(List<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }

    public static BasicConnectionPool create() {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                pool.add(createConnection());
            } catch (NamingException | SQLException e) {
                e.printStackTrace();
            }
        }
        return new BasicConnectionPool(pool);
    }

    private static Connection createConnection() throws NamingException, SQLException {
        Context initialContext = new InitialContext();
        DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/cinema");

        logger.info("Connection is created");

        return dataSource.getConnection();
    }

    @Override
    public synchronized Connection getConnection() {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                try {
                    connectionPool.add(createConnection());
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            } else {
                logger.error("Maximum pool size reached, no available connections");
                throw new RuntimeException("Maximum pool size reached, no available connections!");
            }
        }

        Connection connection = connectionPool.remove(connectionPool.size() - 1);

        try {
            if (!connection.isValid(MAX_TIMEOUT)) {
                try {
                    connection = createConnection();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        logger.info("Connection was released");
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
        logger.info("Closing all connections");
    }

}
