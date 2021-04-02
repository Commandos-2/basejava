package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

public class MapStorageSearchKeyResume extends AbstractMapStorage {

    @Override
    protected Object findKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected Object getResume(Object key) {
        return key;
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove(((Resume) key).getUuid());
    }
}
