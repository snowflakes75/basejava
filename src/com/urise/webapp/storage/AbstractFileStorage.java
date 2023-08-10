package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage<T> extends AbstractStorage<File> {

    private final File directory;
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

    private void checkDirectoryAccess(File[] files, String message) {
        if (Objects.isNull(files)) {
            throw new StorageException(message, directory.getName());
        }
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
        doDelete(file);
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

    @Override
    protected Resume doGet(File file) {
        return doRead(file, stream);
    }

    @Override
    protected void doDelete(File file) {
        boolean delete = file.delete();
        if (delete) throw new StorageException("Can`t delete file", file.getName());
    }

    @Override
    protected List<Resume> getAllElements() {
        File[] files = directory.listFiles();
        checkDirectoryAccess(files, "Can`t find files in directory");
        List<Resume> resumeList = new ArrayList<>();
        for (File file : files) {
            resumeList.add(doGet(file));
        }
        return resumeList;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        checkDirectoryAccess(files, "Can`t clear directory");
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        File[] files = directory.listFiles();
        checkDirectoryAccess(files, "Can`t count files in directory");
        return files.length;
    }

    protected abstract void doWrite(Resume r, File file);

    protected abstract Resume doRead(File file, T stream);
}
