package com.resume.webapp.model;

public class TextSection extends AbstractSection {
    private static final long serialVersionUID=1L;
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
    public String toString() {
        return (String) information;
    }
}
