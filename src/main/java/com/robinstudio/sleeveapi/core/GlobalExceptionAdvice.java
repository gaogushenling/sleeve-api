package com.robinstudio.sleeveapi.core;

import com.robinstudio.sleeveapi.core.config.ExceptionCodeConfig;
import com.robinstudio.sleeveapi.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @Autowired
    private ExceptionCodeConfig codeConfig;

    // 未知异常处理
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse HandleException(HttpServletRequest req, Exception e) {
        String method = req.getMethod();
        String url = req.getRequestURI();
        UnifyResponse response = new UnifyResponse(9999, "未知异常", method + " " + url);
        return response;
    }

    // http 已知异常
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponse> HandleHttpException(HttpServletRequest req, HttpException e) {
        String method = req.getMethod();
        String url = req.getRequestURI();
        UnifyResponse response = new UnifyResponse(e.getCode(), codeConfig.getMessage(e.getCode()), method + " " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());
        ResponseEntity<UnifyResponse> r = new ResponseEntity<>(response, headers, httpStatus);
        return r;
    }
}
