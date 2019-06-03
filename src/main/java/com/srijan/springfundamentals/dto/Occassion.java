package com.srijan.springfundamentals.dto;

public enum Occassion {

    BIRTHDAY("email-alert.ftl"),
    FESTIVAL("festival.ftl"),
    BIRTHDAYALERT("self-birthday-alert.ftl");
    private final String template;

    Occassion(String template){
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
