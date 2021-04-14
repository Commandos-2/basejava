package com.resume.webapp.model;

import java.util.ArrayList;

public class SectionListText extends AbstractSection {
    public SectionListText(ArrayList<String> information) {
        super(information);
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
        return String.join(" /n ", (String) information);
    }
}
