package com.resume.webapp.storage;

import com.resume.webapp.storage.strategy.ObjectStreamStrategy;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR,new ObjectStreamStrategy()));
    }
}
