package com.epam.esm.exception.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
@AllArgsConstructor
public class ExceptionMessageReader {

    private final ResourceBundle bundle = ResourceBundle.getBundle("errorMessage");

    public String readMessage(String codeMessage) {

        return bundle.getString(codeMessage);
    }
}
