package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int lastPosition = 0;

    public void clear() {
        Arrays.fill(storage, 0, lastPosition, null);
        lastPosition = 0;
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            return;
        }
        System.out.println("Ошибка. Заданное резюме - " + resume.getUuid() + " - отсутствует.");
    }

    final public void save(Resume resume) {
        if (lastPosition >= storage.length) {
            System.out.println("Ошибка. Хранилище переполнено. " + resume.getUuid() + "- не сохранено");
            return;
        }
        int index = findIndex(resume.getUuid());
        //System.out.println("index = "+index);
        if (index >= 0) {
            System.out.println("Ошибка. Данное резюме - " + resume.getUuid() + " - уже существует.");
            return;
        }
        saveResume(resume, index);
        lastPosition++;
    }

    final public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Ошибка. Заданное резюме - " + uuid + " - отсутствует.");
        return null;
    }

    final public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Ошибка. Заданное резюме - " + uuid + " - отсутствует.");
            return;
        }
        deleteResume(index);
        storage[lastPosition - 1] = null;
        lastPosition--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, lastPosition);
    }

    public int size() {
        return lastPosition;
    }

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

    protected abstract int findIndex(String uuid);
}
