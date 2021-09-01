package com.board.exception;

import com.board.config.http.BaseResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@ControllerAdvice
public class BaseControllerAdvice {

    @Setter(onMethod_ = @Autowired)
    private MessageSource messageSource;

    @ExceptionHandler(value = {BaseException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private BaseResponse<?> handleBaseException(BaseException e, WebRequest request) {
        return new BaseResponse<String>(e.getResponseCode(), messageSource.getMessage(e.getResponseCode().name(), e.getArgs(), Locale.KOREA));
    }

}