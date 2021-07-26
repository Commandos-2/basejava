package com.resume.webapp.model;

public enum ContactsType {
    PHONE("Telefon"),
    EMAIL("Email"){
        @Override
        public String toHtml0(String value){
            return "<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },
    SKYPE("Skype"){
        @Override
        public String toHtml0(String value){
            return "<a href='skype:" + value + "'>" + value + "</a>";
        }
    };
    private final String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

    protected String toHtml0(String value){
        return title+": "+value;
    }

    public String toHtml(String value){
        return (value==null) ? "":toHtml0(value);
    }
}
