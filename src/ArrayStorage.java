/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10];
    int lastPosition = 0;

    void clear() {
        for (int i = 0; storage[i] != null; i++) {
            storage[i].setUuid(null);
        }
        lastPosition = 0;
    }

    void save(Resume r) {
        storage[lastPosition] = r;
        lastPosition++;
    }

    Resume get(String uuid) {
        for (int i = 0; storage[i] != null; i++)
            if (storage[i].toString() == uuid) {
                return storage[i];
            }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; storage[i] != null; i++) {
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
        return storage;
    }

    int size() {
        return lastPosition;
    }
}
