package com.epam.esm.exception;

public class EntityNotFoundException extends RuntimeException {

    private final Object param;

    public EntityNotFoundException(String messageCode, Object param) {
        super(messageCode);
        this.param = param;
    }

    public Object getParam() {
        return param;
    }
}
