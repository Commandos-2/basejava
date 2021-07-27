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
import java.util.List;

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
        Resume resume;
        if (uuid == null) {
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
                    String value = request.getParameter("TextSection");
                    if (value != null && value.trim().length() != 0) {
                        resume.addSection(sectionType, new TextSection(value));
                    } else {
                        resume.getSections().remove(sectionType);
                    }
                    break;
                }

                case ACHIEVEMENT:
                case QUALIFICATIONS: {
                    String[] value = request.getParameterValues("TextListSection");
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < value.length; i++) {
                        if (value[i] != null && value[i].trim().length() != 0) {
                            list.add(value[i]);
                        }
                    }
                    resume.addSection(sectionType, new TextListSection(list));
                    break;
                }

            }
        }
        if (uuid == null) {
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
            case "save":
                request.getRequestDispatcher("/WEB-INF/jsp/save.jsp").forward(request, response);
                return;
            case "view":
            case "edit":
                resume = storage.get(uuid);
                break;
            default:
                throw new IllegalStateException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")).forward(request, response);
    }
}
