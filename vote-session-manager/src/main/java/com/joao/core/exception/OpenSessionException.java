package com.joao.core.exception;

import com.joao.core.enumeration.ExceptionCodeEnumeration;
import lombok.Getter;

public class OpenSessionException extends RuntimeException {
    @Getter
    private final String errorCode;

    public OpenSessionException(ExceptionCodeEnumeration exceptionCodeEnumeration) {
        super(exceptionCodeEnumeration.message);
        this.errorCode = exceptionCodeEnumeration.name();
    }
}
