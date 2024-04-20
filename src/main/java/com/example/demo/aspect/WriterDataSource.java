package com.example.demo.aspect;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import java.sql.SQLFeatureNotSupportedException;

@ConfigurationProperties(prefix = "spring.datasource.writer")
public class WriterDataSource implements DataSource{
    private final DataSource delegate;

    public WriterDataSource(DataSource delegate) {
        this.delegate = delegate;
    }

    @Override
    public Connection getConnection() throws SQLException {
        //print all the datasource data
        Connection connection = delegate.getConnection();
        setAuroraReplicaReadConsistency(connection);
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return delegate.getConnection(username, password);
    }

    // Implement other methods of DataSource interface by delegating to the original DataSource

    private void setAuroraReplicaReadConsistency(Connection connection) throws SQLException {
        // Execute your custom query here
        connection.createStatement().execute("select 1;");
//        connection.createStatement().execute("SET aurora_replica_read_consistency = 'session'");
    }


    // Implementing all the methods of the DataSource interface
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return delegate.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        delegate.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        delegate.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return delegate.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return delegate.getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return delegate.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return delegate.isWrapperFor(iface);
    }
}
