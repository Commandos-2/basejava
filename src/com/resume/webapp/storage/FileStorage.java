package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;
import com.resume.webapp.model.StrategyType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private StrategyType strategy = StrategyType.OBJECT_STREAM;

    public void setStrategy(StrategyType strategy) {
        this.strategy = strategy;
    }

    public FileStorage(File directory) {
        Objects.requireNonNull(directory, "The directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "not a directory.");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " \n" +
                    "it is not possible to write/read information to the directory");
        }
        this.directory = directory;
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
            if (file.createNewFile()) {
                doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
            }
        } catch (IOException e) {
            throw new StorageException("Unable to create file", file.getName(), e);
        }
    }

    protected void doWrite(Resume resume, OutputStream os) throws IOException {
        strategy.getRealization().doWrite(resume, os);
    }

    protected Resume doRead(InputStream is) throws IOException {
        return strategy.getRealization().doRead(is);
    }

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("I/O error", file.getName(), e);
        }
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File reading error", file.getName());
        }
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("File not deleted", file.getName());
        }
    }

    @Override
    protected File findKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> getAll() {
        File[] list = directory.listFiles();
        if (list != null) {
            List<Resume> listResume = new ArrayList<>();
            for (File file : list) {
                listResume.add(getResume(file));
            }
            return listResume;
        }
        throw new StorageException("File reading error", (directory).getName());
    }

    @Override
    public void clear() {
        File[] list = directory.listFiles();
        if (list != null) {
            for (File file : list) {
                if (!file.delete()) {
                    throw new StorageException("File not deleted", file.getName());
                }
            }
        }
    }

    @Override
    public int size() {
        File[] list = directory.listFiles();
        if (list == null) {
            throw new StorageException("\n" +
                    "Error when requesting files in a directory", directory.getName());
        }
        return list.length;
    }
}
