package com.robinstudio.sleeveapi.exception.http;

public class ForbiddenException extends HttpException {
    public ForbiddenException(int code){
        this.code = code;
        this.httpStatusCode = 403; // 表示登录但没有权限
    }
}
