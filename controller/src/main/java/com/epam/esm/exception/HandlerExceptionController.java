package com.epam.esm.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class HandlerExceptionController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionEntity> handle(ServiceException exception) {

        ExceptionEntity exceptionEntity = new ExceptionEntity(exception.getMessage() + " : ", exception.getErrorCode());
        return ResponseEntity.badRequest().body(exceptionEntity);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionEntity> handle(RuntimeException exception) {
        ExceptionEntity exceptionEntity = new ExceptionEntity(exception.getMessage(), exception.toString());
        return ResponseEntity.badRequest().body(exceptionEntity);
    }




}
