package com.resume.webapp;

import com.resume.webapp.storage.SqlStorage;
import com.resume.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    protected final static String PROPS = "/resumes.properties";
    private static final Config INSTANSE = new Config();
    private Properties props = new Properties();
    private File StorageDir;
    //private SqlStorage sqlStorage;
    private Storage storage;

    public static Config get() {
        return INSTANSE;
    }

    private Config() {
        try (InputStream is = Config.class.getResourceAsStream(PROPS)) {
            props.load(is);
            storage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
            StorageDir = new File(props.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file" + PROPS);
        }
    }

    public File getStorageDir() {
        return StorageDir;
    }

    public Storage getStorage() {
        return storage;
    }
    }
