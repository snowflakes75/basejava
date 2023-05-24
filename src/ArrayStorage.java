import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int capacity = 0;

    void clear() {
        Arrays.fill(storage, null);
        capacity = 0;
    }

    void save(Resume r) {
        storage[capacity++] = r;
    }

    Resume get(String uuid) {
        Resume searchResume = null;
        for (int i = 0; i < capacity; i++) {
            if (storage[i].uuid.equals(uuid)) {
                searchResume = storage[i];
                break;
            }
        }
        return searchResume;
    }

    void delete(String uuid) {
        int index = -1;
        for (int i = 0; i < capacity; i++) {
            if (storage[i].uuid.equals(uuid)) {
                index = i;
                break;
            }
        }
        System.arraycopy(storage, index + 1, storage, index, capacity);
        capacity--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, capacity);
    }

    int size() {
        return capacity;
    }
}
