package com.epam.esm.error;

import org.springframework.stereotype.Component;

public enum ErrorCode {

    CERTIFICATE_IS_NULL(CodeError.ERROR_0400150, MessageError.ERROR_0400150),
    CERTIFICATE_NAME_IS_EMPTY(CodeError.ERROR_0400151, MessageError.ERROR_0400151),
    CERTIFICATE_NAME_LESS(CodeError.ERROR_0400152, MessageError.ERROR_0400152),
    CERTIFICATE_NAME_MORE(CodeError.ERROR_0400153, MessageError.ERROR_0400153),
    CERTIFICATE_DESCRIPTION_IS_NULL(CodeError.ERROR_0400154, MessageError.ERROR_0400154),
    CERTIFICATE_DESCRIPTION_LESS(CodeError.ERROR_0400155, MessageError.ERROR_0400155),
    CERTIFICATE_DESCRIPTION_MORE(CodeError.ERROR_0400156, MessageError.ERROR_0400156),
    CERTIFICATE_PRICE_IS_NULL(CodeError.ERROR_0400157, MessageError.ERROR_0400157),
    CERTIFICATE_PRICE_IS_NEGATIVE(CodeError.ERROR_0400158, MessageError.ERROR_0400158),
    CERTIFICATE_DURATION_IS_NULL(CodeError.ERROR_0400159, MessageError.ERROR_0400159),
    CERTIFICATE_WITH_SAME_NAME_EXIST(CodeError.ERROR_0400160, MessageError.ERROR_0400160),
    CERTIFICATE_WITH_SAME_NAME_NOT_EXIST(CodeError.ERROR_0400161, MessageError.ERROR_0400161),
    INCORRECT_CERTIFICATE_ID(CodeError.ERROR_0400162, MessageError.ERROR_0400162),
    WRONG_TYPE_CERTIFICATE_ID(CodeError.ERROR_0400163, MessageError.ERROR_0400163),

    TAG_IS_NULL(CodeError.ERROR_0500150, MessageError.ERROR_0500150),
    TAG_NAME_IS_EMPTY(CodeError.ERROR_0500151, MessageError.ERROR_0500151),
    TAG_NAME_LESS(CodeError.ERROR_0500152, MessageError.ERROR_0500152),
    TAG_NAME_MORE(CodeError.ERROR_0500153, MessageError.ERROR_0500153),
    TAG_WITH_SAME_NAME_NOT_EXIST(CodeError.ERROR_0500154, MessageError.ERROR_0500154),
    INCORRECT_ID(CodeError.ERROR_0500155, MessageError.ERROR_0500155),
    WRONG_TYPE_ID(CodeError.ERROR_0500156, MessageError.ERROR_0500156);

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
