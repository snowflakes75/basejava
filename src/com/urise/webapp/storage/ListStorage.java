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
    protected void updateElements(int index, Resume r) {
        storage.set(index, r);
    }

    @Override
    protected Resume getElementStorage(int index) {
        return storage.get(index);
    }

    @Override
    protected void insertElementByIndex(Resume r, int index) {
        storage.add(r);
    }

    @Override
    protected void deletedElementByIndex(int index) {
        storage.remove(index);
    }

}