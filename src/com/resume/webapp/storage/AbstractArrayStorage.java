package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int lastPosition = 0;

    public void clear() {
        Arrays.fill(storage, 0, lastPosition, null);
        lastPosition = 0;
    }

    public final void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            return;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    public final void save(Resume resume) {
        if (lastPosition >= storage.length) {
            throw new StorageException("Хранилище переполнено. Резюме не сохранено",resume.getUuid());
        }
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(resume, index);
        lastPosition++;
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(index);
        storage[lastPosition - 1] = null;
        lastPosition--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, lastPosition);
    }

    public int size() {
        return lastPosition;
    }

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

    protected abstract int findIndex(String uuid);
}
