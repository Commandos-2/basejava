<%@ page import="com.resume.webapp.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="com.resume.webapp.model.ContactsType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <th>Удалить</th>
            <th>Редактировать</th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="com.resume.webapp.model.Resume"/>

        <tr>
            <td><a href="resume?uuid=${resume.getUuid()}&action=view">${resume.getFullName()}</a></td>
            <td><%=ContactsType.EMAIL.toHtml(resume.getContacts(ContactsType.EMAIL))%></td>
            <td ><a href="resume?uuid=${resume.getUuid()}&action=delete"><img src="img/delete.png"></a></td>
            <td><a href="resume?uuid=${resume.getUuid()}&action=edit"><img src="img/pencil.png"></a></td>
        </tr>
        </c:forEach>
    </table>
    <h2>Всего резюме в базе: ${size} </h2>
    <h2><a href="resume?action=save">Создать новое резюме</a></h2>
    <h2><a href="resume?action=clear">Удалить все резюме</a></h2>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
