package com.resume.webapp.model;

import java.util.ArrayList;
import java.util.Date;

public class SectionInformation extends AbstractSection {
    public SectionInformation(ArrayList<Information> information) {
        super(information);
    }

    private class Information {
        String title = null;
        Date initialDate = null;
        Date endDate = null;
        String heading = null;
        String text = null;

        public Information() {
        }

        public Information(String title, Date initialDate, Date endDate, String heading, String text) {
            this.title = title;
            this.initialDate = initialDate;
            this.endDate = endDate;
            this.heading = heading;
            this.text = text;
        }

        public String getTitle() {
            return title;
        }

        public Date getInitialDate() {
            return initialDate;
        }

        public Date getEndDate() {
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
    public Object getInformation() {
        return information;
    }

    @Override
    public void setInformation(Object setInformation) {
        information = setInformation;
    }

    @Override
    public String getStringInformation(Object information) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ((ArrayList<Information>) information).size(); i++) {
            sb.append(((ArrayList<Information>) information).get(i).getTitle() + "/n" +
                    ((ArrayList<Information>) information).get(i).getInitialDate() + "-" +
                    ((ArrayList<Information>) information).get(i).getEndDate() + "     " +
                    ((ArrayList<Information>) information).get(i).getHeading() + "/n" +
                    ((ArrayList<Information>) information).get(i).getText()+ "/n/n");
        }
        return String.valueOf(sb);
    }
}