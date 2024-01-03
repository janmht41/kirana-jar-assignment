package com.assignment.KiranaService.exception;

import com.assignment.KiranaService.model.Error;
import com.assignment.KiranaService.model.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for handling MethodArgumentNotValidException.
 */
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException and returns an appropriate error response.
     *
     * @param methodArgumentNotValidException The exception containing validation errors.
     * @return ErrorResponse containing details of the validation error.
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {

        List<String> errors = methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .error(Error.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .title("Request Error")
                        .message(errors.get(0))
                        .build())
                .build();
    }
}
