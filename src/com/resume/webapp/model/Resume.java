package com.resume.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume {
    private final String uuid;
    private final String fullName;
    private final Map<SectionType, AbstractSection> section = new LinkedHashMap<>();
    private final Map<ContactsType, String> contacts = new LinkedHashMap<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid не должен быть равен null");
        Objects.requireNonNull(fullName, "fullName    не должен быть равен null");
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
        return uuid.equals(resume.uuid) &&
                fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(uuid + " - (" + fullName + ")/n/n");
        for (Map.Entry<ContactsType, String> entry : contacts.entrySet()) {
            sb.append(entry.getValue() + "/n");
        }
        for (Map.Entry<SectionType, AbstractSection> entry : section.entrySet()) {
            sb.append(entry.getValue().getStringInformation(entry.getValue()));
        }
        return String.valueOf(sb);
    }

    //  @Override
    //  public int compareTo(Resume o) {
    //     return uuid.compareTo(o.uuid);
    //}
}
