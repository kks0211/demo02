package com.board.exception;

import com.board.config.http.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BaseControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = {BaseException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private BaseResponse<?> handleBaseException(BaseException e, WebRequest request) {
        //return new BaseResponse<String>(e.getResponseCode(), messageSource.getMessage(e.getMessage(), e.getArgs(), null));
        System.out.println("=======> " + e.getResponseCode().status());
        return new BaseResponse<String>(e.getResponseCode().status(), messageSource.getMessage(e.getResponseCode().message(), e.getArgs(), null));

    }

}