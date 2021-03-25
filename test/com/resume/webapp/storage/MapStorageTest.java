package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;
import org.junit.Assert;

import static java.util.Arrays.sort;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    public void arraySaveOverflow(Storage storage) throws Exception {
        throw new StorageException("Successful test", "0");
    }

    @Override
    public void getAllTest(Resume[] resumes) throws Exception {
        sort(resumes);
        Assert.assertEquals(new Resume(UUID1), resumes[0]);
        Assert.assertEquals(new Resume(UUID2), resumes[1]);
        Assert.assertEquals(new Resume(UUID3), resumes[2]);
    }
}
