package com.resume.webapp.storage;


import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;
    protected static final String UUID1 = "uuid1";
    protected static final String UUID2 = "uuid2";
    protected static final String UUID3 = "uuid3";
    protected static final String UUID23 = "uuid23";
    protected static final String FULL_NAME1 = "NAME1";
    protected static final String FULL_NAME2 = "NAME2";
    protected static final String FULL_NAME3 = "NAME3";
    protected static final String FULL_NAME23 = "NAME23";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID1, FULL_NAME1));
        storage.save(new Resume(UUID2, FULL_NAME2));
        storage.save(new Resume(UUID3, FULL_NAME3));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        storage.update(new Resume(UUID2, FULL_NAME2));
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(new Resume(UUID2, FULL_NAME2), storage.get(FULL_NAME2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume(UUID23, FULL_NAME23));
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume(UUID23, FULL_NAME23));
        Assert.assertEquals(4, storage.size());
        storage.get(FULL_NAME23);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID2, FULL_NAME2));
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(new Resume(UUID2, FULL_NAME2), storage.get(FULL_NAME2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("Danny");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(FULL_NAME2);
        Assert.assertEquals(2, storage.size());
        storage.get(FULL_NAME2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(FULL_NAME23);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> resumes = storage.getAllSorted();
        Assert.assertEquals(new Resume(UUID1, FULL_NAME1), resumes.get(0));
        Assert.assertEquals(new Resume(UUID2, FULL_NAME2), resumes.get(1));
        Assert.assertEquals(new Resume(UUID3, FULL_NAME3), resumes.get(2));
    }
}
