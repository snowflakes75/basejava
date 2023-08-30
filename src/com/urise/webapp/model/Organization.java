package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private Link homePage;
    private List<Period> periods = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name) {
        this(name, "");
    }

    public Organization(String name, String url) {
        this.homePage = new Link(name, url);
    }

    public Organization(String name, String url, Period... periods) {
        this.homePage = new Link(name, url);
        this.periods = Arrays.asList(periods);
    }

    public Organization(String name, String url, List<Period> periods) {
        this.homePage = new Link(name, url);
        this.periods = periods;
    }

    public Organization(String name, Period... periods) {
        this(name, "", periods);
    }

    public int getPeriodsSize() {
        return periods.size();
    }

    public Period getPeriod(int i) {
        return periods.get(i);
    }

    public void addPeriod(Period period) {
        periods.add(period);
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public String toString() {
        return homePage.toString()
                + "\n"
                + periods.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) &&
                Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, periods);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;

        private String position;

        private String description;

        public Period() {
        }

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

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getPosition() {
            return position;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
            return Objects.equals(startDate, period.startDate) &&
                    Objects.equals(endDate, period.endDate) &&
                    Objects.equals(position, period.position) &&
                    Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, position, description);
        }
    }
}
