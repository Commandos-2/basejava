package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] catsArray = (Resume[]) storage.toArray(new Resume[0]);
        return catsArray;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public int checkСontainsResume(Resume resume) {
        if (storage.contains(resume)) {
            return 1;
        }
        return -1;
    }

    @Override
    public void saveResume(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    public void updateResume(Resume resume, int index) {
        storage.set(storage.indexOf(resume), resume);
    }

    @Override
    public Resume getResume(String uuid) {
        Resume resume;
        for (int i = 0; i < storage.size(); i++) {
            resume = (Resume) storage.get(i);
            if (resume.getUuid() == uuid) {
                return resume;
            }
        }
        return null;
    }

    @Override
    public void deleteResume(String uuid) {
        Iterator iterator = storage.iterator();
        Resume resume;
        while (iterator.hasNext()) {
            resume = (Resume) iterator.next();
            if (resume.getUuid() == uuid) {
                iterator.remove();
                return;
            }
        }
    }
}
