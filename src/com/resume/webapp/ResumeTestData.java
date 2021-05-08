package com.resume.webapp;

import com.resume.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

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
        experience.add(new Organization("Java Online Projects", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("Автор проекта.")),
                new ArrayList<String>(Collections.singleton("Создание, организация и проведение Java онлайн проектов и стажировок.")))));
        experience.add(new Organization("Wrike", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("Старший разработчик (backend)")), new ArrayList<String>(Collections.singleton("Проектирование" +
                " и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")))));
        experience.add(new Organization("RIT Center", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("Java архитектор")), new ArrayList<String>(Collections.singleton(
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins)," +
                        " миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы." +
                        " Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html)." +
                        " Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons," +
                        " Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python")))));
        experience.add(new Organization("Luxoft (Deutsche Bank)", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("Ведущий программист")), new ArrayList<String>(Collections.singleton("Участие" +
                " в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM." +
                " Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT," +
                " ExtGWT (GXT), Highstock, Commet, HTML5.")))));
        experience.add(new Organization("Yota", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("Ведущий специалист")),
                new ArrayList<String>(Collections.singleton("Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS," +
                        " Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)")))));
        experience.add(new Organization("Enkata", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("Разработчик ПО")), new ArrayList<String>(Collections.singleton("Реализация клиентской" +
                " (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).")))));
        experience.add(new Organization("Siemens AG", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("Разработчик ПО")), new ArrayList<String>(Collections.singleton("Разработка информационной модели," +
                " проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).")))));
        experience.add(new Organization("Alcatel", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("Инженер по аппаратному и программному тестированию")), new ArrayList<String>(Collections.singleton(
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).")))));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(experience));
        ArrayList<Organization> education = new ArrayList<Organization>();
        education.add(new Organization("Coursera", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("Functional Programming Principles in Scala\" by Martin Odersky")), new ArrayList<String>(Collections.singleton("")))));
        education.add(new Organization("Luxoft", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("\tКурс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"")), new ArrayList<String>(Collections.singleton("")))));
        education.add(new Organization("Siemens AG", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("3 месяца обучения мобильным IN сетям (Берлин)")), new ArrayList<String>(Collections.singleton("")))));
        education.add(new Organization("Alcatel", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("6 месяцев обучения цифровым телефонным сетям (Москва)")), new ArrayList<String>(Collections.singleton("")))));
        ArrayList<LocalDate> list = new ArrayList<>();
        list.add(LocalDate.of(2014, 10, 11));
        list.add(LocalDate.of(2017, 10, 11));
        ArrayList<LocalDate> list2 = new ArrayList<>();
        list2.add(LocalDate.of(2014, 10, 11));
        list2.add(LocalDate.of(2017, 10, 11));
        ArrayList<String> list3 = new ArrayList<>();
        list3.add("Аспирантура (программист С, С++)");
        list3.add("Инженер (программист Fortran, C)");
        ArrayList<String> list4 = new ArrayList<>();
        list4.add("");
        list4.add("");
        education.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и " +
                "оптики", "",new Organization.Position( list, list2, list3, list4)));
        education.add(new Organization("Заочная физико-техническая школа при МФТИ", "",new Organization.Position( new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<LocalDate>(Collections.singleton(LocalDate.of(2014, 10, 11))), new ArrayList<String>(Collections.singleton("Закончил с отличием")), new ArrayList<String>(Collections.singleton("")))));
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
