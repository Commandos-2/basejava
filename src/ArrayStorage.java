/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int lastPosition = 0;

    void clear() {
        for (int i = 0; i < lastPosition; i++) {
            storage[i] = null;
        }
        lastPosition = 0;
    }

    void save(Resume r) {
        storage[lastPosition] = r;
        lastPosition++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < lastPosition; i++) {
            if (storage[i].toString() == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < lastPosition; i++) {
            if (storage[i].toString() == uuid) {
                storage[i] = storage[lastPosition - 1];
                storage[lastPosition - 1] = null;
                lastPosition--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] getStorage = new Resume[lastPosition];
        for (int i = 0; i < lastPosition; i++) {
            getStorage[i] = storage[i];
        }
        return getStorage;
    }

    int size() {
        return lastPosition;
    }
}
