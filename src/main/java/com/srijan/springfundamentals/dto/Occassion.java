package com.srijan.springfundamentals.dto;

public enum Occassion {

    BIRTHDAY("birthday-wish.ftl"),
    FESTIVAL("festival-wish.ftl"),
    BIRTHDAYALERT("birthday-alert.ftl");
    private final String template;

    Occassion(String template){
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
