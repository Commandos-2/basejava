package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());


    public final void update(Resume resume) {
        LOG.info("Update " + resume);
        updateResume(resume, getKeyIfExist(resume.getUuid()));
    }

    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        saveResume(resume, getKeyIfNotExist(resume.getUuid()));
    }

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return getResume(getKeyIfExist(uuid));
    }

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        deleteResume(getKeyIfExist(uuid));
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> sortedList = getAll();
        sortedList.sort(RESUME_COMPARATOR);
        return sortedList;
    }

    protected abstract void saveResume(Resume resume, SK key);

    protected abstract void updateResume(Resume resume, SK key);

    protected abstract Resume getResume(SK key);

    protected abstract void deleteResume(SK key);

    protected abstract SK findKey(String uuid);

    private SK getKeyIfExist(String uuid) {
        SK key = findKey(uuid);
        if (!isExist(key)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private SK getKeyIfNotExist(String uuid) {
        SK key = findKey(uuid);
        if (isExist(key)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract boolean isExist(SK key);

    protected abstract List<Resume> getAll();
}

