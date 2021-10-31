package com.epam.esm.exception.util;

import com.epam.esm.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ThrowException {

    public void throwValidationException(List<String> list) {
        throw new ValidationException(String.join(", ", list));
    }


}
