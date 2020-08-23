package com.robinstudio.sleeveapi.exception.http;

public class UnauthenticatedException extends HttpException{
    public UnauthenticatedException(int code){
        this.code = code;
        this.httpStatusCode = 401; // 没有令牌、没有登录等情况下无权限访问
    }
}
