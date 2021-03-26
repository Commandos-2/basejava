package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    public void arraySaveOverflow(Storage storage) throws Exception {
        throw new StorageException("Successful test", "0");
    }
}
