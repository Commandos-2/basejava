package com.resume.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume {
    private final String uuid;
    private final String fullName;
    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
    private final Map<ContactsType, String> contacts = new EnumMap<>(ContactsType.class);

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

    public AbstractSection getSections(SectionType type) {
        return sections.get(type);
    }

    public String getContacts(ContactsType type) {
        return contacts.get(type);
    }

    public void addSection(SectionType type, AbstractSection section) {
        Objects.requireNonNull(type, "Тип секции не должен быть равен null");
        Objects.requireNonNull(section, "Информация заносимая в секцию не должна быть равна null");
        this.sections.put(type, section);
    }

    public void addContact(ContactsType type, String section) {
        Objects.requireNonNull(type, "Тип секции не должен быть равен null");
        Objects.requireNonNull(section, "Информация заносимая в секцию не должна быть равна null");
        this.contacts.put(type, section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(sections, resume.sections) &&
                Objects.equals(contacts, resume.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, sections, contacts);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(uuid).append(" - (").append(fullName).append(")\n\n");
        for (Map.Entry<ContactsType, String> entry : contacts.entrySet()) {
            sb.append(entry.getKey().getTitle()).append(" - ").append(entry.getValue()).append("\n");
        }
        for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
            sb.append("\n\n").append(entry.getKey().getTitle()).append("\n\n").append(entry.getValue().toString());
        }
        return String.valueOf(sb);
    }
}
