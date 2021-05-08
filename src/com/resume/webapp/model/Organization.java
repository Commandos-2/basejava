package com.resume.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Link homePage;
    private List<Position> positions = new ArrayList<>();

    public Organization(String name, String url, Position... positions) {
        this.homePage = new Link(name, url);
        this.positions = Arrays.asList(positions);
    }

    public Organization(String name, String url, List<Position> positions) {
        this.homePage = new Link(name, url);
        this.positions = positions;
    }

    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;
        //private final DateTimeFormatter formater = DateTimeFormatter.ofPattern("MM.yyyy");
        private final ArrayList<LocalDate> initialDate;
        private final ArrayList<LocalDate> endDate;
        private final ArrayList<String> heading;
        private final ArrayList<String> text;

        public Position(ArrayList<LocalDate> initialDate, ArrayList<LocalDate> endDate, ArrayList<String> heading, ArrayList<String> text) {
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

        public String getInitialDate(int i) {
            return initialDate.get(i).toString();
        }

        public ArrayList<LocalDate> getInitialDate() {
            return initialDate;
        }

        public String getEndDate(int i) {
            return endDate.get(i).toString();
        }

        public String getHeading(int i) {
            return heading.get(i);
        }

        public String getText(int i) {
            return text.get(i);
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.getPositions().size(); i++) {
            sb.append(getHomePage().toString()).append("\n");
            for (int j = 0; j < this.getPositions().get(i).getInitialDate().size(); j++) {
                sb.append((
                        this.getPositions()).get(i).getInitialDate(j)).append("-").append((
                        this.getPositions()).get(i).getEndDate(j)).append("     ").append((
                        this.getPositions()).get(i).getHeading(j)).append("\n").append((
                        this.getPositions()).get(i).getText(j));
            }
            sb.append("\n");
        }
        return String.valueOf(sb);
    }
}