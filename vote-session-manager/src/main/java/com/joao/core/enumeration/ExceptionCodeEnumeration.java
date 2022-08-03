package com.joao.core.enumeration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExceptionCodeEnumeration {
    AGENDA_NOT_FOUND("agenda not found in the database", "001"),
    AGENDA_ALREADY_HAS_A_SESSION("there is already a session for the selected agenda", "002"),
    SESSION_CLOSED("the agenda session has already closed", "003"),
    SESSION_NOT_CREATED("not found a session created for agenda, please create a session for agenda", "004"),
    SECOND_ATTEMPT_VOTE("it is not allowed to vote twice on the same agenda", "005"),
    NOT_FOUND("not found information status associate", "006"),
    BAD_REQUEST("invalid request", "007"),
    NOT_ENABLED_VOTE("associate has no voting rights", "008"),
    SESSION_OPEN("the agenda session has open", "009");

    public final String message;
    public final String code;
}
