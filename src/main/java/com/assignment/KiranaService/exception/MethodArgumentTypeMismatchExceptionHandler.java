package com.assignment.KiranaService.exception;

import com.assignment.KiranaService.model.Error;
import com.assignment.KiranaService.model.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
public class MethodArgumentTypeMismatchExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exc){

        return ErrorResponse.builder().
                error(Error.builder().code(HttpStatus.BAD_REQUEST.value())
                        .title("Request Error")
                        .message("invalid date format")
                        .build()).
                build();
    }
}
