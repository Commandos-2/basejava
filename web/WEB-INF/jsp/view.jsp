<%@ page import="com.resume.webapp.model.SectionType" %>
<%@ page import="com.resume.webapp.model.TextSection" %>
<%@ page import="java.util.List" %>
<%@ page import="com.resume.webapp.model.TextListSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.resume.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a>
        <p>
            <c:forEach var="contactEntry" items="${resume.contacts}">
                <jsp:useBean id="contactEntry"
                             type="java.util.Map.Entry<com.resume.webapp.model.ContactsType,java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
            </c:forEach>
        </p>
        <p>
        <table border="1" cellpadding="8" cellspacing="0">
            <c:forEach var="sectionEntry" items="${resume.sections}">
                <jsp:useBean id="sectionEntry"
                             type="java.util.Map.Entry<com.resume.webapp.model.SectionType,com.resume.webapp.model.AbstractSection>"/>
                <% SectionType sectionType = sectionEntry.getKey();%>
              <tr><td> <%=sectionType.getTitle()%></td>
                  <td> <% switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE: %>
                <%=((TextSection) sectionEntry.getValue()).getContent()%>
                <% break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        for (String text : ((TextListSection) sectionEntry.getValue()).getItems()) { %>
                <%=text%>
                <% }}
                %>
                  </td></tr>
            </c:forEach></table>
        </p>
    </h2>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
