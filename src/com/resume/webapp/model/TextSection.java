package com.resume.webapp.model;

public class TextSection extends AbstractSection {
    public TextSection(String information) {
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
    public String getStringInformation() {
        return this.getInformation();
    }
}