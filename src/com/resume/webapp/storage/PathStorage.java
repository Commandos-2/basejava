package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final Strategy strategy;

    public PathStorage(String dir, Strategy strategy) {
        this.directory = Paths.get(dir);
        Objects.requireNonNull(directory, "The directory must not benull");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " it is not a directory or it is not possible to write/read information to the directory");
        }
        Objects.requireNonNull(strategy, "The strategy must not benull");
        this.strategy = strategy;
    }

    @Override
    protected void saveResume(Resume resume, Path file) {
        try {
            Files.createFile(file);
            if (Files.exists(file)) {
                updateResume(resume, file);
            }
        } catch (IOException e) {
            throw new StorageException("Unable to create file", file.toString(), e);
        }
    }

    protected void doWrite(Resume resume, OutputStream os) throws IOException {
        strategy.doWrite(resume, os);
    }

    protected Resume doRead(InputStream is) throws IOException {
        return strategy.doRead(is);
    }

    @Override
    protected void updateResume(Resume resume, Path file) {
        try {
            doWrite(resume, new BufferedOutputStream(Files.newOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("I/O error", file.toString(), e);
        }
    }

    @Override
    protected Resume getResume(Path file) {
        try {
            return doRead(new BufferedInputStream(Files.newInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File reading error", file.toString());
        }
    }

    @Override
    protected void deleteResume(Path file) {
        try {
            Files.delete(file);
        } catch (IOException e) {
            throw new StorageException("File not deleted", file.toString());
        }
    }

    @Override
    protected Path findKey(String uuid) {
        return Paths.get(String.valueOf(directory)).resolve(uuid);
    }

    @Override
    protected boolean isExist(Path file) {
        return Files.exists(file);
    }

    @Override
    protected List<Resume> getAll() {
        try {
            return Files.list(directory).map(this::getResume).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Path not added", null);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Path not deleted", null);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("\n" +
                    "Error when requesting files in a directory", directory.toString());
        }
    }
}
