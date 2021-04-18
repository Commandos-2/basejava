package com.resume.webapp.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InformationSection extends AbstractSection {
    public InformationSection(ArrayList<Information> information) {
        super(information);
    }

    public static class Information {
        SimpleDateFormat formater = new SimpleDateFormat("MM-YYYY");
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
    public List getInformation() {
        return(List) information;
    }

    @Override
    public void setInformation(Object setInformation) {
        information = setInformation;
    }

    @Override
    public String getStringInformation() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.getInformation().size(); i++) {
            sb.append(((ArrayList<Information>) this.getInformation()).get(i).getTitle() + "\n" +
                    ((ArrayList<Information>) this.getInformation()).get(i).getInitialDate() + "-" +
                    ((ArrayList<Information>) this.getInformation()).get(i).getEndDate() + "     " +
                    ((ArrayList<Information>) this.getInformation()).get(i).getHeading() + "\n" +
                    ((ArrayList<Information>) this.getInformation()).get(i).getText()+ "\n\n");
        }
        return String.valueOf(sb);
    }
}