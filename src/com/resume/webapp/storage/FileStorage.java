package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;
import com.resume.webapp.model.StrategyType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage {
    private final File directory;
    private StrategyType strategy=StrategyType.OBJECT_STREAM;

    public void setStrategy(StrategyType strategy) {
        this.strategy = strategy;
    }

    public FileStorage(File directory) {
        Objects.requireNonNull(directory,"The directory must not be null");
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
    protected void saveResume(Resume resume, Object file) {
        try {
            if (((File) file).createNewFile()) {
                doWrite(resume, new BufferedOutputStream(new FileOutputStream((File)file)));
            }
        } catch (IOException e) {
            throw new StorageException("Unable to create file", ((File) file).getName(), e);
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
    protected void updateResume(Resume resume, Object file) {
        try {
            doWrite(resume, new BufferedOutputStream(new FileOutputStream((File)file)));
        } catch (IOException e) {
            throw new StorageException("I/O error", ((File) file).getName(), e);
        }
    }

    @Override
    protected Object getResume(Object file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream((File)file)));
        } catch (IOException e) {
            throw new StorageException("File reading error", ((File) file).getName());
        }
    }

    @Override
    protected void deleteResume(Object file) {
        if (!((File) file).delete()) {
            throw new StorageException("File not deleted", ((File) file).getName());
        }
    }

    @Override
    protected Object findKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(Object file) {
        return ((File) file).exists();
    }

    @Override
    protected List<Resume> getAll() {
        File[] list = directory.listFiles();
        if (list != null) {
            List<Resume> listResume = new ArrayList<>();
            for (File file : list) {
                listResume.add((Resume) getResume((File) file));
            }
            return listResume;
        }
        throw new StorageException("File reading error", ((File) directory).getName());
    }

    @Override
    public void clear() {
        File[] list = directory.listFiles();
        if (list != null) {
            for (File file : list) {
                if (!file.delete()) {
                    throw new StorageException("File not deleted", ((File) file).getName());
                }
            }
        }
    }

    @Override
    public int size() {
        File[] list = directory.listFiles();
        if (list == null) {
            throw new StorageException("\n" +
                    "Error when requesting files in a directory", ((File) directory).getName());
        }
        return list.length;
    }
}
