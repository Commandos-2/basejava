package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage implements Storage {
    static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            if(o1.getFullName().equals(o2.getFullName()))
            {
                return 0;
            }
            return o1.getFullName().compareTo(o2.getFullName());
        }
    }

    static final ResumeComparator RESUME_COMPARATOR = new ResumeComparator();

    public final void update(Resume resume) {
        updateResume(resume, getKeyIfNotExist(resume.getFullName()));
    }

    public final void save(Resume resume) {
        saveResume(resume, getKeyIfExist(resume.getFullName()));
    }

    public final Resume get(String fullName) {
        return (Resume) getResume(getKeyIfNotExist(fullName));
    }

    public final void delete(String fullName) {
        deleteResume(getKeyIfNotExist(fullName));
    }

    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract Object getResume(Object key);

    protected abstract void deleteResume(Object key);

    protected abstract Object findKey(String fullName);

    private Object getKeyIfNotExist(String fullName) {
        Object key = findKey(fullName);
        if (!isExist(key)) {
            throw new NotExistStorageException(fullName);
        }
        return key;
    }

    private Object getKeyIfExist(String fullName) {
        Object key = findKey(fullName);
        if (isExist(key)) {
            throw new ExistStorageException(fullName);
        }
        return key;
    }

    protected abstract boolean isExist(Object key);


}

