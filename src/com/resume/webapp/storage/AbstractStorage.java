package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    abstract public void clear();

    public final void update(Resume resume) {
        int index = checkСontainsResume(resume);
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateResume(resume, index);
    }

    public final void save(Resume resume) {
        int index = checkСontainsResume(resume);
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(resume, index);
    }

    public final Resume get(String uuid) {
        return checkNotExistStorageException(uuid);
    }

    public final void delete(String uuid) {
        checkNotExistStorageException(uuid);
        deleteResume(uuid);
    }

    abstract public Resume[] getAll();

    abstract public int size();

    public abstract int checkСontainsResume(Resume resume);

    public abstract void saveResume(Resume resume, int index);

    public abstract void updateResume(Resume resume, int index);

    public abstract Resume getResume(String uuid);

    public abstract void deleteResume(String uuid);

    private final Resume checkNotExistStorageException(String uuid) {
        Resume resume = getResume(uuid);
        if (null == resume) {
            throw new NotExistStorageException(uuid);
        }
        return resume;
    }
}
