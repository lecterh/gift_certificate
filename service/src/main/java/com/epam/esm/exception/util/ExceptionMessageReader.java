package com.epam.esm.exception.util;

import lombok.AllArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
@AllArgsConstructor
public class ExceptionMessageReader {

    //private final ResourceBundle bundle = ResourceBundle.getBundle("errorMessage");
    /*public ExceptionMessageReader(String strLocale) {
        Locale locale = new Locale(strLocale);
        bundle = ResourceBundle.getBundle("errorMessage", locale);
    }*/

    public String readMessage(String codeMessage, Locale locale) {

        ResourceBundle bundle = ResourceBundle.getBundle("errorMessage", locale);
        return bundle.getString(codeMessage);
    }
}
