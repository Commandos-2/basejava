package com.resume.webapp.storage.strategy;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public static void sqlHelp(ConnectionFactory connectionFactory, String sql, SqlHelp sqlHelp) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            sqlHelp.help(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public static  <T> T sqlHelp(ConnectionFactory connectionFactory, String sql, SqlHelpT <T> sqlHelp) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return sqlHelp.help(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
