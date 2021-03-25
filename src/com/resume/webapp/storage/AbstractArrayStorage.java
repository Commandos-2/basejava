package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int lastPosition = 0;

    public void clear() {
        Arrays.fill(storage, 0, lastPosition, null);
        lastPosition = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, lastPosition);
    }

    public int size() {
        return lastPosition;
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        if (lastPosition >= storage.length) {
            throw new StorageException("Хранилище переполнено. Резюме не сохранено", resume.getUuid());
        }
        saveResumeToArray(resume, index);
        lastPosition++;
    }

    @Override
    protected final void updateResume(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected final Resume getResume(int index,String uuid) {
        return storage[index];
    }

    @Override
    protected final void deleteResume(int index,String uuid) {
        deleteResumeFromArray(index);
        storage[lastPosition - 1] = null;
        lastPosition--;
    }

    protected abstract void saveResumeToArray(Resume resume, int index);

    protected abstract void deleteResumeFromArray(int index);

    protected abstract int findIndex(String uuid);
}
