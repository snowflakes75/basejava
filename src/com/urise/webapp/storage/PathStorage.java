
package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private final Path directory;

    private final Serialization strategy;

    protected PathStorage(String dir, Serialization strategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.strategy = strategy;
    }

    private List<Path> getListPathResume() throws IOException {
        try (Stream<Path> list = Files.list(directory)) {
            return list.collect(Collectors.toList());
        }
    }

    @Override
    public void clear() {
        try {
            getListPathResume().forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        try {
            return getListPathResume().size();
        } catch (IOException e) {
            throw new StorageException("Directory read error", directory.getFileName().toString(), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory.toUri()).resolve(uuid);
    }

    @Override
    protected void doUpdate(Path file, Resume r) {
        File fileToSave = file.toFile();
        try {
            strategy.doWrite(r, new BufferedOutputStream(Files.newOutputStream(fileToSave.toPath())));
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExists(Path file) {
        return Files.exists(file);
    }

    @Override
    protected void doSave(Resume r, Path file) {
        try {
            Files.createFile(file);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.toAbsolutePath().toString(), file.toString(), e);
        }
        doUpdate(file, r);
    }

    @Override
    protected Resume doGet(Path file) {
        try {
            File fileNeedGet = file.toFile();
            return strategy.doRead(new BufferedInputStream(Files.newInputStream(fileNeedGet.toPath())));
        } catch (IOException e) {
            throw new StorageException("File read error", file.toString(), e);
        }
    }

    @Override
    protected void doDelete(Path file) {
        try {
            Files.delete(file);
        } catch (IOException e) {
            throw new StorageException("File delete error", file.getFileName().toString());
        }
    }

    @Override
    protected List<Resume> getAllElements() {
        try {
            return getListPathResume()
                    .stream()
                    .map(this::doGet)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}