package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        Object searchKey = getIndex(r.getUuid());
        if (!isExists(searchKey)) {
            getNotExistingSearchKey(r.getUuid());
        } else {
            updateElements(searchKey, r);
        }
    }

    public void save(Resume r) {
        Object searchKey = getIndex(r.getUuid());
        if (isExists(searchKey)) {
            getExistingSearchKey(r.getUuid());
        } else {
            insertElementByIndex(r, searchKey);
        }
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getIndex(uuid);
        if (!isExists(searchKey)) {
            getNotExistingSearchKey(uuid);
        } else {
            deletedElementByIndex(searchKey);
        }
    }

    protected abstract boolean isExists(Object object);

    private void getExistingSearchKey(String uuid) {
        throw new ExistStorageException(uuid);
    }

    private void getNotExistingSearchKey(String uuid) {
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getIndex(uuid);
        if (!isExists(searchKey)) {
            getNotExistingSearchKey(uuid);
        }
        return getElementStorage(searchKey);
    }

    protected abstract Object getIndex(String uuid);

    protected abstract void updateElements(Object searchKey, Resume r);

    protected abstract void insertElementByIndex(Resume r, Object searchKey);

    protected abstract Resume getElementStorage(Object searchKey);

    protected abstract void deletedElementByIndex(Object searchKey);

}
