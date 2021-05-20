package com.resume.webapp.storage;

import com.resume.webapp.storage.strategy.DataStreamStrategy;

public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(),new DataStreamStrategy()));
    }
}
