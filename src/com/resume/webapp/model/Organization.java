package com.resume.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization extends AbstractSection {
    private static final long serialVersionUID=1L;
    public Organization(ArrayList<Experiense> experienses) {
        super(experienses);
    }

    public static class Experiense implements Serializable {
        private static final long serialVersionUID=1L;
        //private final DateTimeFormatter formater = DateTimeFormatter.ofPattern("MM.yyyy");
        private final Link title;
        private final ArrayList<LocalDate> initialDate;
        private final ArrayList<LocalDate> endDate;
        private final ArrayList<String> heading;
        private final ArrayList<String> text;

        public Experiense(String name, String url, ArrayList<LocalDate> initialDate, ArrayList<LocalDate> endDate, ArrayList<String> heading, ArrayList<String> text) {
            Objects.requireNonNull(initialDate, "initialDate not be null");
            Objects.requireNonNull(endDate, "endDate not be null");
            Objects.requireNonNull(heading, "heading not be null");
            this.title = new Link(name, url);
            this.initialDate = initialDate;
            this.endDate = endDate;
            this.heading = heading;
            this.text = text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Experiense that = (Experiense) o;
            return Objects.equals(title, that.title) &&
                    Objects.equals(initialDate, that.initialDate) &&
                    Objects.equals(endDate, that.endDate) &&
                    Objects.equals(heading, that.heading) &&
                    Objects.equals(text, that.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, initialDate, endDate, heading, text);
        }

        public Link getTitle() {
            return title;
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
    public List<Experiense> getInformation() {
        return (List<Experiense>) information;
    }


    @Override
    public void setInformation(Object setInformation) {
        information = setInformation;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.getInformation().size(); i++) {
            sb.append(((ArrayList<Experiense>)
                    this.getInformation()).get(i).getTitle()).append("\n");
            for (int j = 0; j < this.getInformation().get(i).getInitialDate().size(); j++) {
                sb.append(((ArrayList<Experiense>)
                        this.getInformation()).get(i).getInitialDate(j)).append("-").append(((ArrayList<Experiense>)
                        this.getInformation()).get(i).getEndDate(j)).append("     ").append(((ArrayList<Experiense>)
                        this.getInformation()).get(i).getHeading(j)).append("\n").append(((ArrayList<Experiense>)
                        this.getInformation()).get(i).getText(j)).append("\n\n");
            }
        }
        return String.valueOf(sb);
    }
}