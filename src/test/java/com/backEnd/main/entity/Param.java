package com.backEnd.main.entity;

import com.backEnd.main.enums.RequestParamsType;
import io.restassured.http.Header;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Param {
    @Getter
    @Setter
    private RequestParamsType type;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String value;

    @Getter
    @Setter
    private List<Header> headers;

    public Param(RequestParamsType paramType, String paramName, String paramValue){
        this.setType(paramType);
        this.setName(paramName);
        this.setValue(paramValue);
    }

    public Param(RequestParamsType paramType, String paramName, String paramValue, List<Header> headers){
        this.setType(paramType);
        this.setName(paramName);
        this.setValue(paramValue);
        this.setHeaders(headers);
    }
}
