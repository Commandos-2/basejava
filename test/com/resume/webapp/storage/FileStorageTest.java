package com.resume.webapp.storage;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STRORAGE_DIR));
    }
}
