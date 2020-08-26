package com.backEnd.main.enums;

public enum RequestType {
    POST("Post"),
    GET("Get"),
    DELETE("Delete"),
    PUT("Put");

    private String value;

    RequestType(String adviserType) {
        this.value = adviserType;
    }

    public static RequestType getByName(String value) {
        for (RequestType v : values()) {
            if (v.value.replaceAll("(\\s+|_)", "").toLowerCase().contains(value.replaceAll("(\\s+|_)", "").toLowerCase())) {
                return v;
            }
            if(value.toLowerCase().contains(v.value.toLowerCase())){
                return v;
            }
        }
        throw new EnumConstantNotPresentException(RequestType.class, value);
    }
}
