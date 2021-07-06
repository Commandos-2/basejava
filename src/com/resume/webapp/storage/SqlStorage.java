package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;
import com.resume.webapp.storage.sql.SqlExecute;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SqlStorage implements Storage {
    Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
    public final SqlExecute SqlExecute;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        SqlExecute = new SqlExecute(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        getSqlExecute().sqlHelp("DELETE FROM resume", (ps) -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        getSqlExecute().sqlHelp("UPDATE resume SET full_name=? WHERE uuid=?;", (ps) -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        checkNotExist(uuid);
        getSqlExecute().sqlHelp("INSERT INTO resume(uuid,full_name) VALUES (?,?)", (ps) -> {
            ps.setString(1, uuid);
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return getSqlExecute().sqlHelp("SELECT * FROM resume r WHERE r.uuid=?", (ps) -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void delete(String uuid) {
        getSqlExecute().sqlHelp("DELETE FROM resume WHERE uuid=?", (ps) -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return getSqlExecute().sqlHelp("SELECT * FROM resume", (ps) -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            list.sort(RESUME_COMPARATOR);
            return list;
        });
    }

    @Override
    public int size() {
        return getSqlExecute().sqlHelp("SELECT count(*) FROM resume", (ps) -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    public SqlExecute getSqlExecute() {
        return SqlExecute;
    }

    private void checkNotExist(String uuid) {
        getSqlExecute().sqlHelp("SELECT * FROM resume r WHERE r.uuid=?", (ps) -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                throw new ExistStorageException(uuid);
            }
            return null;
        });
    }
}
