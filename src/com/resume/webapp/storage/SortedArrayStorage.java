package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.abs;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResumeToArray(Resume resume, int index) {
        index = abs(index) - 1;
        if (lastPosition - index > 0) System.arraycopy(storage, index, storage, index + 1, lastPosition - index);
        storage[index] = resume;
    }

    @Override
    protected void deleteResumeFromArray(int index) {
        if (lastPosition - 1 - index >= 0)
            System.arraycopy(storage, index + 1, storage, index, lastPosition - 1 - index);
    }

    @Override
    protected Integer findKey(String uuid) {
        Resume searchKey = new Resume(uuid, uuid);
        return Arrays.binarySearch(storage, 0, lastPosition, searchKey, Comparator.comparing(Resume::getUuid));
    }
}
