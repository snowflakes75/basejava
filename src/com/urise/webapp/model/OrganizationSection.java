package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrganizationSection extends Section<Organization> {
    private static final long serialVersionUID = 1L;
    private final List<Organization> organizations = new ArrayList<>();

    @Override
    public void addContent(Organization content) {
        organizations.add(content);
    }

    @Override
    public Organization getContent(int i) {
        return organizations.get(i);
    }

    @Override
    public int size() {
        return organizations.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Organization org: organizations) {
            result.append(org.toString()).append("\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return organizations.equals(that.organizations);

    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }
}
