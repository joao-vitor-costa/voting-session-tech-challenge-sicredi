package com.joao.core.enumeration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExceptionCode {
    AGENDA_NOT_FOUND("Agenda not found in the database", "001");

    public final String message;
    public final String code;
}
