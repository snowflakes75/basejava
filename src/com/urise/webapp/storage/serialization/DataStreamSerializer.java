package com.urise.webapp.storage.serialization;


import com.urise.webapp.model.*;
import com.urise.webapp.storage.Serialization;

import java.io.*;
import java.time.LocalDate;
import java.util.Map;

public class DataStreamSerializer implements Serialization {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            // TODO implements sections
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.values().size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        TextSection textSection = (TextSection) entry.getValue();
                        dos.writeUTF(textSection.getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = (ListSection) entry.getValue();
                        int listSize = listSection.size();
                        dos.writeInt(listSize);
                        for (int i = 0; i < listSize; i++) {
                            dos.writeUTF(listSection.getContent(i));
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                        int orgSize = organizationSection.size();
                        dos.writeInt(orgSize);
                        for (int i = 0; i < orgSize; i++) {
                            Organization org = organizationSection.getContent(i);
                            Link homePage = org.getHomePage();
                            dos.writeUTF(homePage.getName());
                            dos.writeUTF(homePage.getUrl());
                            int periodsLength = org.getPeriodsSize();
                            dos.writeInt(periodsLength);
                            for (int j = 0; j < periodsLength; j++) {
                                Organization.Period period = org.getPeriod(j);
                                dos.writeUTF(String.valueOf(period.getStartDate()));
                                dos.writeUTF(String.valueOf(period.getEndDate()));
                                dos.writeUTF(period.getPosition());
                                dos.writeUTF(period.getDescription());
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeContacts = dis.readInt();
            for (int i = 0; i < sizeContacts; i++) {
                resume.setContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // TODO implements sections
            int sizeSections = dis.readInt();
            for (int i = 0; i < sizeSections; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        TextSection textSection = new TextSection(dis.readUTF());
                        resume.setSection(sectionType, textSection);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = new ListSection();
                        int countPoint = dis.readInt();
                        for (int j = 0; j < countPoint; j++) {
                            listSection.addContent(dis.readUTF());
                        }
                        resume.setSection(sectionType, listSection);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = new OrganizationSection();
                        int orgSize = dis.readInt();
                        for (int j = 0; j < orgSize; j++) {
                            Organization organization = new Organization(dis.readUTF(), dis.readUTF());
                            int periodsLength = dis.readInt();
                            for (int k = 0; k < periodsLength; k++) {
                                Organization.Period period = new Organization.Period(
                                        LocalDate.parse(dis.readUTF())
                                        , LocalDate.parse(dis.readUTF())
                                        , dis.readUTF()
                                        , dis.readUTF()
                                );
                                organization.addPeriod(period);
                            }
                            organizationSection.addContent(organization);
                        }
                        resume.setSection(sectionType, organizationSection);
                        break;
                }
            }
            return resume;
        }
    }
}