package com.resume.webapp.storage;

import com.resume.webapp.storage.strategy.ObjectStreamStrategy;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(),new ObjectStreamStrategy()));
    }
}
