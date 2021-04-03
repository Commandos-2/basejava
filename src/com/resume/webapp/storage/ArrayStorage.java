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

    @Override
    protected Integer findKey(String uuid) {
        for (int i = 0; i < lastPosition; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
