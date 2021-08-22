package com.resume.webapp.web;

import com.resume.webapp.Config;
import com.resume.webapp.model.*;
import com.resume.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.resume.webapp.util.DataAdapter.convertStringToLocalDate;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    public void init(ServletConfig servletConfig) {
        storage = Config.get().getStorage();
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        if (fullName == null && uuid != null) {
            storage.delete(uuid);
        }
        Resume resume;
        if (uuid == null || uuid.equals("")) {
            resume = new Resume(fullName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        }
        for (ContactsType contactsType : ContactsType.values()) {
            String value = request.getParameter(contactsType.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContact(contactsType, value);
            } else {
                resume.getContacts().remove(contactsType);
            }
        }
        for (SectionType sectionType : SectionType.values()) {
            String value = request.getParameter(sectionType.name());
            String[] values = request.getParameterValues(sectionType.name());
            if ((value == null || value.trim().length() == 0) && values.length < 2) {
                resume.getSections().remove(sectionType);
            } else {
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE: {
                        resume.addSection(sectionType, new TextSection(value));
                        break;
                    }
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        resume.addSection(sectionType, new TextListSection(value.split("\n")));
                        break;
                    }
                    case EXPERIENCE:
                    case EDUCATION: {
                        // String[] name = request.getParameterValues(sectionType.name());
                        String[] urls = request.getParameterValues(sectionType.name() + "url");
                        // String[] count = request.getParameterValues(sectionType.name() + "count");
                        List<Organization> listOrg = new ArrayList<>();
                        for (int j = 0; j < values.length; j++) {
                            if (values[j] != null && values[j].trim().length() != 0) {
                                List<Organization.Position> list = new ArrayList<>();
                                String pfx = sectionType.name() + j;
                                String[] heading = request.getParameterValues(pfx + "heading");
                                String[] descriptions = request.getParameterValues(pfx + "value");
                                String[] initialDate = request.getParameterValues(pfx + "initial_date");
                                String[] endDate = request.getParameterValues(pfx + "end_date");
                                    for (int i = 0; i < heading.length; i++) {
                                        if (heading[i] != null&& heading[i].trim().length() != 0) {
                                        list.add(new Organization.Position(convertStringToLocalDate(initialDate[i]), convertStringToLocalDate(endDate[i]), heading[i], descriptions[i]));
                                    }
                                }
                                listOrg.add(new Organization(values[j], urls[j], list));
                            }
                        }
                        resume.addSection(sectionType, new OrganizationSection(listOrg));
                        break;
                    }
                }
            }
        }
        if (uuid == null || uuid == "") {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
        return;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.setAttribute("size", storage.size());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "clear":
                storage.clear();
                response.sendRedirect("resume");
                return;
            case "view":
                resume = storage.get(uuid);
                break;
            case "edit":
                if (uuid != null) {
                    resume = storage.get(uuid);
                } else {
                    resume = new Resume("", "");
                }
                for (SectionType type : SectionType.values()) {
                    AbstractSection section = resume.getSections(type);
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (section == null) {
                                section = TextSection.EMPTY;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = TextListSection.EMPTY;
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            OrganizationSection orgSection = (OrganizationSection) section;
                            List<Organization> emptyFirstOrganizations = new ArrayList<>();
                            emptyFirstOrganizations.add(Organization.EMPTY);
                            if (orgSection != null) {
                                for (Organization org : orgSection.getOrganizations()) {
                                    List<Organization.Position> emptyFirstPositions = new ArrayList<>();
                                    emptyFirstPositions.add(Organization.Position.EMPTY);
                                    emptyFirstPositions.addAll(org.getPositions());
                                    emptyFirstOrganizations.add(new Organization(org.getHomePage(), emptyFirstPositions));
                                }
                            }
                            section = new OrganizationSection(emptyFirstOrganizations);
                            break;
                    }
                    resume.addSection(type, section);
                }
                break;
            default:
                throw new IllegalStateException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(("view".
                equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")).
                forward(request, response);
    }
}
