package com.resume.webapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization extends AbstractSection {
    public Organization(ArrayList<Experiense> experienses) {
        super(experienses);
    }

    public static class Experiense {
        private DateTimeFormatter formater = DateTimeFormatter.ofPattern("MM.yyyy");
        private Link title;
        private ArrayList<LocalDate> initialDate;
        private ArrayList<LocalDate> endDate;
        private ArrayList<String> heading;
        private ArrayList<String> text;

        public Experiense(String name, String url, ArrayList<LocalDate> initialDate, ArrayList<LocalDate> endDate, ArrayList<String> heading, ArrayList<String> text) {
            Objects.requireNonNull(initialDate, "initialDate не может быть null");
            Objects.requireNonNull(endDate, "endDate не может быть null");
            Objects.requireNonNull(heading, "heading не может быть null");
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
            return Objects.hash(formater, title, initialDate, endDate, heading, text);
        }

        public Link getTitle() {
            return title;
        }

        public String getInitialDate(int i) {
            return formater.format(initialDate.get(i));
        }

        public ArrayList<LocalDate> getInitialDate() {
            return initialDate;
        }

        public String getEndDate(int i) {
            return formater.format(endDate.get(i));
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