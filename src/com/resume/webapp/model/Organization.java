package com.resume.webapp.model;

import com.resume.webapp.util.LocalDateAdapter;

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
    private List<Position> positions = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, String url, Position... positions) {
        this.homePage = new Link(name, url);
        this.positions = Arrays.asList(positions);
    }

    public Organization(String name, String url, List<Position> positions) {
        this.homePage = new Link(name, url);
        this.positions = positions;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;
        //private final DateTimeFormatter formater = DateTimeFormatter.ofPattern("MM.yyyy");
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate initialDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String heading;
        private String text;

        public Position() {
        }

        public Position(LocalDate initialDate, LocalDate endDate, String heading, String text) {
            Objects.requireNonNull(initialDate, "initialDate not be null");
            Objects.requireNonNull(endDate, "endDate not be null");
            Objects.requireNonNull(heading, "heading not be null");

            this.initialDate = initialDate;
            this.endDate = endDate;
            this.heading = heading;
            this.text = text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position that = (Position) o;
            return Objects.equals(initialDate, that.initialDate) &&
                    Objects.equals(endDate, that.endDate) &&
                    Objects.equals(heading, that.heading) &&
                    Objects.equals(text, that.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(initialDate, endDate, heading, text);
        }

        public LocalDate getInitialDate() {
            return initialDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getHeading() {
            return heading;
        }

        public String getText() {
            return text;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) &&
                Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public Position getPosition(int j) {
        return positions.get(j);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getHomePage().toString()).append("\n");
        for (int i = 0; i < this.getPositions().size(); i++) {
            sb.append((
                    this.getPositions()).get(i).getInitialDate()).append("-").append((
                    this.getPositions()).get(i).getEndDate()).append("     ").append((
                    this.getPositions()).get(i).getHeading()).append("\n").append((
                    this.getPositions()).get(i).getText());
        }
        sb.append("\n");
        return String.valueOf(sb);
    }
}