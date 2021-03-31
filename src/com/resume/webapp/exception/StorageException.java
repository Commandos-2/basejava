package com.resume.webapp.exception;

public class StorageException extends RuntimeException   {
    private final String fullName;

    public StorageException(String message,String fullName) {
        super(message);
        this.fullName = fullName;
    }

    public String getUuid() {
        return fullName;
    }
}
