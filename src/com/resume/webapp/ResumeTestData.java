package com.resume.webapp;

import com.resume.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ResumeTestData {
    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок" +
                " и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума," +
                " сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        ArrayList<String> achievement = new ArrayList<String>();
        achievement.add("С 2013 года: разработка проектов" +
                " \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX)." +
                " Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\"." +
                " Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы" +
                " управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        resume.addSection(SectionType.ACHIEVEMENT, new TextListSection(achievement));
        ArrayList<String> qualifications = new ArrayList<String>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring " +
                "(MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT)," +
                " Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.add("Родной русский, английский \"upper intermediate\"");
        resume.addSection(SectionType.QUALIFICATIONS, new TextListSection(qualifications));
        ArrayList<Organization> experience = new ArrayList<Organization>();
        experience.add(new Organization("Java Online Projects", "www.yandex.ru", new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        experience.add(new Organization("Yota", "www.yandex.ru", new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS," +
                        " Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)")));
        experience.add(new Organization("Siemens AG", null, new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Разработчик ПО", "Разработка информационной модели," +
                " проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).")));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(experience));
        ArrayList<Organization> education = new ArrayList<Organization>();
        education.add(new Organization("Siemens AG", "www.yandex.ru", new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "3 месяца обучения мобильным IN сетям (Берлин)", null)));
        education.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и " +
                "оптики", null, new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Инженер (программист Fortran, C)", null),
                new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "6 месяцев обучения цифровым телефонным сетям (Москва)", null)));
        education.add(new Organization("Заочная физико-техническая школа при МФТИ", null, new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Закончил с отличием", null)));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(education));
        resume.addContact(ContactsType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactsType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactsType.SKYPE, "grigory.kislin");
        return resume;
    }

    public static void main(String[] args) {
        Resume resume = ResumeTestData.createResume("1", "Григорий Кислин");
        System.out.println(resume.toString());
    }
}
