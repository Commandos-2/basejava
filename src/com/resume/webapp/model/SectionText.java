package com.resume.webapp.model;

public class SectionText extends AbstractSection {
    public SectionText(String information) {
        super(information);
    }

    @Override
    public String getInformation() {
        return (String) information;
    }

    @Override
    public void setInformation(Object setInformation) {
        information = setInformation;
    }

    @Override
    public String getStringInformation(Object information) {
        return (String) information;
    }
}
