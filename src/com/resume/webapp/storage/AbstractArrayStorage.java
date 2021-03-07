package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int lastPosition = 0;

    public int size() {
        return lastPosition;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Ошибка. Заданное резюме - " + uuid + " - отсутствует.");
        return null;
    }

    protected abstract int findIndex(String uuid);
}
