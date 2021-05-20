package com.resume.webapp.storage.strategy;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

import java.io.*;

public class ObjectStreamStrategy implements Strategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        } catch (IOException e) {
            throw new StorageException("Error writing to a file", null, e);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("File reading error", null, e);
        }
    }
}