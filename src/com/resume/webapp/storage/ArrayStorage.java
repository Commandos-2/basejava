package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, lastPosition, null);
        lastPosition = 0;
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
            return;
        }

        System.out.println("Ошибка. Заданное резюме - " + resume.getUuid() + " - отсутствует.");
    }

    public void save(Resume resume) {
        if (findIndex(resume.getUuid()) != -1) {
            System.out.println("Ошибка. Данное резюме - " + resume.getUuid() + " - уже существует.");
            return;
        }
        if (lastPosition < storage.length) {
            storage[lastPosition] = resume;
            lastPosition++;
        } else {
            System.out.println("Ошибка. Хранилище переполнено. " + resume.getUuid() + "- не сохранено");
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = storage[lastPosition - 1];
            storage[lastPosition - 1] = null;
            lastPosition--;
            return;
        }
        System.out.println("Ошибка. Заданное резюме - " + uuid + " - отсутствует.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, lastPosition);
    }

    // функция проверки наличия резюме с указанным uuid в базе (при наличи возвращает индекс в базе)
    protected int findIndex(String uuid) {
        for (int i = 0; i < lastPosition; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }
}
