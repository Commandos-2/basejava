package com.resume.webapp.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Organization extends AbstractSection {
    public Organization(ArrayList<Experiense> experienses) {
        super(experienses);
    }

    public static class Experiense {
        SimpleDateFormat formater = new SimpleDateFormat("MM-YYYY");
        String title;
        Date initialDate;
        Date endDate;
        String heading;
        String text;

        public Experiense(String title, Date initialDate, Date endDate, String heading, String text) {
            this.title = title;
            this.initialDate = initialDate;
            this.endDate = endDate;
            this.heading = heading;
            this.text = text;
        }

        public String getTitle() {
            return title;
        }

        public String getInitialDate() {
            return formater.format(initialDate);
        }

        public String getEndDate() {
            return formater.format(endDate);
        }

        public String getHeading() {
            return heading;
        }

        public String getText() {
            return text;
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
                    this.getInformation()).get(i).getTitle()).append("\n").append(((ArrayList<Experiense>)
                    this.getInformation()).get(i).getInitialDate()).append("-").append(((ArrayList<Experiense>)
                    this.getInformation()).get(i).getEndDate()).append("     ").append(((ArrayList<Experiense>)
                    this.getInformation()).get(i).getHeading()).append("\n").append(((ArrayList<Experiense>)
                    this.getInformation()).get(i).getText()).append("\n\n");
        }
        return String.valueOf(sb);
    }
}