package com.resume.webapp.storage;

import com.resume.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.get().getSqlStorage());
    }
}

