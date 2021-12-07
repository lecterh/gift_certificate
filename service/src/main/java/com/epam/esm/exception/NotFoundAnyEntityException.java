package com.epam.esm.exception;

public class NotFoundAnyEntityException extends RuntimeException {

    public NotFoundAnyEntityException(String message) {
        super(message);
    }
}
