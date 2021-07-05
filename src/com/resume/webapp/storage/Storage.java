package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

public interface Storage {
    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    Object getAllSorted();

    int size();
}
