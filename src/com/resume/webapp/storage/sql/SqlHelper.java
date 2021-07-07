package com.resume.webapp.storage.sql;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.StorageException;
import com.resume.webapp.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
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

    public  <T> T sqlHelp(String sql, SqlExecutor<T> execut) {
        try (Connection conn = this.connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return execut.execute(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(e.toString());
            }else {
                throw new StorageException(e);
            }

        }
    }
}
