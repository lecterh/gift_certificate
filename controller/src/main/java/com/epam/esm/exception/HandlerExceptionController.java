package com.epam.esm.exception;

import com.epam.esm.exception.util.ExceptionMessageReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class HandlerExceptionController extends ResponseEntityExceptionHandler {

    private static final List<String> AVAILABLE_LOCALES = Arrays.asList("en", "ru");
    private static final Locale CUR_LOCALE = new Locale("en");

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
            sb.append(Arrays.toString(exceptionMessageReader.readMessage(el, getLocale()).split(",")));
        }
        String code = String.join(",", listError);
        String message = sb.toString();

        return new ExceptionEntity(message, code);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionEntity> handle(EntityNotFoundException exception) {

        ExceptionEntity exceptionEntity = new ExceptionEntity(
                messageFormat(exception.getMessage(), exception.getParam()),
                exceptionMessageReader.readMessage(exception.getMessage(), getLocale()));
        return ResponseEntity.badRequest().body(exceptionEntity);
    }

    @ExceptionHandler(EntityDuplicateException.class)
    public ResponseEntity<ExceptionEntity> handle(EntityDuplicateException exception) {

        ExceptionEntity exceptionEntity = new ExceptionEntity(
                messageFormat(exception.getMessage(), exception.getParam()),
                exceptionMessageReader.readMessage(exception.getMessage(), getLocale()));
        return ResponseEntity.badRequest().body(exceptionEntity);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> handle(NotFoundAnyEntityException exception) {

        ExceptionEntity exceptionEntity = new ExceptionEntity(exception.getMessage(),
                exceptionMessageReader.readMessage(exception.getMessage(), getLocale()));
        return ResponseEntity.badRequest().body(exceptionEntity);
    }

    public String messageFormat(String message, Object param) {

        return String.format("%s {input=%s}", message, param.toString());
    }

    public Locale getLocale() {
        if (!AVAILABLE_LOCALES.contains(CUR_LOCALE.toString())) {
            return Locale.getDefault();
        }
        return CUR_LOCALE;
    }
}
