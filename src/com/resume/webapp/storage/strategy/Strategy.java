package com.resume.webapp.storage.strategy;

import com.resume.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Strategy {
   abstract void doWrite(Resume resume, OutputStream os) throws IOException;
   abstract  Resume doRead(InputStream is) throws IOException ;

}
