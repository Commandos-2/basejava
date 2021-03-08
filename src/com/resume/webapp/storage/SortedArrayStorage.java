package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

import static java.lang.Math.abs;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume resume, int index) {
        if (lastPosition == 0) {
            storage[lastPosition] = resume;
            lastPosition++;
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
    protected void deleteResume(int index) {
        for (int i = index; i < lastPosition; i++) {
            storage[i] = storage[i + 1];
        }
        lastPosition--;
        return;
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, lastPosition, searchKey);
    }
}
