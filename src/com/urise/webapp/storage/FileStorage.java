package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    private final File directory;

    private final Serialization strategy;

    protected FileStorage(File directory, Serialization strategy) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.strategy = strategy;
    }

    private File[] getFiles(String messageError) {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException(messageError, null);
        }
        return files;
    }

    @Override
    public void clear() {
        File[] files = getFiles("Directory clean error");
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        File[] files = getFiles("Directory read error");
        return files.length;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        try {
            strategy.doWrite(r, new BufferedOutputStream(Files.newOutputStream(file.toPath())));
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExists(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(file, r);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return strategy.doRead(new BufferedInputStream(Files.newInputStream(file.toPath())));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected List<Resume> getAllElements() {
        File[] files = getFiles("Directory read error");
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(doGet(file));
        }
        return list;
    }
}