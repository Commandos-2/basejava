package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResumeToArray(Resume resume, int index) {
        storage[lastPosition] = resume;
    }

    @Override
    protected void deleteResumeFromArray(int index) {
        storage[index] = storage[lastPosition - 1];
    }

    // функция проверки наличия резюме с указанным uuid в базе (при наличи возвращает индекс в базе)
    @Override
    protected Integer findKey(String fullName) {
        for (int i = 0; i < lastPosition; i++) {
            if (storage[i].getFullName().equals(fullName)) {
                return i;
            }
        }
        return -1;
    }
}
