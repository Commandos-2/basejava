package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

public class MapStorageSearchKeyResume extends AbstractMapStorage<Resume> {

    @Override
    protected Resume findKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume key) {
        return key != null;
    }

    @Override
    protected void saveResume(Resume resume, Resume key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Resume key) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Resume key) {
        return key;
    }

    @Override
    protected void deleteResume(Resume key) {
        storage.remove(key.getUuid());
    }
}
