package com.epam.esm.validator;

import com.epam.esm.entity.Tag;
import com.epam.esm.error.ErrorCode;
import com.epam.esm.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import com.epam.esm.error.ErrorCode.*;

@Component
public class TagValidator {

    final static Long MIN_ID = 5L;
    final static int MIN_LENGTH_NAME = 3;
    final static int MAX_LENGTH_NAME = 50;
    private final List<ErrorCode> errors = new ArrayList<>();

    public boolean isTagValid(Tag tag) {

        if (tag == null) {
            errors.add(ErrorCode.TAG_IS_NULL);
        }
        isIdValid(tag.getId());
        isNameValid(tag.getName());
        if (!errors.isEmpty()) {
            throwError(errors);
        }
        return true;
    }

    public boolean isIdValid(Long id) {
        errors.clear();
        if (id == null) {
            errors.add(ErrorCode.WRONG_TYPE_ID);
        } else if (id < MIN_ID) {
            errors.add(ErrorCode.INCORRECT_ID);
        }
        if (!errors.isEmpty()) {
            throwError(errors);
        }
        return true;
    }

    public boolean isNameValid(String name) {
        errors.clear();
        if (name.isEmpty()) {
            errors.add(ErrorCode.TAG_NAME_IS_EMPTY);
        } else if (name.length() < MIN_LENGTH_NAME) {
            errors.add(ErrorCode.TAG_NAME_LESS);
        } else if (name.length() > MAX_LENGTH_NAME) {
            errors.add(ErrorCode.TAG_NAME_MORE);
        }
        if (!errors.isEmpty()) {
            throwError(errors);
        }
        return true;
    }

    private static void throwError(List<ErrorCode> error) {
        for (ErrorCode er : error) {
            throw new ServiceException(er);
        }

    }

}
