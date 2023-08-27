package com.urise.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Link homePage;
    private final List<Period> periods = new ArrayList<>();

    public Organization(String name) {
        this(name, "");
    }

    public Organization(String name, String url) {
        this.homePage = new Link(name, url);
    }

    public void addPeriod(Period period) {
        periods.add(period);
    }

    @Override
    public String toString() {
        return homePage.toString()
                + "\n"
                + periods.toString();
    }
}
