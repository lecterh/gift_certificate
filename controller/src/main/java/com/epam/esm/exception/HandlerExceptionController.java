package com.epam.esm.exception;

import com.epam.esm.exception.util.ExceptionMessageReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class HandlerExceptionController extends ResponseEntityExceptionHandler {

    private final ExceptionMessageReader exceptionMessageReader;

    public HandlerExceptionController(ExceptionMessageReader exceptionMessageReader) {
        this.exceptionMessageReader = exceptionMessageReader;
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionEntity> handle(ServiceException exception) {

        ExceptionEntity exceptionEntity = new ExceptionEntity(exception.getMessage(), exception.getErrorCode());
        return ResponseEntity.badRequest().body(exceptionEntity);
    }

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
