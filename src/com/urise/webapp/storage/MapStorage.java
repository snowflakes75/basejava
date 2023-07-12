package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();
    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExists(Object object) {
        return storage.containsKey(object);
    }

    @Override
    protected Object getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected void updateElements(Object searchKey, Resume r) {
        storage.put((String) searchKey, r);
    }

    @Override
    protected void insertElementByIndex(Resume r, Object searchKey) {
        storage.put((String) searchKey, r);
    }

    @Override
    protected Resume getElementStorage(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void deletedElementByIndex(Object searchKey) {
        storage.remove((String) searchKey);
    }
}
