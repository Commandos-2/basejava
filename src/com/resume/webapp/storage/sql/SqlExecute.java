package com.resume.webapp.storage.sql;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlExecute {
    ConnectionFactory connectionFactory;

    public SqlExecute(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /*public void sqlHelp(String sql, SqlHelp sqlHelp) {
        try (Connection conn = this.connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            sqlHelp.help(ps);
            return null;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }*/

    public  <T> T sqlHelp(String sql, SqlExecutor<T> sqlHelp) {
        try (Connection conn = this.connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return sqlHelp.help(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
