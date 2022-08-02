package com.joao.core.exception;

import com.joao.core.enumeration.ExceptionCodeEnumeration;
import lombok.Getter;

public class CloseSessionException extends RuntimeException {
    @Getter
    private final String errorCode;

    public CloseSessionException(ExceptionCodeEnumeration exceptionCodeEnumeration) {
        super(exceptionCodeEnumeration.message);
        this.errorCode = exceptionCodeEnumeration.name();
    }
}
