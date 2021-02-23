package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int lastPosition = 0;

    public void clear() {
        Arrays.fill(storage, 0, lastPosition, null);
        lastPosition = 0;
    }

    public void update(Resume resume) {
        if (checkExistence(resume) != -1) {
            storage[checkExistence(resume)] = resume;
            return;
        }

        System.out.println("Ошибка. Заданное резюме -" + resume.toString() + "- отсутствует.");
    }

    public void save(Resume resume) {
        if (checkExistence(resume) != -1) {
            System.out.println("Ошибка. Данное резюме -" + resume.toString() + "- уже существует.");
            return;
        }
        if (lastPosition < storage.length) {
            storage[lastPosition] = resume;
            lastPosition++;
        } else {
            System.out.println("Ошибка. Хранилище переполнено. " + resume.toString() + "- не сохранено");
        }
    }

    public Resume get(String uuid) {
        if (checkExistence(uuid) != -1) {
            return storage[checkExistence(uuid)];
        }
        System.out.println("Ошибка. Заданное резюме -" + uuid + "- отсутствует.");
        return null;
    }

    public void delete(String uuid) {
        if (checkExistence(uuid) != -1) {
            storage[checkExistence(uuid)] = storage[lastPosition - 1];
            storage[lastPosition - 1] = null;
            lastPosition--;
            return;
        }
        System.out.println("Ошибка. Заданное резюме отсутствует.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] getStorage = new Resume[lastPosition];
        getStorage = Arrays.copyOfRange(storage, 0, lastPosition);
        return getStorage;
    }

    public int size() {
        return lastPosition;
    }

    public int checkExistence(Resume resume) { // функция проверки наличия указанного резюме в базе (при наличи возвращает индекс в базе)
        for (int i = 0; i < lastPosition; i++) {
            if (storage[i].toString() == resume.toString()) {
                return i;
            }
        }
        return -1;
    }

    public int checkExistence(String uuid) { // перегруженная функция проверки наличия резюме с указанным uuid в базе (при наличи возвращает индекс в базе)
        for (int i = 0; i < lastPosition; i++) {
            if (storage[i].toString() == uuid) {
                return i;
            }
        }
        return -1;
    }
}
