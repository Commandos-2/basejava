package com.resume.webapp.storage.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlHelpT<T> {
    T help(PreparedStatement ps)throws SQLException;
}
