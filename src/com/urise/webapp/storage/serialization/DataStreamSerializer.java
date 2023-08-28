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
                int size = entry.getValue().size();
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        dos.writeInt(size);
                        for (int i = 0; i < size; i++) {
                            dos.writeUTF(entry.getValue().getContent(i).toString());
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        dos.writeInt(size);
                        for (int i = 0; i < size; i++) {
                            Organization org = (Organization) entry.getValue().getContent(i);
                            Link homePage = org.getHomePage();
                            dos.writeUTF(homePage.getName());
                            dos.writeUTF(homePage.getUrl());
                            int periodsLength = org.getPeriodsSize();
                            dos.writeInt(periodsLength);
                            for (int j = 0; j < periodsLength; j++) {
                                Organization.Period period = org.getPeriod(j);
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
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
                Section section;
                int countPoint;
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        section = new TextSection(dis.readUTF());
                        resume.setSection(sectionType, section);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        section = new ListSection();
                        countPoint = dis.readInt();
                        for (int j = 0; j < countPoint; j++) {
                            section.addContent(dis.readUTF());
                        }
                        resume.setSection(sectionType, section);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        section = new OrganizationSection();
                        countPoint = dis.readInt();
                        for (int j = 0; j < countPoint; j++) {
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
                            section.addContent(organization);
                        }
                        resume.setSection(sectionType, section);
                        break;
                }
            }
            return resume;
        }
    }
}