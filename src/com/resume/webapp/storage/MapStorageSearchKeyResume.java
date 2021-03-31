package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

public class MapStorageSearchKeyResume extends MapStorage {
    @Override
    protected Object findKey(String fullName) {
        return storage.get(fullName);
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        storage.put(resume.getFullName(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage.replace(resume.getFullName(), resume);
    }

    @Override
    protected Object getResume(Object key) {
        return key;
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove(((Resume) key).getFullName());
    }
}
