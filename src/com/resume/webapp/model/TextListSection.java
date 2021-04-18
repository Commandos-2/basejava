package com.resume.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TextListSection extends AbstractSection {
    public TextListSection(ArrayList<String> information) {
        super(information);
    }

    @Override
    public List getInformation() {
        return (List)information;
    }

    @Override
    public void setInformation(Object setInformation) {
        information = setInformation;
    }

    @Override
    public String getStringInformation() {
        return String.join(" \n ",  this.getInformation());
    }
}
