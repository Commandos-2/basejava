package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int lastPosition = 0;

    public void clear() {
        Arrays.fill(storage, 0, lastPosition, null);
        lastPosition = 0;
    }

    @Override
    protected List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOf(storage, lastPosition));
    }

    public int size() {
        return lastPosition;
    }

    @Override
    protected void saveResume(Resume resume, Integer key) {
        if (lastPosition >= storage.length) {
            throw new StorageException("The storage is full. Resume not saved", resume.getUuid());
        }
        saveResumeToArray(resume, key);
        lastPosition++;
    }

    @Override
    protected final void updateResume(Resume resume, Integer key) {
        storage[key] = resume;
    }

    @Override
    protected final Resume getResume(Integer key) {
        return storage[key];
    }

    @Override
    protected final void deleteResume(Integer key) {
        deleteResumeFromArray(key);
        storage[lastPosition - 1] = null;
        lastPosition--;
    }

    protected abstract void saveResumeToArray(Resume resume, int index);

    protected abstract void deleteResumeFromArray(int index);

    @Override
    protected boolean isExist(Integer key) {
        return key >= 0;
    }

    protected abstract Integer findKey(String uuid);
}
