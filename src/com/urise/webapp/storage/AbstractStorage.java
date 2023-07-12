package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        Object index = getIndex(r.getUuid());
        if (!isExists(index)) {
            getNotExistingSearchKey(r.getUuid());
        } else {
            updateElements((Integer) index, r);
        }
    }

    public void save(Resume r) {
        Object index = getIndex(r.getUuid());
        if (isExists(index)) {
            getExistingSearchKey(r.getUuid());
        } else {
            insertElementByIndex(r, (Integer) index);
        }
    }

    @Override
    public void delete(String uuid) {
        Object index = getIndex(uuid);
        if (!isExists(index)) {
            getNotExistingSearchKey(uuid);
        } else {
            deletedElementByIndex((Integer) index);
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
        Object index = getIndex(uuid);
        if (!isExists(index)) {
            getNotExistingSearchKey(uuid);
        }
        return getElementStorage((Integer) index);
    }

    protected abstract Object getIndex(String uuid);

    protected abstract void updateElements(int index, Resume r);

    protected abstract void insertElementByIndex(Resume r, int index);

    protected abstract Resume getElementStorage(int index);

    protected abstract void deletedElementByIndex(int index);

}
