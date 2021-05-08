package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

public class MapStorageSearchKeyUuid extends AbstractMapStorage<String> {

    @Override
    protected String findKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String key) {
        return storage.containsKey(key);
    }

    @Override
    protected void saveResume(Resume resume, String key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume, String key) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(String key) {
        return storage.get(key);
    }

    @Override
    protected void deleteResume(String key) {
        storage.remove(key);
    }
}
