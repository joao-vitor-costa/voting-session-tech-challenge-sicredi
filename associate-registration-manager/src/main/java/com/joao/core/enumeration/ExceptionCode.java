package com.joao.core.enumeration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExceptionCode {

    ASSOCIATE_NOT_FOUND("Associate not found in the database", "001");

    public final String message;
    public final String code;
}
