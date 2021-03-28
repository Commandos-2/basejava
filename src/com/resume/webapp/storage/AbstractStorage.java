package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void update(Resume resume) {
        updateResume(resume, checkNotExistStorageException(resume.getUuid()));
    }

    public final void save(Resume resume) {
        saveResume(resume, checkExistStorageException(resume.getUuid()));
    }

    public final Resume get(String uuid) {
        return getResume(checkNotExistStorageException(uuid));
    }

    public final void delete(String uuid) {
        deleteResume(checkNotExistStorageException(uuid));
    }

    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract Resume getResume(Object key);

    protected abstract void deleteResume(Object key);

    protected abstract Object findKey(String uuid);

    private Object checkNotExistStorageException(String uuid) {
        Object key = findKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object checkExistStorageException(String uuid) {
        Object key = findKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract boolean isExist(Object key);
}
