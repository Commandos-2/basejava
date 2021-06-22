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
            writeWithException(resume.getContacts().entrySet(), dos, x -> {
                dos.writeUTF(x.getKey().name());
                dos.writeUTF(x.getValue());
            });
            writeWithException(resume.getSections().entrySet(), dos, k -> {
                SectionType sectionType = k.getKey();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE: {
                        dos.writeUTF(((TextSection) k.getValue()).getContent());
                        break;
                    }
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        List<String> list = ((TextListSection) k.getValue()).getItems();
                        dos.writeInt(list.size());
                        for (String items : list) {
                            dos.writeUTF(items);
                        }
                        break;
                    }
                    case EDUCATION:
                    case EXPERIENCE: {
                        List<Organization> organizationSection = ((OrganizationSection) k.getValue()).getOrganizations();
                        writeWithException(organizationSection, dos, x -> {
                            Link link = x.getHomePage();
                            dos.writeUTF(link.getName());
                            String linkString = link.getUrl();
                            dos.writeUTF(isNull(linkString) ? "" : linkString);
                            List<Organization.Position> list = x.getPositions();
                            writeWithException(list, dos, y -> {
                                dos.writeUTF(y.getInitialDate().toString());
                                dos.writeUTF(y.getEndDate().toString());
                                dos.writeUTF(y.getHeading());
                                String text = y.getText();
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

                    // List<Organization.Position> listPosition = new ArrayList<>();

                    listOrganization.add(new Organization(nameOrganization, url, readWithException(new ArrayList<>(), dis, () -> {
                        LocalDate initialDate = LocalDate.parse(dis.readUTF());
                        LocalDate endDate = LocalDate.parse(dis.readUTF());
                        String heading = dis.readUTF();
                        String text = dis.readUTF();
                        text = !text.equals("") ? text : null;
                        return new Organization.Position(initialDate, endDate, heading, text);
                        //listPosition.add(new Organization.Position(initialDate, endDate, heading, text));
                    })));
                }
                return new OrganizationSection(listOrganization);
            }
        }
        return null;
    }

    private <T> void writeWithException(Collection<T> list, DataOutputStream dos, Writer<T> writer) throws IOException {
        dos.writeInt(list.size());
        Objects.requireNonNull(writer);
        for (T t : list) {
            writer.write(t);
        }
    }

    private <T> Collection<T> readWithException(Collection<T> list, DataInputStream dis, Reader<T> reader) throws IOException {
        int size = dis.readInt();
        Objects.requireNonNull(reader);
        for (int j = 0; j < size; j++) {
            list.add(reader.read());
        }
        return list;
    }
}