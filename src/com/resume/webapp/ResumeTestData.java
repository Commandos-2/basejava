package com.resume.webapp;

import com.resume.webapp.model.*;

import java.util.ArrayList;
import java.util.Date;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");
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
        ArrayList<Organization.Experiense> experience = new ArrayList<Organization.Experiense>();
        experience.add(new Organization.Experiense("Java Online Projects", new Date(1996-1900,05,4), new Date(2010-1900,07,21), "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experience.add(new Organization.Experiense("Wrike", new Date(102014), new Date(2010-1900,07,21), "Старший разработчик (backend)", "Проектирование" +
                " и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        experience.add(new Organization.Experiense("RIT Center", new Date(1996-1900,05,4), new Date(2010-1900,07,21), "Java архитектор", "\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins)," +
                " миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы." +
                " Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html)." +
                " Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons," +
                " Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"));
        experience.add(new Organization.Experiense("Luxoft (Deutsche Bank)", new Date(1996-1900,05,4), new Date(2010-1900,07,21), "Ведущий программист", "Участие" +
                " в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM." +
                " Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT," +
                " ExtGWT (GXT), Highstock, Commet, HTML5."));
        experience.add(new Organization.Experiense("Yota", new Date(1996-1900,05,4), new Date(10-2013), "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS," +
                " Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));
        experience.add(new Organization.Experiense("Enkata", new Date(1996-1900,05,4), new Date(2010-1900,07,21), "Разработчик ПО", "Реализация клиентской" +
                " (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."));
        experience.add(new Organization.Experiense("Siemens AG", new Date(1996-1900,05,4), new Date(2010-1900,07,21), "Разработчик ПО", "Разработка информационной модели," +
                " проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        experience.add(new Organization.Experiense("Alcatel", new Date(1996-1900,05,4), new Date(2010-1900,07,21), "Инженер по аппаратному и программному тестированию", "\tИнженер по" +
                " аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        resume.addSection(SectionType.EXPERIENCE, new Organization(experience));
        ArrayList<Organization.Experiense> education = new ArrayList<Organization.Experiense>();
        education.add(new Organization.Experiense("Coursera", new Date(1996-1900,05,4), new Date(2010-1900,07,21), "Functional Programming Principles in Scala\" by Martin Odersky", ""));
        education.add(new Organization.Experiense("Luxoft", new Date(1996-1900,05,4), new Date(2010-1900,07,21), "\tКурс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", ""));
        education.add(new Organization.Experiense("Siemens AG", new Date(1996-1900,05,4), new Date(2010-1900,07,21), "3 месяца обучения мобильным IN сетям (Берлин)", ""));
        education.add(new Organization.Experiense("Alcatel", new Date(1996-1900,05,4), new Date(2010-1900,07,21), "6 месяцев обучения цифровым телефонным сетям (Москва)", ""));
        education.add(new Organization.Experiense("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и " +
                "оптики", new Date(1996-1900,05,4), new Date(2010-1900,07,21), "Аспирантура (программист С, С++)", ""));
        education.add(new Organization.Experiense("Заочная физико-техническая школа при МФТИ", new Date(1996-1900,05,4), new Date(2010-1900,07,21), "Закончил с отличием", ""));
        resume.addSection(SectionType.EDUCATION, new Organization(education));
        resume.addContact(ContactsType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactsType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactsType.SKYPE, "grigory.kislin");
        System.out.println(resume.toString());
    }
}
