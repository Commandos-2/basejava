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
    public Resume[] getAll() {
        return (Resume[]) storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void saveResume(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    public void updateResume(Resume resume, int index) {
        storage.set(index, resume);
    }

    @Override
    public Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    public void deleteResume(int index) {
        storage.remove(index);
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (((Resume) storage.get(i)).getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }
}