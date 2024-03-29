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
        if (uuid == null || uuid == "") {
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
            switch (sectionType) {
                case PERSONAL:
                case OBJECTIVE: {
                    String value = request.getParameter(sectionType.name());
                    if (value != null && value.trim().length() != 0) {
                        resume.addSection(sectionType, new TextSection(value));
                    } else {
                        resume.getSections().remove(sectionType);
                    }
                    break;
                }

                case ACHIEVEMENT:
                case QUALIFICATIONS: {
                    String value = request.getParameter(sectionType.name());
                    if (value != null && value.trim().length() != 0) {
                        List<String> list = new ArrayList<>(Arrays.asList(value.split("\n")));
                        resume.addSection(sectionType, new TextListSection(list));
                    } else {
                        resume.getSections().remove(sectionType);
                    }
                    break;
                }
                case EXPERIENCE:
                case EDUCATION: {
                    String[] name = request.getParameterValues(sectionType.name());
                    String[] url = request.getParameterValues(sectionType.name() + "url");
                    String[] count = request.getParameterValues(sectionType.name() + "count");

                    List<Organization> listOrg = new ArrayList<>();

                    for (int j = 0; j < name.length; j++) {
                        if (name[j] != null && name[j].trim().length() != 0) {
                            List<Organization.Position> list = new ArrayList<>();
                            String[] heading = request.getParameterValues(sectionType.name() + count[j] + "heading");
                            String[] value = request.getParameterValues(sectionType.name() + count[j] + "value");
                            String[] initialDate = request.getParameterValues(sectionType.name() + count[j] + "initial_date");
                            String[] endDate = request.getParameterValues(sectionType.name() + count[j] + "end_date");
                            if (heading != null) {
                                for (int i = 0; i < heading.length; i++) {
                                    list.add(new Organization.Position(convertStringToLocalDate(initialDate[i]), convertStringToLocalDate(endDate[i]), heading[i], value[i]));
                                }
                            }
                            listOrg.add(new Organization(name[j], url[j], list));
                        }
                    }
                    resume.addSection(sectionType, new OrganizationSection(listOrg));
                    break;
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
            case "edit":
                if (uuid != null) {
                    resume = storage.get(uuid);
                } else {
                    resume = new Resume("", "");
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
