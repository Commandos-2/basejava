package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected void saveResume(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get((String) key);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove((String) key);
    }

    @Override
    protected String findKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object key) {
        return storage.get((String)key)!=null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
