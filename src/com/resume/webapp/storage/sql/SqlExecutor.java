package com.resume.webapp.storage.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlExecutor<T> {
    T help(PreparedStatement ps)throws SQLException;
}
