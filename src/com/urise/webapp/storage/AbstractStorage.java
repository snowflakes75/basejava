package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        Object searchKey = getIndex(uuid);
        getNotExistingSearchKey(uuid, searchKey);
        updateElements(searchKey, r);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getIndex(r.getUuid());
        getExistingSearchKey(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getIndex(uuid);
        getNotExistingSearchKey(uuid, searchKey);
        deletedElementByIndex(searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getIndex(uuid);
        getNotExistingSearchKey(uuid, searchKey);
        return getElementStorage(searchKey);
    }

    protected abstract boolean isExists(Object object);

    protected abstract Object getIndex(String uuid);

    protected abstract void updateElements(Object searchKey, Resume r);

    protected abstract void insertElementByIndex(Resume r, Object searchKey);

    protected abstract Resume getElementStorage(Object searchKey);

    protected abstract void deletedElementByIndex(Object searchKey);

    private void getNotExistingSearchKey(String uuid, Object searchKey) {
        if (!isExists(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
    }

    private void getExistingSearchKey(Resume r, Object searchKey) {
        if (isExists(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertElementByIndex(r, searchKey);
        }
    }
}
