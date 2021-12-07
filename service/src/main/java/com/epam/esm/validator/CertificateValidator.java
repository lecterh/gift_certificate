package com.epam.esm.validator;

import com.epam.esm.entity.Certificate;
import com.epam.esm.error.ErrorCode;
import com.epam.esm.exception.util.ThrowException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
@Scope(value = "prototype", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CertificateValidator {

    private final static Long MIN_ID = 1L;
    private final static int MIN_LENGTH_NAME = 2;
    private final static int MAX_LENGTH_NAME = 50;
    private final static int MIN_LENGTH_DESCRIPTION = 5;
    private final static int MAX_LENGTH_DESCRIPTION = 500;
    private final static BigDecimal MIN_PRICE = BigDecimal.ZERO;
    private final static int MIN_DURATION = 1;

    private final List<String> validationErrors = new ArrayList<>();
    private final ThrowException throwException;

    public void getCertificateValid(Certificate certificate) {

        getNameValid(certificate.getName());
        getPriceValid(certificate.getPrice());
        getDescriptionValid(certificate.getDescription());
        getDurationValid(certificate.getDuration());
        if (!validationErrors.isEmpty()) {
            throwException.throwValidationException(validationErrors);
        }
    }

    public void getIdValid(Long id) {

        if (id == null) {
            validationErrors.add(ErrorCode.WRONG_TYPE_CERTIFICATE_ID.getCode());
        } else if (id < MIN_ID) {
            validationErrors.add(ErrorCode.INCORRECT_CERTIFICATE_ID.getCode());
        }
        if (!validationErrors.isEmpty()) {
            throwException.throwValidationException(validationErrors);
        }
    }

    public void getNameValid(String name) {

        if (name.isEmpty()) {
            validationErrors.add(ErrorCode.CERTIFICATE_NAME_IS_EMPTY.getCode());
        } else if (name.length() < MIN_LENGTH_NAME) {
            validationErrors.add(ErrorCode.CERTIFICATE_NAME_LENGTH_MIN.getCode());
        } else if (name.length() > MAX_LENGTH_NAME) {
            validationErrors.add(ErrorCode.CERTIFICATE_NAME_LENGTH_MAX.getCode());
        }
    }

    public void getPriceValid(BigDecimal price) {

        if (price == null) {
            validationErrors.add(ErrorCode.CERTIFICATE_PRICE_IS_NULL.getCode());
        } else if (price.compareTo(MIN_PRICE) < 0) {
            validationErrors.add(ErrorCode.CERTIFICATE_PRICE_IS_NEGATIVE.getCode());
        }
    }

    public void getDescriptionValid(String text) {

        if (text.isEmpty()) {
            validationErrors.add(ErrorCode.CERTIFICATE_DESCRIPTION_IS_NULL.getCode());
        } else if (text.length() < MIN_LENGTH_DESCRIPTION) {
            validationErrors.add(ErrorCode.CERTIFICATE_DESCRIPTION_LESS.getCode());
        } else if (text.length() > MAX_LENGTH_DESCRIPTION) {
            validationErrors.add(ErrorCode.CERTIFICATE_DESCRIPTION_MORE.getCode());
        }
    }

    private void getDurationValid(int duration) {

        if (duration < MIN_DURATION) {
            validationErrors.add(ErrorCode.CERTIFICATE_DURATION_IS_NULL.getCode());
        }
    }
}
