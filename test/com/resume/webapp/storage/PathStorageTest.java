package com.resume.webapp.storage;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STRING_STRORAGE_DIR));
    }
}
