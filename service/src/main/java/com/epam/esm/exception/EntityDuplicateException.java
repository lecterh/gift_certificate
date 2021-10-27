package com.epam.esm.exception;

public class EntityDuplicateException extends RuntimeException {

    private final Object param;

    public EntityDuplicateException(String messageCode, Object param) {
        super(messageCode);
        this.param = param;
    }

    public Object getParam() {
        return param;
    }
}
