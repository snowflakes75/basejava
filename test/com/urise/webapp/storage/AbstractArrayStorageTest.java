package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public abstract class AbstractArrayStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private Resume r1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        String uuid_4 = "uuid4";
        Resume r4 = new Resume(uuid_4);
        storage.save(r4);
        assertEquals(r4, storage.get(uuid_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(r1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < 10_000; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("Overflow too early");
        }
        storage.save(new Resume());
    }

    @Test
    public void get() {
        String uuid_4 = "uuid4";
        Resume r4 = new Resume(uuid_4);
        storage.save(r4);
        assertEquals(r4, storage.get(uuid_4));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume r4 = new Resume(UUID_1);
        storage.update(r4);
        assertEquals(r4, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume r4 = new Resume();
        storage.update(r4);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        Resume r4 = new Resume();
        storage.delete(r4.getUuid());
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[]{r1};

    }
}