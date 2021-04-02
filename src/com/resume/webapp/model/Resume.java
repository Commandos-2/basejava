package com.resume.webapp.model;

import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {
    private final String uuid;
    private final String fullName;

    public Resume(String fullName) {
        this.uuid=UUID.randomUUID().toString();
        this.fullName = fullName;
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    @Override
    public String toString() {
        return fullName;
    }

  //  @Override
  //  public int compareTo(Resume o) {
   //     return uuid.compareTo(o.uuid);
    //}
}
