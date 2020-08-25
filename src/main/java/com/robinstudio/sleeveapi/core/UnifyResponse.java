package com.robinstudio.sleeveapi.core;

public class UnifyResponse {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getRequest() {
        return request;
    }

    private String request;

    public UnifyResponse(int code, String message, String request){
        this.code = code;
        this.message = message;
        this.request = request;
    }
}
