package com.resume.webapp.storage.strategy;

import java.io.IOException;

public interface Writer<T> {
    void write(T t) throws IOException;
}
