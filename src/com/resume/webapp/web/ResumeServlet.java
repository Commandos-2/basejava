package com.resume.webapp.web;

import com.resume.webapp.Config;
import com.resume.webapp.model.Resume;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String uuid = request.getParameter("uuid");
        if (uuid == null) {
            List<Resume> list=Config.get().getSqlStorage().getAllSorted();
            StringBuilder sb = new StringBuilder();
            sb.append("<table style=\"width:100%\">\n");
            for(Resume resume:list){
                sb.append( "  <tr>\n" +"    <th>" + resume.getUuid() + "</th>\n" +"    <th>" + resume.getFullName() + "</th>\n" +"  </tr>\n");
            }
            sb.append("</table>");
            response.getWriter().write(sb.toString());
        } else {
            Resume resume = Config.get().getSqlStorage().get(uuid);
            response.getWriter().write("<table style=\"width:100%\">\n" +
                    "  <tr>\n" +
                    "    <th>" + resume.getUuid() + "</th>\n" +
                    "    <th>" + resume.getFullName() + "</th>\n" +
                    "  </tr>\n" +
                    "</table>");
        }
    }
}
