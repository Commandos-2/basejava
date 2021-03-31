package com.resume.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String fullName) {
        super("Резюме "+fullName+" не существует",fullName);
    }
}
