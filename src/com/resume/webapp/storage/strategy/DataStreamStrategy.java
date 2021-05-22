package com.resume.webapp.storage.strategy;

import com.resume.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamStrategy implements Strategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            dos.writeInt(resume.getContacts().size());
            for (Map.Entry<ContactsType, String> entry : resume.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            dos.writeInt(resume.getSections().size());
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                SectionType sectionType = entry.getKey();
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE: {
                        dos.writeUTF(sectionType.name());
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    }
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        int sizeTextListSection = ((TextListSection) entry.getValue()).getItems().size();
                        dos.writeUTF(sectionType.name());
                        dos.writeInt(sizeTextListSection);
                        for (int i = 0; i < sizeTextListSection; i++) {
                            dos.writeUTF(((TextListSection) entry.getValue()).getItem(i));
                        }
                        break;
                    }
                    case EDUCATION:
                    case EXPERIENCE: {
                        int sizeOrganizationSection = ((OrganizationSection) entry.getValue()).getOrganizations().size();
                        dos.writeUTF(sectionType.name());
                        dos.writeInt(sizeOrganizationSection);
                        for (Organization organization : ((OrganizationSection) entry.getValue()).getOrganizations()) {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());
                            int sizePositions = organization.getPositions().size();
                            dos.writeInt(sizePositions);
                            for (Organization.Position position : organization.getPositions()) {
                                dos.writeUTF(position.getInitialDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getHeading());
                                dos.writeUTF(position.getText());
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactsType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readAbstractSection(dis, sectionType));
            }
            return resume;
        }
    }

    private AbstractSection readAbstractSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE: {
                return new TextSection(dis.readUTF());
            }
            case ACHIEVEMENT:
            case QUALIFICATIONS: {
                int size = dis.readInt();
                List<String> textListSection = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    textListSection.add(dis.readUTF());
                }
                return new TextListSection(textListSection);
            }
            case EDUCATION:
            case EXPERIENCE: {
                int sizeOrganizationSection = dis.readInt();
                List<Organization> listOrganization = new ArrayList<>();
                for (int i = 0; i < sizeOrganizationSection; i++) {
                    String nameOrganization = dis.readUTF();
                    String url = dis.readUTF();
                    int sizePositions = dis.readInt();
                    List<Organization.Position> listPosition = new ArrayList<>();
                    for (int j = 0; j < sizePositions; j++) {
                        LocalDate initialDate = LocalDate.parse(dis.readUTF());
                        LocalDate endDate = LocalDate.parse(dis.readUTF());
                        String heading = dis.readUTF();
                        String text = dis.readUTF();
                        listPosition.add(new Organization.Position(initialDate, endDate, heading, text));
                    }
                    listOrganization.add(new Organization(nameOrganization, url, listPosition));
                }
                return new OrganizationSection(listOrganization);
            }
        }
        return null;
    }
}