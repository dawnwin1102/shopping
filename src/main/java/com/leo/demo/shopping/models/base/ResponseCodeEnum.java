package com.leo.demo.shopping.models.base;

public enum ResponseCodeEnum {
    Code_0000("0000", "success"),
    Code_1000("1000", "system error"),
    Code_2001("2001", "Item list is empty"),
    Code_2002("2002", "Item check failed"),
    Code_2003("2003", "order not found"),
    Code_3001("3001", "need login")
    ;


    private String code;
    private String message;


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResponseCodeEnum(String code, String message) {
        this.message = message;
        this.code = code;
    }
}
