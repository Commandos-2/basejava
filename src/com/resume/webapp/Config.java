package com.resume.webapp;

import com.resume.webapp.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    protected final static File PROPS = new File("c:\\projects\\topjava\\basejava\\config\\resumes.properties");
    private static final Config INSTANSE = new Config();
    private Properties props = new Properties();
    private File StorageDir;
    private SqlStorage sqlStorage;

    public static Config get() {
        return INSTANSE;
    }

    private Config(){
        try (InputStream is = new FileInputStream(PROPS)) {
            props.load(is);
            sqlStorage=new SqlStorage(props.getProperty("db.url"),props.getProperty("db.user"),props.getProperty("db.password"));
            StorageDir = new File(props.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file" + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return StorageDir;
    }

    public SqlStorage getSqlStorage() {
        return sqlStorage;
    }
}
