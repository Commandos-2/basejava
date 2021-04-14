package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<Resume>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        storage.add(resume);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage.set((Integer) key, resume);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove(((Integer) key).intValue());
    }

    @Override
    protected boolean isExist(Object key) {
        return (Integer) key != -1;
    }

    @Override
    protected Integer findKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if ((storage.get(i)).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}