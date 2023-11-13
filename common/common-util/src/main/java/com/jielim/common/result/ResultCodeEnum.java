package com.jielim.common.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"Success"),
    FAIL(201,"Fail"),
    SERVICE_ERROR(2012,"Service Error"),
    DATA_ERROR(204,"Data Error"),
    LOGIN_AUTH(208,"No Login"),
    PERMISSION(209,"No Permission");

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }


}
