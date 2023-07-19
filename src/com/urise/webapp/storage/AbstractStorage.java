package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        updateElements(searchKey, r);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getExistingSearchKey(r);
        insertElementByIndex(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getNotExistingSearchKey(uuid);
        deletedElementByIndex(searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getNotExistingSearchKey(uuid);
        return getElementStorage(searchKey);
    }

    protected abstract boolean isExists(Object object);

    protected abstract Object getIndex(String uuid);

    protected abstract void updateElements(Object searchKey, Resume r);

    protected abstract void insertElementByIndex(Resume r, Object searchKey);

    protected abstract Resume getElementStorage(Object searchKey);

    protected abstract void deletedElementByIndex(Object searchKey);

    private Object getExistingSearchKey(Resume r) {
        Object searchKey = getIndex(r.getUuid());
        if (isExists(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getIndex(uuid);
        if (!isExists(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
