package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;

import static org.junit.Assert.*;

public class ListStorageTest extends AbstractArrayStorageTest {
    public ListStorageTest()  {
        super(new ListStorage());
    }

    @Override
    public void arraySaveOverflow(Storage storage) throws Exception {
        throw new StorageException("Successful test","0");
    }
}
