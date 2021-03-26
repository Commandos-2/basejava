package com.resume.webapp.storage;


import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {
    private Storage storage;
    protected static final String UUID1 = "uuid1";
    protected static final String UUID2 = "uuid2";
    protected static final String UUID3 = "uuid3";
    protected static final String UUID23 = "uuid23";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID1));
        storage.save(new Resume(UUID2));
        storage.save(new Resume(UUID3));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        storage.update(new Resume(UUID2));
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(new Resume(UUID2), storage.get(UUID2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume(UUID23));
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume(UUID23));
        Assert.assertEquals(4, storage.size());
        storage.get(UUID23);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID2));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        arraySaveOverflow(storage);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(new Resume(UUID2), storage.get(UUID2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("Danny");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID2);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID23);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(new Resume(UUID1), resumes[0]);
        Assert.assertEquals(new Resume(UUID2), resumes[1]);
        Assert.assertEquals(new Resume(UUID3), resumes[2]);
        Assert.assertEquals(3, resumes.length);
    }

    public abstract void arraySaveOverflow(Storage storage) throws Exception;
}
