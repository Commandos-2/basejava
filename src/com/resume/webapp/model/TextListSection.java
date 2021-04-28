package com.resume.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TextListSection extends AbstractSection {
    public TextListSection(ArrayList<String> information) {
        super(information);
    }

    @Override
    public List<String> getInformation() {
        return (List<String>) information;
    }

    @Override
    public void setInformation(Object setInformation) {
        information = setInformation;
    }


    @Override
    public String toString() {
        return String.join(" \n ", (List<String>) information);
    }
}
