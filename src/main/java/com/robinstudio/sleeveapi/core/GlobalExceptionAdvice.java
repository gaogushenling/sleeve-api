package com.robinstudio.sleeveapi.core;

import com.robinstudio.sleeveapi.core.config.ExceptionCodeConfig;
import com.robinstudio.sleeveapi.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

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
        System.out.println(e);
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

    // 校验body参数异常，校验时会处理多个校验信息 统一返回 400 错误
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public UnifyResponse handleBeanValidation(HttpServletRequest req, MethodArgumentNotValidException e) {
        String method = req.getMethod();
        String url = req.getRequestURI();

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = this.formatAllErrorMessages(errors);
        return new UnifyResponse(10001, message, method + " " + url);
    }

    public String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error -> {
            errorMsg.append(error.getDefaultMessage()).append(";");
        });
        return errorMsg.toString();
    }

    // 校验路径参数异常
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public UnifyResponse handleConstraintException(HttpServletRequest req, ConstraintViolationException e){
        String method = req.getMethod();
        String url = req.getRequestURI();
        String message = e.getMessage();
        // for(ConstraintViolation error: e.getConstraintViolations()){}

        return new UnifyResponse(10001, message, method + " " + url);
    }
}
