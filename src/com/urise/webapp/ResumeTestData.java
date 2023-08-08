package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.Date;

public class ResumeTestData {
    public static void fillLoremIpsumResume(Resume resume) {
        resume.setContacts(ContactType.TELEPHONE, "+7(921) 855-0482");
        resume.setContacts(ContactType.SKYPE, "skype:grigory.kislin");
        resume.setContacts(ContactType.EMAIl, "gkislin@yandex.ru");
        resume.setContacts(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContacts(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContacts(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.setContacts(ContactType.HOMEPAGE, "http://gkislin.ru/");

        // позиция
        Section<String> sectionPosition = new TextSection("Ведущий стажировок и ...");
        resume.setSection(SectionType.OBJECTIVE, sectionPosition);

        // Личные качества
        Section<String> sectionPersonal = new TextSection("Аналитический склад ума, сильная ...");
        resume.setSection(SectionType.PERSONAL, sectionPersonal);

        // Достижения
        Section<String> sectionAchievement = new ListSection();

        sectionAchievement.addContent("Организация команды и успешная реализация Java проектов ...");
        sectionAchievement.addContent("С 2013 года: разработка проектов ...");
        sectionAchievement.addContent("Реализация двухфакторной аутентификации ...");
        sectionAchievement.addContent("Налаживание процесса разработки ...");

        resume.setSection(SectionType.ACHIEVEMENT, sectionAchievement);

        // Квалификация
        Section<String> sectionQualified = new ListSection();

        sectionQualified.addContent("JEE AS: GlassFish ...");
        sectionQualified.addContent("Version control ...");
        sectionQualified.addContent("DB: PostgreSQL ...");
        sectionQualified.addContent("Languages ...");

        resume.setSection(SectionType.QUALIFICATIONS, sectionQualified);

        // Опыт работы
        Section<Organization> sectionExp = new OrganizationSection();

        // Java Ops Project
        Organization jopOrg = new Organization("Java Ops Projects", "http://javaops.ru/");

        Period authorPeriod = new Period(
                LocalDate.parse("2013-10-01")
                , "Автор проекта."
                , "Создание, организация и проведение Java онлайн проектов и стажировок."
        );

        jopOrg.addPeriod(authorPeriod);

        sectionExp.addContent(jopOrg);

        // Wrike
        Organization wrikeOrg = new Organization("Wrike", "https://www.wrike.com/");

        Period seniorDeveloperPeriod = new Period(
                LocalDate.parse("2014-10-01")
                , LocalDate.parse("2016-01-01")
                , "Старший разработчик (backend)"
                , "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
        );

        wrikeOrg.addPeriod(seniorDeveloperPeriod);

        sectionExp.addContent(wrikeOrg);


        // RIT Center
        Organization ritCenter = new Organization("RIT Center");

        Period architect = new Period(
                LocalDate.parse("2012-04-01")
                , LocalDate.parse("2014-10-01")
                , "Java архитектор"
                , "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
        );

        ritCenter.addPeriod(architect);

        sectionExp.addContent(ritCenter);

        resume.setSection(SectionType.EXPERIENCE, sectionExp);


        // Образование
        Section<Organization> sectionEdu = new OrganizationSection();

        // Coursera
        Organization coursera = new Organization("Coursera", "https://www.coursera.org/course/progfun");

        Period courseraPeriod = new Period(
                LocalDate.parse("2013-03-01")
                , LocalDate.parse("2013-05-01")
                , "Functional Programming Principles"
        );

        coursera.addPeriod(courseraPeriod);

        sectionEdu.addContent(coursera);


        // Alcatel
        Organization alcatel = new Organization("Alcatel", "http://www.alcatel.ru/");

        Period studyTelephone = new Period(
                LocalDate.parse("1997-09-01")
                , LocalDate.parse("1998-03-01")
                , "6 месяцев обучения цифровым телефонным сетям (Москва)"
        );

        alcatel.addPeriod(studyTelephone);

        sectionEdu.addContent(alcatel);

        // SPB
        Organization itmo = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/");

        Period graduate = new Period(
                LocalDate.parse("1993-09-01")
                , LocalDate.parse("1996-07-01")
                , "Аспирантура"
                , "(программист С, С++)"
        );

        Period engineer = new Period(
                LocalDate.parse("1987-09-01")
                , LocalDate.parse("1993-07-01")
                , "Инженер"
                , "(программист Fortran, C)"
        );

        itmo.addPeriod(graduate);
        itmo.addPeriod(engineer);

        sectionEdu.addContent(itmo);

        resume.setSection(SectionType.EDUCATION, sectionEdu);
    }
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        System.out.println(resume.getFullName());

        // Контакты
        resume.setContacts(ContactType.TELEPHONE, "+7(921) 855-0482");
        resume.setContacts(ContactType.SKYPE, "skype:grigory.kislin");
        resume.setContacts(ContactType.EMAIl, "gkislin@yandex.ru");
        resume.setContacts(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContacts(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContacts(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.setContacts(ContactType.HOMEPAGE, "http://gkislin.ru/");

        for (ContactType contact : ContactType.values()) {
            System.out.println(contact.getName() + " " + resume.getContact(contact));
        }

        System.out.println();
        System.out.println();

        // позиция
        Section<String> sectionPosition = new TextSection("Ведущий стажировок и ...");
        resume.setSection(SectionType.OBJECTIVE, sectionPosition);

        // Личные качества
        Section<String> sectionPersonal = new TextSection("Аналитический склад ума, сильная ...");
        resume.setSection(SectionType.PERSONAL, sectionPersonal);

        // Достижения
        Section<String> sectionAchievement = new ListSection();

        sectionAchievement.addContent("Организация команды и успешная реализация Java проектов ...");
        sectionAchievement.addContent("С 2013 года: разработка проектов ...");
        sectionAchievement.addContent("Реализация двухфакторной аутентификации ...");
        sectionAchievement.addContent("Налаживание процесса разработки ...");

        resume.setSection(SectionType.ACHIEVEMENT, sectionAchievement);

        // Квалификация
        Section<String> sectionQualified = new ListSection();

        sectionQualified.addContent("JEE AS: GlassFish ...");
        sectionQualified.addContent("Version control ...");
        sectionQualified.addContent("DB: PostgreSQL ...");
        sectionQualified.addContent("Languages ...");

        resume.setSection(SectionType.QUALIFICATIONS, sectionQualified);

        // Опыт работы
        Section<Organization> sectionExp = new OrganizationSection();

        // Java Ops Project
        Organization jopOrg = new Organization("Java Ops Projects", "http://javaops.ru/");

        Period authorPeriod = new Period(
                LocalDate.parse("2013-10-01")
                , "Автор проекта."
                , "Создание, организация и проведение Java онлайн проектов и стажировок."
        );

        jopOrg.addPeriod(authorPeriod);

        sectionExp.addContent(jopOrg);

        // Wrike
        Organization wrikeOrg = new Organization("Wrike", "https://www.wrike.com/");

        Period seniorDeveloperPeriod = new Period(
                LocalDate.parse("2014-10-01")
                , LocalDate.parse("2016-01-01")
                , "Старший разработчик (backend)"
                , "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
        );

        wrikeOrg.addPeriod(seniorDeveloperPeriod);

        sectionExp.addContent(wrikeOrg);


        // RIT Center
        Organization ritCenter = new Organization("RIT Center");

        Period architect = new Period(
                LocalDate.parse("2012-04-01")
                , LocalDate.parse("2014-10-01")
                , "Java архитектор"
                , "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
        );

        ritCenter.addPeriod(architect);

        sectionExp.addContent(ritCenter);

        resume.setSection(SectionType.EXPERIENCE, sectionExp);


        // Образование
        Section<Organization> sectionEdu = new OrganizationSection();

        // Coursera
        Organization coursera = new Organization("Coursera", "https://www.coursera.org/course/progfun");

        Period courseraPeriod = new Period(
                LocalDate.parse("2013-03-01")
                , LocalDate.parse("2013-05-01")
                , "Functional Programming Principles"
        );

        coursera.addPeriod(courseraPeriod);

        sectionEdu.addContent(coursera);


        // Alcatel
        Organization alcatel = new Organization("Alcatel", "http://www.alcatel.ru/");

        Period studyTelephone = new Period(
                LocalDate.parse("1997-09-01")
                , LocalDate.parse("1998-03-01")
                , "6 месяцев обучения цифровым телефонным сетям (Москва)"
        );

        alcatel.addPeriod(studyTelephone);

        sectionEdu.addContent(alcatel);

        // SPB
        Organization itmo = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/");

        Period graduate = new Period(
                LocalDate.parse("1993-09-01")
                , LocalDate.parse("1996-07-01")
                , "Аспирантура"
                , "(программист С, С++)"
        );

        Period engineer = new Period(
                LocalDate.parse("1987-09-01")
                , LocalDate.parse("1993-07-01")
                , "Инженер"
                , "(программист Fortran, C)"
        );

        itmo.addPeriod(graduate);
        itmo.addPeriod(engineer);

        sectionEdu.addContent(itmo);

        resume.setSection(SectionType.EDUCATION, sectionEdu);

        for (SectionType section : SectionType.values()) {
            System.out.println(section.getTitle() + "\n" + resume.getSection(section));
        }

    }
}
