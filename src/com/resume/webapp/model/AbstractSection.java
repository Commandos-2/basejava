package com.resume.webapp.model;

public abstract class AbstractSection {
    protected Object information;

    public AbstractSection(Object information) {
        this.information = information;
    }

    public abstract Object getInformation();

    public abstract void setInformation(Object setInformation);

    public abstract String getStringInformation(Object information);


}
