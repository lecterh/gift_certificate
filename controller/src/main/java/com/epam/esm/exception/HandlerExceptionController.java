package com.epam.esm.exception;

import com.epam.esm.exception.util.ExceptionMessageReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

@RestControllerAdvice
public class HandlerExceptionController extends ResponseEntityExceptionHandler {

    private final ExceptionMessageReader exceptionMessageReader;

    public HandlerExceptionController(ExceptionMessageReader exceptionMessageReader) {
        this.exceptionMessageReader = exceptionMessageReader;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionEntity handleValidationException(ValidationException exception) {

        StringBuilder sb = new StringBuilder();
        List<String> listError = new ArrayList<>(Arrays.asList(exception.getMessage().split(", ")));
        for (String el : listError) {
            sb.append(Arrays.toString(exceptionMessageReader.readMessage(el).split(",")));
        }
        String code = String.join(",", listError);
        String message = sb.toString();

        return new ExceptionEntity(message, code);
    }

    /*@ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionEntity> handle(ServiceException exception) {

        ExceptionEntity exceptionEntity = new ExceptionEntity(exception.getMessage(), exception.getErrorCode());
        return ResponseEntity.badRequest().body(exceptionEntity);
    }*/

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionEntity> handle(EntityNotFoundException exception) {

        ExceptionEntity exceptionEntity = new ExceptionEntity(
                messageFormat(exception.getMessage(), exception.getParam()),
                exceptionMessageReader.readMessage(exception.getMessage()));
        return ResponseEntity.badRequest().body(exceptionEntity);
    }

    @ExceptionHandler(EntityDuplicateException.class)
    public ResponseEntity<ExceptionEntity> handle(EntityDuplicateException exception) {

        ExceptionEntity exceptionEntity = new ExceptionEntity(
                messageFormat(exception.getMessage(), exception.getParam()),
                exceptionMessageReader.readMessage(exception.getMessage()));
        return ResponseEntity.badRequest().body(exceptionEntity);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> handle(NotFoundAnyEntityException exception) {

        ExceptionEntity exceptionEntity = new ExceptionEntity(exception.getMessage(),
                exceptionMessageReader.readMessage(exception.getMessage()));
        return ResponseEntity.badRequest().body(exceptionEntity);
    }

    public String messageFormat(String message, Object param) {

        return String.format("%s {input=%s}", message, param.toString());
    }
}
