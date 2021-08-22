<%@ page import="com.resume.webapp.model.ContactsType" %>
<%@ page import="com.resume.webapp.model.SectionType" %>
<%@ page import="java.util.UUID" %>
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
        <h2>Контакты</h2>
        <c:forEach items="<%=ContactsType.values()%>" var="type">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size="30" value="${resume.getContacts(type)}"></dd>
            </dl>
        </c:forEach>
        <c:forEach items="<%=SectionType.values()%>" var="typeSections">
            <dl>
                <dt><h3>${typeSections.title}</h3></dt>
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
                    <c:when test="${typeSections.name() == SectionType.EXPERIENCE||typeSections.name() == SectionType.EDUCATION }">
                        <c:forEach items="${resume.getSections(typeSections).getOrganizations()}" var="organization"
                                   varStatus="counter">
                            <jsp:useBean id="organization"
                                         type="com.resume.webapp.model.Organization"/>
                            <dd>Название:<br/> <input type="text" name="${typeSections.name()}" size="100"
                                                      value="${organization.homePage.name}"></dd>
                            <br/><br/>
                            <dd>Сайт организации:<br/> <input type="text" name="${typeSections.name()}url" size="100"
                                                              value="${organization.homePage.url}"></dd>
                            <br/><br/>
                            <c:forEach items="${organization.positions}" var="position" varStatus="id">
                                <dd>Должность:<br/><input type="text"
                                                          name="${typeSections.name()}${counter.index}heading"
                                                          size="100"
                                                          value="${position.heading}"></dd>
                                <br/><br/>
                                <dd>Дата начала:<br/><input type="date"
                                                            name="${typeSections.name()}${counter.index}initial_date"
                                                            size="100"
                                                            value="${position.initialDate}"></dd>
                                <br/><br/>
                                <dd>Дата окончания:<br/><input type="date"
                                                               name="${typeSections.name()}${counter.index}end_date"
                                                               size="100"
                                                               value="${position.endDate}"></dd>
                                <br/><br/>
                                <dd>
                                    <div>Описание:</div>
                                    <textarea name="${typeSections.name()}${counter.index}value" cols="100"
                                              rows="10">${position.text}</textarea></dd>
                                <br/><br/>

                            </c:forEach>
                        </c:forEach>
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
