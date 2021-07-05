package com.resume.webapp.storage;

import com.resume.webapp.Config;
import com.resume.webapp.ResumeTestData;
import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public abstract class AbstractStorageTest {
    protected Storage storage;
    protected final static File STORAGE_DIR = Config.get().getStorageDir();
    protected static final String UUID1 = randomUUID().toString();
    protected static final String UUID2 = randomUUID().toString();
    protected static final String UUID3 = randomUUID().toString();
    protected static final String UUID23 = randomUUID().toString();
    protected static final String FULL_NAME1 = "NAME1";
    protected static final String FULL_NAME2 = "NAME2";
    protected static final String FULL_NAME3 = "NAME3";
    protected static final String FULL_NAME23 = "NAME23";
    protected static final Resume resume1 = ResumeTestData.createResume(UUID1, FULL_NAME1);
    protected static final Resume resume2 = ResumeTestData.createResume(UUID2, FULL_NAME2);
    protected static final Resume resume3 = ResumeTestData.createResume(UUID23, FULL_NAME23);
    protected static final Resume resume4 = ResumeTestData.createResume(UUID3, FULL_NAME3);


    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume4);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        storage.update(resume2);
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(resume2, storage.get(UUID2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(resume3);
    }

    @Test
    public void save() throws Exception {
        storage.save(resume3);
        Assert.assertEquals(4, storage.size());
        storage.get(UUID23);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(resume2);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(resume2, storage.get(UUID2));
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
    public void getAllSorted() throws Exception {
        List<Resume> resumes = new ArrayList<>();
        resumes.add(resume1);
        resumes.add(resume2);
        resumes.add(resume3);
        resumes.add(resume4);
        storage.save(resume3);
        Assert.assertEquals(resumes, storage.getAllSorted());

    }
}
