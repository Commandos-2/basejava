package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int lastPosition = 0;

    public void clear() {
        Arrays.fill(storage, 0, lastPosition, null);
        lastPosition = 0;
    }

    public List<Resume> getAllSorted() {
        List<Resume> sortedList= new ArrayList<Resume>(lastPosition);
        sortedList.addAll(Arrays.asList(storage).subList(0, lastPosition));
      //  sortedList.sort(RESUME_COMPARATOR);
        return sortedList;
    }

    public int size() {
        return lastPosition;
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        if (lastPosition >= storage.length) {
            throw new StorageException("Хранилище переполнено. Резюме не сохранено", resume.getFullName());
        }
        saveResumeToArray(resume, (Integer) key);
        lastPosition++;
    }

    @Override
    protected final void updateResume(Resume resume, Object key) {
        storage[(Integer) key] = resume;
    }

    @Override
    protected final Resume getResume(Object key) {
        return storage[(Integer) key];
    }

    @Override
    protected final void deleteResume(Object key) {
        deleteResumeFromArray((Integer) key);
        storage[lastPosition - 1] = null;
        lastPosition--;
    }

    protected abstract void saveResumeToArray(Resume resume, int index);

    protected abstract void deleteResumeFromArray(int index);

    @Override
    protected boolean isExist(Object key) {
        return (Integer) key >= 0;
    }

    protected abstract Integer findKey(String fullName);
}
