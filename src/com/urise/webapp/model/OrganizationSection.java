package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrganizationSection extends Section<Organization> {
    List<Organization> organizations = new ArrayList<>();

    @Override
    public void addContent(Organization content) {
        organizations.add(content);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Organization org: organizations) {
            result.append(org.toString()).append("\n");
        }
        return result.toString();
    }
}
