package com.epam.esm.exception.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
@AllArgsConstructor
public class ExceptionMessageReader {

    public String readMessage(String codeMessage, Locale locale) {

        ResourceBundle bundle = ResourceBundle.getBundle("errorMessage", locale);
        return bundle.getString(codeMessage);
    }
}
