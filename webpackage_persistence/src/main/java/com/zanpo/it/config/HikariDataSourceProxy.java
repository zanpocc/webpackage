package com.zanpo.it.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 02:06
 */
public class HikariDataSourceProxy implements DataSource {

    @Setter
    @Getter
    private HikariDataSource dataSource;

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return dataSource.getConnection(username, password);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return dataSource.unwrap(iface);
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return dataSource.isWrapperFor(iface);
    }

    public PrintWriter getLogWriter() throws SQLException {
        return dataSource.getLogWriter();
    }

    public void setLogWriter(PrintWriter out) throws SQLException {
        dataSource.setLogWriter(out);
    }

    public void setLoginTimeout(int seconds) throws SQLException {
        dataSource.setLoginTimeout(seconds);
    }

    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return dataSource.getParentLogger();
    }
}
