package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    List<Period> periods = new ArrayList<>();
    String name;
    String url;

    public Organization(String name) {
        this(name, "");
    }

    public Organization(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public void addPeriod(Period period) {
        periods.add(period);
    }

    @Override
    public String toString() {
        return name
                + "\n"
                + url
                + "\n"
                + periods.toString();
    }
}
