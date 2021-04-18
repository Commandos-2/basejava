package com.resume.webapp.model;

public enum TypeContacts {
    PHONE("Телефон"),
    EMAIL("Электронная почта"),
    SKYPE("Скайп");
    private String title;

    TypeContacts(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "TypeContacts{" +
                "title='" + title + '\'' +
                '}';
    }
}
