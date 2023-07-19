package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract boolean isExists(Object object);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Object searchKey, Resume r);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    @Override
    public void update(Resume r) {
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        doUpdate(searchKey, r);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getExistingSearchKey(r);
        doSave(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getNotExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getNotExistingSearchKey(uuid);
        return doGet(searchKey);
    }


    private Object getExistingSearchKey(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExists(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExists(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
