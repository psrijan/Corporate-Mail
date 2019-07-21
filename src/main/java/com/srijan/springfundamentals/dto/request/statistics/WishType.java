package com.srijan.springfundamentals.dto.request.statistics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WishType {
    DAY("DAY"),
    WEEK("WEEK"),
    MONTH("MONTH"),
    YEAR("YEAR"),
    HALFYEAR("HALFYEAR");

    private String shortName;

    WishType(String shortName) {
        this.shortName = shortName;
    }
//
//    @JsonCreator
//    public static WishType create(String value) {
//        if (value == null) {
//            throw new IllegalArgumentException();
//        }
//        for (WishType v : values()) {
//            if (value.equals(v.getShortName())) {
//                return v;
//            }
//        }
//        throw new IllegalArgumentException();
//    }
//
//    @JsonValue
//    public String getShortName() {
//        return shortName;
//    }
//
//    @Override
//    public String toString() {
//        return shortName;
//    }
}
