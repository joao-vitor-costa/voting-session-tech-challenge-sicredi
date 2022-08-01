package com.joao.core.enumeration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExceptionCodeEnumeration {
    AGENDA_NOT_FOUND("agenda not found in the database", "001"),
    AGENDA_ALREADY_HAS_A_SESSION("there is already a session for the selected agenda","002");


    public final String message;
    public final String code;
}
