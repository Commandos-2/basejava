package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

public abstract class AbstractStorage implements Storage{

    abstract public void clear();

    abstract public void update(Resume r);

    abstract public void save(Resume r);

    abstract public Resume get(String uuid);

    abstract public void delete(String uuid);

    abstract public Resume[] getAll();

    abstract public int size();
}
