package com.resume.webapp.model;

public enum SectionType {

    PERSONAL("Personal"),
    OBJECTIVE("Objective"),
    ACHIEVEMENT("Achievement"),
    QUALIFICATIONS("Qualifications"),
    EXPERIENCE("Experience"),
    EDUCATION("education");
    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "TypeSection{" +
                "title='" + title + '\'' +
                '}';
    }
}
