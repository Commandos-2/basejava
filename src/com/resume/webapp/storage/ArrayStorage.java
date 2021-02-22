package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int lastPosition = 0;

    public void clear() {
        for (int i = 0; i < lastPosition; i++) {
            storage[i] = null;
        }
        lastPosition = 0;
    }

    public void update(Resume r) {
        for (int i = 0; i < lastPosition; i++) {
            if (storage[i].toString() == r.toString()) {
                storage[i] = r;
                return;
            }
        }
        System.out.println("Ошибка. Заданное резюме отсутствует.");
    }

    public void save(Resume r) {
        for (int i = 0; i < lastPosition; i++) {
            if (storage[i].toString() == r.toString()) {
                System.out.println("Ошибка. Данное резюме уже существует.");
                return;
            }
        }
        if (lastPosition < storage.length) {
            storage[lastPosition] = r;
            lastPosition++;
        } else {
            System.out.println("Ошибка. Хранилище переполнено.");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < lastPosition; i++) {
            if (storage[i].toString() == uuid) {
                return storage[i];
            }
        }
        System.out.println("Ошибка. Заданное резюме отсутствует.");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < lastPosition; i++) {
            if (storage[i].toString() == uuid) {
                storage[i] = storage[lastPosition - 1];
                storage[lastPosition - 1] = null;
                lastPosition--;
                return;
            }
        }
        System.out.println("Ошибка. Заданное резюме отсутствует.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] getStorage = new Resume[lastPosition];
        for (int i = 0; i < lastPosition; i++) {
            getStorage[i] = storage[i];
        }
        return getStorage;
    }

    public int size() {
        return lastPosition;
    }
}
