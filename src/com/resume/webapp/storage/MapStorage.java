package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.*;

public abstract class MapStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new LinkedHashMap<>();

    abstract protected void saveResume(Resume resume, Object key);

    abstract protected void updateResume(Resume resume, Object key);

    abstract protected Object getResume(Object key);

    abstract protected void deleteResume(Object key);

    abstract protected Object findKey(String fullName);

    abstract protected boolean isExist(Object key);

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> sortedList = new ArrayList<Resume>(storage.values());
        sortedList.sort(RESUME_COMPARATOR);
        return sortedList;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
