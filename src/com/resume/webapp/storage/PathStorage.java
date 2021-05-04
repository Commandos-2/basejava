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

public class PathStorage extends AbstractStorage {
    private final Path directory;
    private StrategyType strategy=StrategyType.OBJECT_STREAM;

    public void setStrategy(StrategyType strategy) {
        this.strategy = strategy;
    }

    public PathStorage(String dir) {
        this.directory = Paths.get(dir);
        Objects.requireNonNull(directory, "Каталог не должен быть null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " не является каталогом или в каталог невозможно записать/считать информацию");
        }
    }

    @Override
    protected void saveResume(Resume resume, Object file) {
        try {
            Files.createFile((Path) file);
            if (Files.exists((Path) file)) {
                doWrite(resume, new BufferedOutputStream(new FileOutputStream(String.valueOf((Path) file))));
            }
        } catch (IOException e) {
            throw new StorageException("Невозможно создать файл", ((Path) file).toString(), e);
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
            doWrite(resume, new BufferedOutputStream(new FileOutputStream((String.valueOf((Path) file)))));
        } catch (IOException e) {
            throw new StorageException("Ошибка ввода/вывода", ((Path) file).toString(), e);
        }
    }

    @Override
    protected Object getResume(Object file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream((String.valueOf((Path) file)))));
        } catch (IOException e) {
            throw new StorageException("Ошибка чтения файла", ((Path) file).toString());
        }
    }

    @Override
    protected void deleteResume(Object file) {
        try {
            Files.delete((Path) file);
        } catch (IOException e) {
            throw new StorageException("Файл не удален", ((Path) file).toString());
        }
    }

    @Override
    protected Object findKey(String uuid) {
        return Paths.get(String.valueOf(directory), uuid);//new File(directory, uuid);
    }

    @Override
    protected boolean isExist(Object file) {
        return Files.exists((Path) file);
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> listResume = new ArrayList<>();
        try {
            Files.list(directory).forEach(file -> {
                listResume.add((Resume) getResume((Path) file));
            });
        } catch (IOException e) {
            throw new StorageException("Path не добавлен", null);
        }
        return listResume;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Path не удален", null);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Ошибка при запросе файлов в каталоге", ((Path) directory).toString());
        }
    }
}
