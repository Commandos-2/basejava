package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;
import org.junit.Assert;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Override
    public void arraySaveOverflow(Storage storage) throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Ошибка сохранения Резюме в хранилище. Отрицательный результат теста");
        }
        storage.save(new Resume());
    }

    @Override
    public void getAllTest(Resume[] resumes) throws Exception {
        Assert.assertEquals(new Resume(UUID1), resumes[0]);
        Assert.assertEquals(new Resume(UUID2), resumes[1]);
        Assert.assertEquals(new Resume(UUID3), resumes[2]);
    }
}
