package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;
import com.resume.webapp.model.StrategyType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private StrategyType strategy=StrategyType.OBJECT_STREAM;

    public void setStrategy(StrategyType strategy) {
        this.strategy = strategy;
    }

    public PathStorage(String dir) {
        this.directory = Paths.get(dir);
        Objects.requireNonNull(directory, "The directory must not benull");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " it is not a directory or it is not possible to write/read information to the directory");
        }
    }

    @Override
    protected void saveResume(Resume resume, Path file) {
        try {
            Files.createFile(file);
            if (Files.exists(file)) {
                doWrite(resume, new BufferedOutputStream(new FileOutputStream(String.valueOf(file))));
            }
        } catch (IOException e) {
            throw new StorageException("Unable to create file", file.toString(), e);
        }
    }

    protected  void doWrite(Resume resume, OutputStream os) throws IOException
    {
        strategy.getRealization().doWrite(resume,os);
    }

    protected  Resume doRead(InputStream is) throws IOException
    {
        return strategy.getRealization().doRead(is);
    }

    @Override
    protected void updateResume(Resume resume, Path file) {
        try {
            doWrite(resume, new BufferedOutputStream(new FileOutputStream((String.valueOf(file)))));
        } catch (IOException e) {
            throw new StorageException("I/O error", file.toString(), e);
        }
    }

    @Override
    protected Resume getResume(Path file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream((String.valueOf(file)))));
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
        return Paths.get(String.valueOf(directory), uuid);
    }

    @Override
    protected boolean isExist(Path file) {
        return Files.exists(file);
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> listResume = new ArrayList<>();
        try {
            Files.list(directory).forEach(file -> {
                listResume.add(getResume(file));
            });
        } catch (IOException e) {
            throw new StorageException("Path not added", null);
        }
        return listResume;
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
