package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Date;

public class Period {
    private LocalDate startDate;
    private LocalDate endDate;

    private String position;

    private String description;

    public Period(LocalDate start, LocalDate end, String position, String description) {
        this.startDate = start;
        this.endDate = end;
        this.position = position;
        this.description = description;
    }

    public Period(LocalDate start, String position, String description) {
        this(start, LocalDate.now(), position, description);
    }

    public Period(LocalDate start, LocalDate end, String description) {
        this(start, end, "", description);
    }

    @Override
    public String toString() {
        return "Period{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
