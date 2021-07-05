package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;
import com.resume.webapp.sql.ConnectionFactory;
import com.resume.webapp.storage.strategy.SqlHelp;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.resume.webapp.storage.strategy.SqlHelper.sqlHelp;

public class SqlStorage implements Storage {
    Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    @Override
    public void clear() {
        sqlHelp(this.getConnectionFactory(), "DELETE FROM resume", (SqlHelp) PreparedStatement::execute);
    }

    @Override
    public void update(Resume r) {
        checkExist(this.getConnectionFactory(), r.getUuid());
        sqlHelp(this.getConnectionFactory(), "UPDATE resume SET uuid=?,full_name=? WHERE uuid=?;", (x) -> {
            x.setString(1, r.getUuid());
            x.setString(2, r.getFullName());
            x.setString(3, r.getUuid());
            x.execute();
        });
    }

    @Override
    public void save(Resume r) {
        checkNotExist(this.getConnectionFactory(), r.getUuid());
        sqlHelp(this.getConnectionFactory(), "INSERT INTO resume(uuid,full_name) VALUES (?,?)", (x) -> {
            x.setString(1, r.getUuid());
            x.setString(2, r.getFullName());
            x.execute();
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelp(this.getConnectionFactory(), "SELECT * FROM resume r WHERE r.uuid=?", (x) -> {
            x.setString(1, uuid);
            ResultSet rs = x.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void delete(String uuid) {
        checkExist(this.getConnectionFactory(), uuid);
        sqlHelp(this.getConnectionFactory(), "DELETE FROM resume WHERE uuid=?", (x) -> {
            x.setString(1, uuid);
            x.execute();
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelp(this.getConnectionFactory(), "SELECT * FROM resume", (x) -> {
            ResultSet rs = x.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Resume(rs.getString("uuid").replaceAll(" ", ""), rs.getString("full_name")));
            }
            list.sort(RESUME_COMPARATOR);
            return list;
        });
    }

    @Override
    public int size() {
        return sqlHelp(this.getConnectionFactory(), "SELECT * FROM resume", (x) -> {
            ResultSet rs = x.executeQuery();
            int size = 0;
            while (rs.next()) {
                size++;
            }
            return size;
        });
    }

    private static void checkExist(ConnectionFactory connectionFactory, String uuid) {
        sqlHelp(connectionFactory, "SELECT * FROM resume r WHERE r.uuid=?", (x) -> {
            x.setString(1, uuid);
            ResultSet rs = x.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
        });
    }

    private static void checkNotExist(ConnectionFactory connectionFactory, String uuid) {
        sqlHelp(connectionFactory, "SELECT * FROM resume r WHERE r.uuid=?", (x) -> {
            x.setString(1, uuid);
            ResultSet rs = x.executeQuery();
            if (rs.next()) {
                throw new ExistStorageException(uuid);
            }
        });
    }
}
