package com.resume.webapp.storage.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlHelp {
    void help(PreparedStatement ps)throws SQLException;
}
