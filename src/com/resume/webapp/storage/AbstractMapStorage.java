package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.*;

public abstract class AbstractMapStorage<R> extends AbstractStorage<R> {
    protected final Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
