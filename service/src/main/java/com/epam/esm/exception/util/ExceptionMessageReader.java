package com.epam.esm.exception.util;

import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class ExceptionMessageReader {

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public String readMessage(String codeMessage) {
        return bundle.getString(codeMessage);
    }
}
