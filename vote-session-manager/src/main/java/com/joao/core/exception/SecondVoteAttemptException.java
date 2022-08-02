package com.joao.core.exception;

import com.joao.core.enumeration.ExceptionCodeEnumeration;
import lombok.Getter;

public class SecondVoteAttemptException extends RuntimeException {
    @Getter
    private final String errorCode;

    public SecondVoteAttemptException(ExceptionCodeEnumeration exceptionCodeEnumeration) {
        super(exceptionCodeEnumeration.message);
        this.errorCode = exceptionCodeEnumeration.name();
    }
}
