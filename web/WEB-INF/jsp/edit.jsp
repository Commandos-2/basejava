<%@ page import="com.resume.webapp.model.ContactsType" %>
<%@ page import="com.resume.webapp.model.SectionType" %>
<%@ page import="com.resume.webapp.model.TextListSection" %>
<%@ page import="com.resume.webapp.model.TextSection" %>
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
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input required type="text" name="fullName" size="50" value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты</h3>
        <c:forEach items="<%=ContactsType.values()%>" var="type">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size="30" value="${resume.getContacts(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции</h3>
        <c:forEach items="<%=SectionType.values()%>" var="typeSections">
            <dl>
                <dt>${typeSections.title}</dt>
                <br/>
                <c:choose>
                    <c:when test="${typeSections.name() == SectionType.PERSONAL||typeSections.name()== SectionType.OBJECTIVE }">
                        <dd><input type="text" name="${typeSections.name()}" size="100"
                                   value="${resume.getSections(typeSections).toHtml()}"></dd>
                    </c:when>
                    <c:when test="${typeSections.name() == SectionType.ACHIEVEMENT||typeSections.name() == SectionType.QUALIFICATIONS }">
                        <dd><textarea name="${typeSections.name()}" cols="100"
                                      rows="10">${resume.getSections(typeSections).toHtml()}</textarea></dd>
                        <br/>
                    </c:when>
                </c:choose>
            </dl>
        </c:forEach>
        <hr/>
        <button type="submit">Сохранить</button>
        <button type="reset">Отменить изменения</button>
        <button type="reset" onclick="window.history.back()">Назад</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
