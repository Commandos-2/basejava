package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage {
    private final File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " не является каталогом.");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " в каталог невозможно записать/считать информацию");
        }
        this.directory = directory;
    }

    @Override
    protected void saveResume(Resume resume, Object file) {
        try {
            ((File) file).createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("Ошибка ввода/вывода", ((File) file).getName(), e);
        }
    }

    protected abstract void doWrite(Resume resume, Object file) throws IOException;

    @Override
    protected void updateResume(Resume resume, Object file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("Ошибка ввода/вывода", ((File) file).getName(), e);
        }
    }

    @Override
    protected abstract Object getResume(Object key);

    @Override
    protected void deleteResume(Object file) {
        ((File) file).delete();
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
        List<Resume> listResume=new ArrayList<>();
        for (File file : list) {
            listResume.add((Resume) getResume((File) file));
        }
        return listResume;
    }

    @Override
    public void clear() {
        File[] list = directory.listFiles();
        for (File file : list) {
            file.delete();
        }
    }

    @Override
    public int size() {
        File[] list = directory.listFiles();
        return list.length;
    }
}
