package com.epam.esm.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class ExceptionEntity {

    private String errorMessage;
    private String errorCode;


}
