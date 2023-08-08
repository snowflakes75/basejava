package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage<T> extends AbstractStorage<File> {

    private File directory;
    protected T stream;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected boolean isExists(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        file.deleteOnExit();
        doSave(r, file);
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume r, File file);

    @Override
    protected Resume doGet(File file) {
        return doRead(file, stream);
    }

    protected abstract Resume doRead(File file, T stream);

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected List<Resume> getAllElements() {
        File[] files = directory.listFiles();
        List<Resume> resumeList = new ArrayList<>();
        for (File file : files) {
            resumeList.add(doRead(file, stream));
        }
        return resumeList;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        for (File file : files) {
            file.delete();
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.listFiles()).length;
    }
}
