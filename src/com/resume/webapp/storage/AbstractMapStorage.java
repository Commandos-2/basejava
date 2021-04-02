package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.*;

public abstract class AbstractMapStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getAllSortedImplementation() {
        List<Resume> sortedList = new ArrayList<Resume>(storage.values());
        return sortedList;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
