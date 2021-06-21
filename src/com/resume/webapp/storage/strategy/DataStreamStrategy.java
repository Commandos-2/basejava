package com.resume.webapp.storage.strategy;

import com.resume.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static java.util.Objects.isNull;

public class DataStreamStrategy implements Strategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeWithExeption(resume.getContacts().entrySet(), dos, x -> {
                dos.writeUTF(((Map.Entry<ContactsType, String>) x).getKey().name());
                dos.writeUTF(((Map.Entry<ContactsType, String>) x).getValue());
            });
            writeWithExeption(resume.getSections().entrySet(), dos, k -> {
                SectionType sectionType = ((Map.Entry<SectionType, AbstractSection>) k).getKey();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE: {
                        dos.writeUTF(((TextSection) ((Map.Entry<SectionType, AbstractSection>) k).getValue()).getContent());
                        break;
                    }
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        List<String> list = ((TextListSection) ((Map.Entry<SectionType, AbstractSection>) k).getValue()).getItems();
                        dos.writeInt(list.size());
                        for (String items : list) {
                            dos.writeUTF(items);
                        }
                        break;
                    }
                    case EDUCATION:
                    case EXPERIENCE: {
                        List<Organization> organizationSection = ((OrganizationSection) ((Map.Entry<SectionType, AbstractSection>) k).getValue()).getOrganizations();
                        writeWithExeption(organizationSection, dos, x -> {
                            Link link = ((Organization) x).getHomePage();
                            dos.writeUTF(link.getName());
                            String linkString = link.getUrl();
                            dos.writeUTF(isNull(linkString) ? "" : linkString);
                            List<Organization.Position> list = ((Organization) x).getPositions();
                            writeWithExeption(list, dos, y -> {
                                dos.writeUTF(((Organization.Position) y).getInitialDate().toString());
                                dos.writeUTF(((Organization.Position) y).getEndDate().toString());
                                dos.writeUTF(((Organization.Position) y).getHeading());
                                String text = ((Organization.Position) y).getText();
                                dos.writeUTF(isNull(text) ? "" : text);
                            });
                            /*for (Organization.Position position : list) {
                                dos.writeUTF(position.getInitialDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getHeading());
                                String text = position.getText();
                                dos.writeUTF(isNull(text) ? "" : text);
                            }*/
                        });
                    }
                    break;
                }
            });
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
                    url = !url.equals("") ? url : null;
                    int sizePositions = dis.readInt();
                    List<Organization.Position> listPosition = new ArrayList<>();
                    for (int j = 0; j < sizePositions; j++) {
                        LocalDate initialDate = LocalDate.parse(dis.readUTF());
                        LocalDate endDate = LocalDate.parse(dis.readUTF());
                        String heading = dis.readUTF();
                        String text = dis.readUTF();
                        text = !text.equals("") ? text : null;
                        listPosition.add(new Organization.Position(initialDate, endDate, heading, text));
                    }
                    listOrganization.add(new Organization(nameOrganization, url, listPosition));
                }
                return new OrganizationSection(listOrganization);
            }
        }
        return null;
    }

    private void writeWithExeption(Collection list, DataOutputStream dos, Writer writer) throws IOException {
        dos.writeInt(list.size());
        Objects.requireNonNull(writer);
        for (Object t : list) {
            writer.write(t);
        }
    }
}