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
                if (sectionType == SectionType.PERSONAL || sectionType == SectionType.OBJECTIVE) {
                    dos.writeUTF(sectionType.name());
                    dos.writeUTF(entry.getValue().toString());
                }
                if (sectionType == SectionType.ACHIEVEMENT || sectionType == SectionType.QUALIFICATIONS) {
                    int sizeTextListSection = ((TextListSection) entry.getValue()).getItems().size();
                    dos.writeUTF(sectionType.name());
                    dos.writeInt(sizeTextListSection);
                    for (int i = 0; i < sizeTextListSection; i++) {
                        dos.writeUTF(((TextListSection) entry.getValue()).getItem(i));
                    }
                }
                if (sectionType == SectionType.EDUCATION || sectionType == SectionType.EXPERIENCE) {
                    int sizeOrganizationSection = ((OrganizationSection) entry.getValue()).getOrganizations().size();
                    dos.writeUTF(sectionType.name());
                    dos.writeInt(sizeOrganizationSection);
                    for (int i = 0; i < sizeOrganizationSection; i++) {
                        dos.writeUTF(((OrganizationSection) entry.getValue()).getOrganization(i).getHomePage().getName());
                        dos.writeUTF(((OrganizationSection) entry.getValue()).getOrganization(i).getHomePage().getUrl());
                        int sizePositions = ((OrganizationSection) entry.getValue()).getOrganization(i).getPositions().size();
                        dos.writeInt(sizePositions);
                        for (int j = 0; j < sizePositions; j++) {
                            int sizeInitialDate = ((OrganizationSection) entry.getValue()).getOrganization(i).getPosition(j).getInitialDate().size();
                            dos.writeInt(sizeInitialDate);
                            for (int k = 0; k < sizeInitialDate; k++) {
                                dos.writeUTF(((OrganizationSection) entry.getValue()).getOrganization(i).getPosition(j).getInitialDate(k));
                                dos.writeUTF(((OrganizationSection) entry.getValue()).getOrganization(i).getPosition(j).getEndDate(k));
                                dos.writeUTF(((OrganizationSection) entry.getValue()).getOrganization(i).getPosition(j).getHeading(k));
                                dos.writeUTF(((OrganizationSection) entry.getValue()).getOrganization(i).getPosition(j).getText(k));
                            }
                        }
                    }
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
        if (sectionType == SectionType.PERSONAL || sectionType == SectionType.OBJECTIVE) {
            return new TextSection(dis.readUTF());
        }
        if (sectionType == SectionType.ACHIEVEMENT || sectionType == SectionType.QUALIFICATIONS) {
            int size = dis.readInt();
            List<String> textListSection = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                textListSection.add(dis.readUTF());
            }
            return new TextListSection(textListSection);
        }
        if (sectionType == SectionType.EDUCATION || sectionType == SectionType.EXPERIENCE) {
            int sizeOrganizationSection = dis.readInt();
            List<Organization> listOrganization=new ArrayList<>();
            for (int i = 0; i < sizeOrganizationSection; i++) {
                String nameOrganization = dis.readUTF();
                String url = dis.readUTF();
                int sizePositions = dis.readInt();
                List<Organization.Position> listPosition = new ArrayList<>();
                for (int j = 0; j < sizePositions; j++) {
                    int sizeInitialDate = dis.readInt();
                    ArrayList<LocalDate> initialDate = new ArrayList<>();
                    ArrayList<LocalDate> endDate = new ArrayList<>();
                    ArrayList<String> heading = new ArrayList<>();
                    ArrayList<String> text = new ArrayList<>();
                    for (int k = 0; k < sizeInitialDate; k++) {
                        initialDate.add(LocalDate.parse(dis.readUTF()));
                        endDate.add(LocalDate.parse(dis.readUTF()));
                        heading.add(dis.readUTF());
                        text.add(dis.readUTF());
                    }
                    listPosition.add(new Organization.Position(initialDate, endDate, heading, text));
                }
                listOrganization.add(new Organization(nameOrganization, url, listPosition));
            }
            return new OrganizationSection(listOrganization);
        }
        return null;
    }
}