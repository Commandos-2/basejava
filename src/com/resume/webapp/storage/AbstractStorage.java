package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void update(Resume resume) {
        updateResume(resume, checkNotExistStorageException(resume.getUuid()));
    }

    public final void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(resume, index);
    }

    public final Resume get(String uuid) {
        return getResume(checkNotExistStorageException(uuid),uuid);
    }

    public final void delete(String uuid) {
        deleteResume(checkNotExistStorageException(uuid),uuid);
    }

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract Resume getResume(int index,String uuid);

    protected abstract void deleteResume(int index, String uuid);

    protected abstract int findIndex(String uuid);

    private int checkNotExistStorageException(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }
}
