package com.resume.webapp.storage.strategy;

import java.io.IOException;

public interface Reader<T> {
    T read() throws IOException;
}
