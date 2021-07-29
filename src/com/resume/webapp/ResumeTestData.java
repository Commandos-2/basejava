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
        achievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С," +
                " Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery." +
                " Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC," +
                " GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base " +
                "архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему " +
                "мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay," +
                " Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        resume.addSection(SectionType.ACHIEVEMENT, new TextListSection(achievement));
        ArrayList<String> qualifications = new ArrayList<String>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring " +
                "(MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT)," +
                " Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.add("Python: Django.");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS," +
                " JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP," +
                " AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher," +
                " Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования," +
                " архитектурных шаблонов, UML, функционального программирования");
        qualifications.add("Родной русский, английский \"upper intermediate\"");
        resume.addSection(SectionType.QUALIFICATIONS, new TextListSection(qualifications));

        ArrayList<Organization> experience = new ArrayList<Organization>();
        experience.add(new Organization("Java Online Projects", "www.yandex.ru", new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        experience.add(new Organization("Wrike", "www.yandex.ru", new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Старший разработчик (backend)", "Проектирование" +
                " и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")));
        experience.add(new Organization("RIT Center", "www.yandex.ru", new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins)," +
                        " миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы." +
                        " Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html)." +
                        " Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons," +
                        " Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python")));
        experience.add(new Organization("Luxoft (Deutsche Bank)", "www.yandex.ru", new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Ведущий программист", "Участие" +
                " в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM." +
                " Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT," +
                " ExtGWT (GXT), Highstock, Commet, HTML5.")));
        experience.add(new Organization("Yota", "www.yandex.ru", new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS," +
                        " Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)")));
        experience.add(new Organization("Enkata", null, new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Разработчик ПО", "Реализация клиентской" +
                " (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).")));
        experience.add(new Organization("Siemens AG", null, new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Разработчик ПО", "Разработка информационной модели," +
                " проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).")));
        experience.add(new Organization("Alcatel", null, new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).")));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(experience));
        ArrayList<Organization> education = new ArrayList<Organization>();
        education.add(new Organization("Coursera", "www.yandex.ru", new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Functional Programming Principles in Scala\" by Martin Odersky", null)));
        education.add(new Organization("Luxoft", null, new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "\tКурс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null)));
        education.add(new Organization("Siemens AG", "www.yandex.ru", new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "3 месяца обучения мобильным IN сетям (Берлин)", null)));
        education.add(new Organization("Alcatel", null, new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Аспирантура (программист С, С++)", null)));
        education.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и " +
                "оптики", null, new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "Инженер (программист Fortran, C)", null),
                new Organization.Position(LocalDate.of(2014, 10, 11), LocalDate.of(2014, 10, 11), "6 месяцев обучения цифровым телефонным сетям (Москва)", null)));
        education.add(new Organization("Заочная физико-техническая школа при МФТИ", null, new Organization.Position(LocalDate.of(2014, 10, 11),LocalDate.of(2014, 10, 11), "Закончил с отличием", null)));
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
