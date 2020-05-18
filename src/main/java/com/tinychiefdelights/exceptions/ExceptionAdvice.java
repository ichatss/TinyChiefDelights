package com.tinychiefdelights.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    // NotFoundException
    @ResponseBody
    @ExceptionHandler(MainNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundHandler(MainNotFound ex) {
        return ex.getMessage();
    }


    // IllegalArgumentException
    @ResponseBody
    @ExceptionHandler(MainIllegalArgument.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String IllegalArgumentHandler(MainIllegalArgument ex) {
        return ex.getMessage();
    }


    // NullPointerException
    @ResponseBody
    @ExceptionHandler(MainNullPointer.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String NullPointerHandler(MainNullPointer ex) {
        return ex.getMessage();
    }
}