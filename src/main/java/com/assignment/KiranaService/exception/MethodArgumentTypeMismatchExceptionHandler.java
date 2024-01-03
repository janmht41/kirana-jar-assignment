package com.assignment.KiranaService.exception;

import com.assignment.KiranaService.model.ErrorModel;
import com.assignment.KiranaService.model.ErrorResponseModel;

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
    public ErrorResponseModel handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exc){

        return ErrorResponseModel.builder().
                errorModel(ErrorModel.builder().code(HttpStatus.BAD_REQUEST.value())
                        .title("Request ErrorModel")
                        .message("invalid date format")
                        .build()).
                build();
    }
}
