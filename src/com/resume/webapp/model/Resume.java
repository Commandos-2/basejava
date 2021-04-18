package com.resume.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume {
    private final String uuid;
    private final String fullName;
    private final Map<TypeSection, AbstractSection> section = new EnumMap<>(TypeSection.class);
    private final Map<TypeContacts, String> contacts = new EnumMap<>(TypeContacts.class);

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

    public void addSection(TypeSection type,AbstractSection section)
    {
        Objects.requireNonNull(type, "Тип секции не должен быть равен null");
        Objects.requireNonNull(section, "Информация заносимая в секцию не должна быть равна null");
        this.section.put(type,section);
    }
    public void addContacts(TypeContacts type,String section)
    {
        Objects.requireNonNull(type, "Тип секции не должен быть равен null");
        Objects.requireNonNull(section, "Информация заносимая в секцию не должна быть равна null");
        this.contacts.put(type,section);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) &&
                fullName.equals(resume.fullName)&&
                section.equals(resume.section)&&
                contacts.equals(resume.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName,section,contacts);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(uuid + " - (" + fullName + ")\n\n");
        for (Map.Entry<TypeContacts, String> entry : contacts.entrySet()) {
            sb.append(entry.getKey().getTitle() + " - ");
            sb.append(entry.getValue() + "\n");
        }
        for (Map.Entry<TypeSection, AbstractSection> entry : section.entrySet()) {
            sb.append("\n\n"+entry.getKey().getTitle()+"\n\n");
            sb.append(entry.getValue().getStringInformation());
        }
        return String.valueOf(sb);
    }

    //  @Override
    //  public int compareTo(Resume o) {
    //     return uuid.compareTo(o.uuid);
    //}
}
