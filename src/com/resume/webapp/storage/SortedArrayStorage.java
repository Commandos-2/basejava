package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

import static java.lang.Math.abs;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void clear() {
        Arrays.fill(storage, 0, lastPosition, null);
        lastPosition = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
            return;
        }

        System.out.println("Ошибка. Заданное резюме - " + resume.getUuid() + " - отсутствует.");
    }

    @Override
    public void save(Resume resume) {
        if (lastPosition >= storage.length) {
            System.out.println("Ошибка. Хранилище переполнено. " + resume.getUuid() + "- не сохранено");
            return;
        }
        if (lastPosition == 0) {
            storage[lastPosition] = resume;
            lastPosition++;
            return;
        }
        int index = findIndex(resume.getUuid());
        //System.out.println("index = "+index);
        if (index >= 0) {
            System.out.println("Ошибка. Данное резюме - " + resume.getUuid() + " - уже существует.");
            return;
        }
        index = abs(index) - 1;
        for (int i = lastPosition; i > index; i--) {
            storage[i] = storage[i - 1];
        }
        storage[index] = resume;
        lastPosition++;
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            for (int i = index; i < lastPosition; i++) {
                storage[i] = storage[i + 1];
            }
            lastPosition--;
            return;
        }
        System.out.println("Ошибка. Заданное резюме - " + uuid + " - отсутствует.");
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, lastPosition);
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, lastPosition, searchKey);
    }
}
