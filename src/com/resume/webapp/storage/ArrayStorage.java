package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume resume, int index) {
        storage[lastPosition] = resume;
        lastPosition++;
    }

    @Override
    protected void deleteResume(int index) {
        storage[index] = storage[lastPosition - 1];
        storage[lastPosition - 1] = null;
        lastPosition--;
        return;
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
