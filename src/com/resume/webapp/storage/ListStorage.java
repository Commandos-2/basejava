package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> storage = new ArrayList<Resume>();



    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume resume) {
        if (storage.contains(resume)) {
            storage.set(storage.indexOf(resume),resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void save(Resume resume) {
        if (storage.contains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            storage.add(resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        Iterator iterator = storage.iterator();
        Resume resume;
        while (iterator.hasNext()) {
            resume = (Resume) iterator.next();
            if (resume.getUuid() == uuid) {
                return resume;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        Iterator iterator = storage.iterator();
        Resume resume;
        while (iterator.hasNext()) {
            resume = (Resume) iterator.next();
            if (resume.getUuid() == uuid) {
                iterator.remove();
                return;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        Resume[] catsArray = storage.toArray(new Resume[0]);
        return catsArray;
    }

    @Override
    public int size() {
        return storage.size();
    }

}
