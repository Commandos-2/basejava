package com.resume.webapp.storage.strategy;

import com.resume.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

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
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE: {
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    }
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        List<String> list = ((TextListSection) entry.getValue()).getItems();
                        dos.writeInt(list.size());
                        for (String items : list) {
                            dos.writeUTF(items);
                        }
                        break;
                    }
                    case EDUCATION:
                    case EXPERIENCE: {
                        List<Organization> organizationSection = ((OrganizationSection) entry.getValue()).getOrganizations();
                        dos.writeInt(organizationSection.size());
                        for (Organization organization : organizationSection) {
                            Link link = organization.getHomePage();
                            dos.writeUTF(link.getName());
                            String linkString = link.getUrl();
                            dos.writeUTF(isNull(linkString) ? "" : linkString);
                            List<Organization.Position> list = organization.getPositions();
                            dos.writeInt(list.size());
                            writeWithExeption(list, dos, (x) -> {
                                try {
                                    dos.writeUTF(x.getInitialDate().toString());
                                    dos.writeUTF(x.getEndDate().toString());
                                    dos.writeUTF(x.getHeading());
                                    String text = x.getText();
                                    dos.writeUTF(isNull(text) ? "" : text);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            /*for (Organization.Position position : list) {
                                dos.writeUTF(position.getInitialDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getHeading());
                                String text = position.getText();
                                dos.writeUTF(isNull(text) ? "" : text);
                            }*/
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

    private void writeWithExeption(List<Organization.Position> list, DataOutputStream dos, Consumer<Organization.Position> action) throws IOException {
        Objects.requireNonNull(action);
        for (Organization.Position t : list) {
            action.accept(t);
        }
    }
}