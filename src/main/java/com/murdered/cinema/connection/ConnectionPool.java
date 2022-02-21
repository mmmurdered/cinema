package com.murdered.cinema.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ConnectionPool {

    Connection getConnection() throws SQLException;

    List<Connection> getConnectionPool();

    int getSize();

    boolean releaseConnection(Connection connection);

    void shutdown() throws SQLException;
}
