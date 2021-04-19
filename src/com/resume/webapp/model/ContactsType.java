package com.resume.webapp.model;

public enum ContactsType {
    PHONE("Телефон"),
    EMAIL("Электронная почта"),
    SKYPE("Скайп");
    private String title;

    ContactsType(String title) {
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
