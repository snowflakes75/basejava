package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExists(Object index) {
        return (int) index >= 0;
    }

    @Override
    protected Object getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void updateElements(Object searchKey, Resume r) {
        storage.set((Integer) searchKey, r);
    }

    @Override
    protected Resume getElementStorage(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected void insertElementByIndex(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    protected void deletedElementByIndex(Object searchKey) {
        storage.remove((int) searchKey);
    }

}