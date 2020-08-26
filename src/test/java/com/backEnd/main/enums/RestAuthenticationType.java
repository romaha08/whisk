package com.backEnd.main.enums;

public enum RestAuthenticationType {
    BEARER("bearer"),
    BASIC("basic");

    private String value;

    RestAuthenticationType(String adviserType) {
        this.value = adviserType;
    }

    public static RestAuthenticationType getByName(String value) {
        for (RestAuthenticationType v : values()) {
            if (v.value.replaceAll("(\\s+|_)", "").toLowerCase().contains(value.replaceAll("(\\s+|_)", "").toLowerCase())) {
                return v;
            }
            if(value.toLowerCase().contains(v.value.toLowerCase())){
                return v;
            }
        }
        throw new EnumConstantNotPresentException(RestAuthenticationType.class, value);
    }
}
