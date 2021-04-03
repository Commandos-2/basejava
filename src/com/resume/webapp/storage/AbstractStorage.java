package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    /*static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            if(o1.getFullName().equals(o2.getFullName()))
            {
                return 0;
            }
            return o1.getFullName().compareTo(o2.getFullName());
        }
    }*/
    Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
    // static final ResumeComparator RESUME_COMPARATOR = new ResumeComparator();

    public final void update(Resume resume) {
        updateResume(resume, getKeyIfExist(resume.getUuid()));
    }

    public final void save(Resume resume) {
        saveResume(resume, getKeyIfNotExist(resume.getUuid()));
    }

    public final Resume get(String uuid) {
        return (Resume) getResume(getKeyIfExist(uuid));
    }

    public final void delete(String uuid) {
        deleteResume(getKeyIfExist(uuid));
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> sortedList = getAll();
        sortedList.sort(RESUME_COMPARATOR);
        return sortedList;
    }

    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract Object getResume(Object key);

    protected abstract void deleteResume(Object key);

    protected abstract Object findKey(String uuid);

    private Object getKeyIfExist(String uuid) {
        Object key = findKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object getKeyIfNotExist(String uuid) {
        Object key = findKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract boolean isExist(Object key);

    protected abstract List<Resume> getAll();
}

