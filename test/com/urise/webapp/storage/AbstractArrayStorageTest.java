package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;
public abstract class AbstractArrayStorageTest {

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_NOT_EXIST = "uuid4";

    private static final Resume r1 = new Resume(UUID_1);
    private static final Resume r2 = new Resume(UUID_2);
    private static final Resume r3 = new Resume(UUID_3);
    private static final Resume resumeNotExist = new Resume(UUID_NOT_EXIST);

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        assertArrayEquals(new Resume[] {}, storage.getAll());
    }

    @Test
    public void save() {
        storage.save(resumeNotExist);
        assertSize(4);
        assertGet(resumeNotExist);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(r1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("Overflow too early");
        }
        storage.save(new Resume());
    }

    @Test
    public void get() {
        for (Resume resume: storage.getAll()) {
            assertGet(resume);
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void update() {
        Resume r4 = new Resume(UUID_2);
        storage.update(r4);
        assertSize(3);
        assertGet(r4);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume r4 = new Resume();
        storage.update(r4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        assertGet(r1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        Resume r4 = new Resume();
        storage.delete(r4.getUuid());
    }

    @Test
    public void getAll() {
        Resume[] excepted = new Resume[] {r1, r2, r3};
        assertArrayEquals(excepted, storage.getAll());
    }
}