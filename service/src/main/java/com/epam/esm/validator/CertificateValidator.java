package com.epam.esm.validator;

import com.epam.esm.entity.Certificate;
import com.epam.esm.error.ErrorCode;
import com.epam.esm.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.epam.esm.error.ErrorCode.*;

@Component
public class CertificateValidator {

    private final static Long MIN_ID = 1L;
    private final static int MIN_LENGTH_NAME = 3;
    private final static int MAX_LENGTH_NAME = 50;
    private final static int MIN_LENGTH_DESCRIPTION = 5;
    private final static int MAX_LENGTH_DESCRIPTION = 500;
    private final static BigDecimal MIN_PRICE = BigDecimal.ZERO;
    private final static int MIN_DURATION = 1;

    private final List<ErrorCode> error = new ArrayList<>();

    public boolean isCertificateValid(Certificate certificate) {


        return true;
    }

    public boolean isIdValid(Long id) {

        error.clear();
        if (id == null) {
            error.add(WRONG_TYPE_CERTIFICATE_ID);
        } else if (id < MIN_ID) {
            error.add(INCORRECT_CERTIFICATE_ID);
        }
        if (!error.isEmpty()) {
            throwException(error);
        }
        return true;
    }

    public boolean isNameValid(String name) {

        error.clear();
        if (name.isEmpty()) {
            error.add(CERTIFICATE_NAME_IS_EMPTY);
        } else if (name.length() < MIN_LENGTH_NAME) {
            error.add(CERTIFICATE_NAME_LESS);
        } else if (name.length() > MAX_LENGTH_NAME) {
            error.add(CERTIFICATE_NAME_MORE);
        }
        if (!error.isEmpty()) {
            throwException(error);
        }
        return true;
    }

    public boolean isPriceValid(BigDecimal price) {

        error.clear();
        if (price == null) {
            error.add(CERTIFICATE_PRICE_IS_NULL);
        } else if (price.compareTo(MIN_PRICE) < 0) {
            error.add(CERTIFICATE_PRICE_IS_NEGATIVE);
        }
        if (!error.isEmpty()) {
            throwException(error);
        }
        return true;
    }

    public boolean isDescriptionValid(String text) {

        error.clear();
        if (text.isEmpty()) {
            error.add(CERTIFICATE_DESCRIPTION_IS_NULL);
        } else if (text.length() < MIN_LENGTH_DESCRIPTION) {
            error.add(CERTIFICATE_DESCRIPTION_LESS);
        } else if (text.length() > MAX_LENGTH_DESCRIPTION) {
            error.add(CERTIFICATE_DESCRIPTION_MORE);
        }
        if (!error.isEmpty()) {
            throwException(error);
        }
        return true;
    }

    private boolean isDurationValid(int duration) {

        error.clear();
        if (duration < MIN_DURATION) {
            error.add(CERTIFICATE_DURATION_IS_NULL);
        }
        if (!error.isEmpty()) {
            throwException(error);
        }
        return true;
    }

    private static void throwException(List<ErrorCode> error) {
        for (ErrorCode er : error) {
            throw new ServiceException(er);
        }
    }

}
