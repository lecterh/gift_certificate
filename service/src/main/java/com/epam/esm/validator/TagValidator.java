package com.epam.esm.validator;

import com.epam.esm.error.ErrorCode;
import com.epam.esm.exception.util.ThrowException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Scope(value = "prototype", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class TagValidator {

    final static Long MIN_ID = 1L;
    final static int MIN_LENGTH_NAME = 2;
    final static int MAX_LENGTH_NAME = 50;
    private final List<String> validationErrors = new ArrayList<>();
    private final ThrowException throwException;

    public void getIdValid(Long id) {

        if (id < MIN_ID) {
            validationErrors.add(ErrorCode.TAG_INVALID_ID.getCode());
        }
        if (!validationErrors.isEmpty()) {
            throwException.throwValidationException(validationErrors);
        }
    }

    public void getNameValid(String name) {

        if (name.isEmpty()) {
            validationErrors.add(ErrorCode.TAG_NAME_IS_EMPTY.getCode());
        } else if (name.length() < MIN_LENGTH_NAME) {
            validationErrors.add(ErrorCode.TAG_NAME_LENGTH_MIN.getCode());
        } else if (name.length() > MAX_LENGTH_NAME) {
            validationErrors.add(ErrorCode.TAG_NAME_LENGTH_MAX.getCode());
        }
        if (!validationErrors.isEmpty()) {
            throwException.throwValidationException(validationErrors);
        }
    }
}
