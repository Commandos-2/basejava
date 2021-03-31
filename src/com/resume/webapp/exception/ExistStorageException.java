package com.resume.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String fullName) {
        super("Резюме "+fullName+" уже существует",fullName);
    }
}
